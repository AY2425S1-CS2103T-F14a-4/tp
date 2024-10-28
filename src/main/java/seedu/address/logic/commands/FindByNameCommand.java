package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindByNameCommand extends SuperFindCommand {
    public static final String COMMAND_WORD = SuperFindCommand.COMMAND_WORD + PREFIX_PHONE;


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names "
            + "contain any of the specified keywords (case-insensitive) and displays them as a list with indices.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public FindByNameCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindByNameCommand)) {
            return false;
        }

        FindByNameCommand otherFindCommand = (FindByNameCommand) other;
        return this.getPredicate().equals(otherFindCommand.getPredicate());
    }
}
