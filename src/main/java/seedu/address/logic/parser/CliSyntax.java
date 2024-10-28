package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_DEFAULT_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CCA_TAG = new Prefix("tc/");
    public static final Prefix PREFIX_MODULE_TAG = new Prefix ("tm/");
    public static final Prefix PREFIX_OTHERS_TAG = new Prefix("to/");

    // temporary Prefix class until the Tag command has been implemented
    public static final Prefix PREFIX_TAG_TEMPLATE = new Prefix("t?/");
}
