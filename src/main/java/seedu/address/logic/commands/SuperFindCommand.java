package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ContainsKeywordsPredicate;

/**
 * Abstract Find command to house the other find command classes.
 */
public class SuperFindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names, phone numbers, emails "
            + "or tags contain any of the specified keywords (case-insensitive) and displays"
            + "them as a list with indices.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example:\n"
            + COMMAND_WORD + PREFIX_NAME + "alice bob charlie\n"
            + COMMAND_WORD + PREFIX_EMAIL + "bob@gmail.com\n"
            + COMMAND_WORD + PREFIX_PHONE + "12345678\n"
            + COMMAND_WORD + PREFIX_MODULE_TAG + "CS2100";

    public static final String MESSAGE_NO_PERSONS_FOUND = "No persons found!";

    private final ContainsKeywordsPredicate predicate;

    public SuperFindCommand(ContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    protected ContainsKeywordsPredicate getPredicate() {
        return this.predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(this.predicate);

        // if the result find list is empty
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_PERSONS_FOUND);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof SuperFindCommand)) {
            return false;
        }

        SuperFindCommand otherCommand = (SuperFindCommand) other;
        return this.predicate.equals(otherCommand.predicate);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", this.predicate)
                .toString();
    }
}
