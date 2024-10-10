package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByContactCommand;
import seedu.address.logic.commands.FindByNameCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.ContactContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindByNameCommand() {
        FindByNameCommand expectedFindCommand =
                new FindByNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "/n Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "/n \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindByContactCommand() {
        FindByContactCommand expectedFindCommand =
                new FindByContactCommand(new ContactContainsKeywordsPredicate(
                        Arrays.asList("91234567", "995")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "/c 91234567 995", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "/c \n 91234567 \n \t 995  \t", expectedFindCommand);
    }

}
