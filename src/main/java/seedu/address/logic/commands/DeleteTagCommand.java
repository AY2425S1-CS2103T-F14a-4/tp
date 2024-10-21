package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Implements command to delete a tag from a person
 * format: deletetag [INDEX] t/[TAG NAME]
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deletetag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes tag from the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) [TAG NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 " + " hello_world";

    private final Index targetIndex;
    private final Tag targetTag;

    /**
     * Constructs new DeleteTag command.
     * @param targetIndex the index of the person in the list.
     * @param target the tag the user wants to delete.
     */
    public DeleteTagCommand(Index targetIndex, Tag target) {
        this.targetIndex = targetIndex;
        this.targetTag = target;
    }

    /**
     * Executes the command and delete a tag from a specific person.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        if (!personToEdit.hasTag(this.targetTag)) {
            throw new CommandException(Messages.MESSAGE_NO_TAG);
        }

        model.deletePersonTag(personToEdit, targetTag);
        return new CommandResult(String.format(Messages.MESSAGE_DELETE_TAG_SUCCESS,
                this.targetTag, personToEdit.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        DeleteTagCommand otherDeleteTagCommand = (DeleteTagCommand) other;
        return targetIndex.equals(otherDeleteTagCommand.targetIndex)
                && targetTag.equals(otherDeleteTagCommand.targetTag);
    }
}
