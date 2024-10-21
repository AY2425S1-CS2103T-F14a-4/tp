package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parse the delete tag command.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    private static final String MESSAGE_TAG_NOT_FOUND = "There is no tag presented in the command!";

    /**
     * Parses the given string of arguments in the context of Delete tag command.
     * and returns DeleteTagCommand object for execution.
     * @throws ParseException if user input does not conform the expected input
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE), pe);
        }

        argMultiMap.verifyNoDuplicatePrefixesFor(PREFIX_TAG);
        if (argMultiMap.getValue(PREFIX_TAG).isEmpty()) {
            throw new ParseException(MESSAGE_TAG_NOT_FOUND);
        }

        Tag target = ParserUtil.parseTag(argMultiMap.getValue(PREFIX_TAG).get());
        return new DeleteTagCommand(index, target);
    }
}
