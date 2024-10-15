package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ContactContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    public ContactContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return this.getKeywords().stream()
                .anyMatch(keyword -> person.getPhone().value.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactContainsKeywordsPredicate)) {
            return false;
        }

        ContactContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (ContactContainsKeywordsPredicate) other;
        return this.getKeywords().equals(otherNameContainsKeywordsPredicate.getKeywords());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", this.getKeywords()).toString();
    }
}
