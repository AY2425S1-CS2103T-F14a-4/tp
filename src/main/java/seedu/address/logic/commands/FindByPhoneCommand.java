package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;;

import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose phone number
 * contains any of the argument keywords.
 */
public class FindByPhoneCommand extends SuperFindCommand {
    public static final String COMMAND_WORD = SuperFindCommand.COMMAND_WORD + PREFIX_PHONE;


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose phone numbers "
            + "contain any of the specified keywords (case-insensitive) and displays them as a list with indices.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 91112345 999 995";
    public FindByPhoneCommand(PhoneContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindByPhoneCommand)) {
            return false;
        }

        FindByPhoneCommand otherFindCommand = (FindByPhoneCommand) other;
        return this.getPredicate().equals(otherFindCommand.getPredicate());
    }
}
