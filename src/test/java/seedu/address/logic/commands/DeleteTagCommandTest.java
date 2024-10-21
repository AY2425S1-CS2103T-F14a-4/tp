package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.FRIEND_TAG;
import static seedu.address.testutil.TypicalPersons.OWES_MONEY_TAG;
import static seedu.address.testutil.TypicalPersons.getTypicalCampusConnect;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class DeleteTagCommandTest {
    private static final Model model = new ModelManager(getTypicalCampusConnect(), new UserPrefs());
    private static final String DEFAULT_TAG = "test";
    private static final String DEFAULT_TAG_SECOND = "test2";
    private static final String TEST_EMAIL = "test@test";
    private static final String TEST_PHONE = "84209817";
    private static final String TEST_USER = "test user";
    @Test
    public void execute_validIndexPersonList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person p = new PersonBuilder(firstPerson).withName(TEST_USER).withEmail(TEST_EMAIL)
                .withPhone(TEST_PHONE).withTags(FRIEND_TAG, OWES_MONEY_TAG).build();
        model.setPerson(firstPerson, p);

        Person expectedPerson = new PersonBuilder(firstPerson).withName(TEST_USER).withEmail(TEST_EMAIL)
                .withPhone(TEST_PHONE).withTags(OWES_MONEY_TAG).build();

        Model expectedModel = new ModelManager(getTypicalCampusConnect(), new UserPrefs());
        expectedModel.setPerson(firstPerson, expectedPerson);

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(FRIEND_TAG));
        String expectedMessage = String.format(Messages.MESSAGE_DELETE_TAG_SUCCESS,
                new Tag(FRIEND_TAG), p.getName());
        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexPersonList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, new Tag(DEFAULT_TAG));
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_personHasNoTag_throwsCommandException() {
        Index lastIndex = Index.fromOneBased(model.getFilteredPersonList().size());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(lastIndex, new Tag(DEFAULT_TAG));
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_NO_TAG);
    }

    @Test
    public void equals() {
        DeleteTagCommand deleteTagCommandA = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(DEFAULT_TAG));
        DeleteTagCommand deleteTagCommandB = new DeleteTagCommand(INDEX_SECOND_PERSON, new Tag(DEFAULT_TAG));
        DeleteTagCommand deleteTagCommandC = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(DEFAULT_TAG_SECOND));
        DeleteTagCommand deleteTagCommandD = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(DEFAULT_TAG));

        assertTrue(deleteTagCommandA.equals(deleteTagCommandD));
        assertTrue(deleteTagCommandA.equals(deleteTagCommandA));
        assertFalse(deleteTagCommandA.equals(deleteTagCommandB));
        assertFalse(deleteTagCommandA.equals(deleteTagCommandC));
    }

}
