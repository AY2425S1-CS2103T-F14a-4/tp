package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;

import seedu.address.model.person.EmailContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose email is equivalent to the specified keyword.
 * Keyword matching is case-insensitive.
 */
public class FindByEmailCommand extends SuperFindCommand {

    public static final String COMMAND_WORD = SuperFindCommand.COMMAND_WORD + PREFIX_EMAIL;


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose email "
            + "is the same as the specified keywords (case-insensitive) and displays them as a list with indices.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alexyeoh@gmail.com";

    public FindByEmailCommand(EmailContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindByEmailCommand)) {
            return false;
        }

        FindByEmailCommand otherFindCommand = (FindByEmailCommand) other;
        return this.getPredicate().equals(otherFindCommand.getPredicate());
    }
}
