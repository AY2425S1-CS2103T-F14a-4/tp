package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_TEMPLATE;

import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindByTagCommand extends SuperFindCommand {
    public static final String COMMAND_WORD = SuperFindCommand.COMMAND_WORD + PREFIX_TAG_TEMPLATE;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags "
            + "contain any of the specified keywords (case-insensitive) and displays them as a list with indices.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2100_classmate";

    public FindByTagCommand(TagContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindByTagCommand)) {
            return false;
        }

        FindByTagCommand otherFindCommand = (FindByTagCommand) other;
        return this.getPredicate().equals(otherFindCommand.getPredicate());
    }
}
