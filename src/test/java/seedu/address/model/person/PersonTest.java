package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void hasSameEmail() {
        assertTrue(ALICE.hasSameEmail(ALICE));

        // same name, different email -> return false
        Person editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.hasSameEmail(editedAlice));
        assertTrue(editedAlice.hasSameEmail(BOB));

        // different name, same email -> return true
        Person editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY).build();
        Person aliceWithNewEmail = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_AMY).build();
        assertTrue(aliceWithNewEmail.hasSameEmail(editedBob));

        // null -> always return false
        assertFalse(ALICE.hasSameEmail(null));
    }

    @Test
    public void hasSamePhoneNumber() {
        assertTrue(ALICE.hasSamePhoneNumber(ALICE));
        assertFalse(ALICE.hasSamePhoneNumber(BOB));

        // different name, same phone number -> return true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(editedAlice.hasSamePhoneNumber(BOB));

        //same name, different phone number -> return false
        assertFalse(editedAlice.hasSamePhoneNumber(ALICE));

        //null -> always return false
        assertFalse(ALICE.hasSamePhoneNumber(null));
    }

    @Test
    public void hasDuplicateInfo() {
        assertTrue(ALICE.hasDuplicateInfo(ALICE));
        assertFalse(ALICE.hasDuplicateInfo(null));

        // Different name, phone, same email
        Person editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY).build();
        Person aliceWithNewEmail = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_AMY).build();
        assertTrue(editedBob.hasDuplicateInfo(aliceWithNewEmail));
        assertFalse(editedBob.hasDuplicateInfo(ALICE));

        // Different email, phone, same phone
        Person editedBob2 = new PersonBuilder(BOB).withPhone(VALID_PHONE_BOB).build();
        Person aliceWithNewEmail2 = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(editedBob2.hasDuplicateInfo(aliceWithNewEmail2));
        assertFalse(editedBob2.hasDuplicateInfo(ALICE));

        // Different email, name, same name
        Person editedBob3 = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        Person aliceWithNewEmail3 = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(editedBob3.hasDuplicateInfo(aliceWithNewEmail3));
        assertTrue(editedBob3.hasDuplicateInfo(BOB));

        //NULL
        assertFalse(editedBob3.hasDuplicateInfo(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hasTag() {
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(aliceCopy.hasTag(new Tag("friends")));
        assertFalse(aliceCopy.hasTag(new Tag("TEST")));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
