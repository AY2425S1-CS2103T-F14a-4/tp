package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_TEMPLATE;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Add new tag(s) for an existing person
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Add tag(s) for the person identified "
            + "by the index number used in the displayed person list "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG_TEMPLATE + "TAG]...\n";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Tag added: %1$s";
    public static final String MESSAGE_NOT_ADD = "At least one tag to be provided.";

    public static final String MESSAGE_CONTAINS_DUPLICATE_TAG = "%1$s already contains some "
            + "tags you want to add";
    private final Index index;
    private final Set<Tag> tagSet;

    /**
     * Constructs add tag command object.
     */
    public AddTagCommand(Index index, Set<Tag> tagSet) {
        requireNonNull(index);
        requireNonNull(tagSet);

        this.index = index;
        this.tagSet = tagSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null;
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.containsDuplicateTag(this.tagSet)) {
            throw new CommandException(String.format(MESSAGE_CONTAINS_DUPLICATE_TAG,
                    Messages.format(personToEdit)));
        }

        model.addPersonTags(personToEdit, this.tagSet);
        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, Messages.format(personToEdit)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        AddTagCommand otherAddTagCommand = (AddTagCommand) other;
        return index.equals(otherAddTagCommand.index) && this.tagSet.equals(otherAddTagCommand.tagSet);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("tag list to add:", tagSet)
                .toString();
    }
}
