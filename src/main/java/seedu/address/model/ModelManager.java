package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CampusConnect campusConnect;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final ObservableList<Tag> currentlyDefinedTags;

    /**
     * Initializes a ModelManager with the given campusConnect and userPrefs.
     */
    public ModelManager(ReadOnlyCampusConnect campusConnect, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(campusConnect, userPrefs);

        logger.fine("Initializing with campus connect: " + campusConnect + " and user prefs " + userPrefs);

        this.campusConnect = new CampusConnect(campusConnect);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.campusConnect.getPersonList());
        this.currentlyDefinedTags = FXCollections.observableArrayList();
        refreshTagList();
    }

    public ModelManager() {
        this(new CampusConnect(), new UserPrefs());
    }

    //=========== UserPrefs ==========================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getCampusConnectFilePath() {
        return userPrefs.getCampusConnectFilePath();
    }

    @Override
    public void setCampusConnectFilePath(Path campusConnectFilePath) {
        requireNonNull(campusConnectFilePath);
        userPrefs.setCampusConnectFilePath(campusConnectFilePath);
    }

    //=========== CampusConnect ======================================================

    @Override
    public void setCampusConnect(ReadOnlyCampusConnect campusConnect) {
        this.campusConnect.resetData(campusConnect);
        refreshTagList();
    }

    @Override
    public ReadOnlyCampusConnect getCampusConnect() {
        return campusConnect;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return campusConnect.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        campusConnect.removePerson(target);
        refreshTagList();
    }

    @Override
    public void addPerson(Person person) {
        campusConnect.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        refreshTagList();
    }

    @Override
    public void insertPerson(Person p, int ind) {
        campusConnect.addPerson(p, ind);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        refreshTagList();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        campusConnect.setPerson(target, editedPerson);
        refreshTagList();
    }

    //=========== Filtered Person List Accessors =====================================

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Tag List Accessors =================================================

    /**
     * Refreshes and returns an unmodifiable view of the list of tags currently in CampusConnect
     */
    private void refreshTagList() {
        currentlyDefinedTags.setAll(campusConnect.getTagList()); // Assume getAllTags aggregates unique tags
    }

    @Override
    public ObservableList<Tag> getListOfCurrentTags() {
        return FXCollections.unmodifiableObservableList(currentlyDefinedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return campusConnect.equals(otherModelManager.campusConnect)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    @Override
    public int hashCode() {
        // Hash based on essential components of the model manager
        return campusConnect.hashCode() + userPrefs.hashCode() + filteredPersons.hashCode();
    }
}

