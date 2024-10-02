package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Likes to swim.";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Remark command not implemented yet";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    private final Index index;
    private final String remark;

    /**
     * Represents RemarkCommand class.
     *
     * @param index index of contact.
     * @param remark content of remark.
     */
    public RemarkCommand(Index index, String remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    /**
     * Executes command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return command results.
     * @throws CommandException when execution fails.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS,
                index.getOneBased(), remark));
    }

    /**
     * Checks equality between two RemarkCommand.
     *
     * @param other another object
     * @return true if equal false otherwise
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }
        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
