package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class AddTagCommandParser implements Parser<AddTagCommand> {
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE), pe);
        }

        AddTagCommand.AddTagDescriptor addTagDescriptor = new AddTagCommand.AddTagDescriptor();

        parseTagstoAdd(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(addTagDescriptor::addTags);

        return new AddTagCommand(index, addTagDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagstoAdd(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
