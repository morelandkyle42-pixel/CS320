//Kylescott Moreland 20 SEP 2026
//SNHU CS320
package contactservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactTest {

    // Verify that a valid contact stores all fields correctly.
    @Test
    public void testCreateContact() {
        Contact contact = new Contact(
                "123", "Kyle", "Moreland", "0123456789", "123 Main St");

        assertEquals("123", contact.getContactID());
        assertEquals("Kyle", contact.getFirstName());
        assertEquals("Moreland", contact.getLastName());
        assertEquals("0123456789", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    // Values exactly at the maximum lengths should be accepted.
    @Test
    public void testMaximumLengths() {
        Contact contact = new Contact(
                "1234567890", "ABCDEFGHIJ", "ABCDEFGHIJ",
                "0123456789", "123456789012345678901234567890");

        assertEquals("1234567890", contact.getContactID());
        assertEquals("ABCDEFGHIJ", contact.getFirstName());
        assertEquals("ABCDEFGHIJ", contact.getLastName());
        assertEquals("123456789012345678901234567890",
                contact.getAddress());
    }

    @Test
    public void testInvalidContactID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "Kyle", "Moreland",
                    "0123456789", "123 Main St");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "Kyle", "Moreland",
                    "0123456789", "123 Main St");
        });
    }

    @Test
    public void testInvalidFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", null, "Moreland",
                    "0123456789", "123 Main St");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "ABCDEFGHIJK", "Moreland",
                    "0123456789", "123 Main St");
        });
    }

    @Test
    public void testInvalidLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Kyle", null,
                    "0123456789", "123 Main St");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Kyle", "ABCDEFGHIJK",
                    "0123456789", "123 Main St");
        });
    }

    @Test
    public void testInvalidPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Kyle", "Moreland",
                    null, "123 Main St");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Kyle", "Moreland",
                    "123456789", "123 Main St");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Kyle", "Moreland",
                    "12345678901", "123 Main St");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Kyle", "Moreland",
                    "123456789A", "123 Main St");
        });
    }

    @Test
    public void testInvalidAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Kyle", "Moreland",
                    "0123456789", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Kyle", "Moreland",
                    "0123456789", "1234567890123456789012345678901");
        });
    }

    // Updates at the allowed limits should work without changing the ID.
    @Test
    public void testValidUpdates() {
        Contact contact = new Contact(
                "123", "Kyle", "Moreland", "0123456789", "123 Main St");

        contact.setFirstName("ABCDEFGHIJ");
        contact.setLastName("KLMNOPQRST");
        contact.setPhone("9876543210");
        contact.setAddress("123456789012345678901234567890");

        assertEquals("ABCDEFGHIJ", contact.getFirstName());
        assertEquals("KLMNOPQRST", contact.getLastName());
        assertEquals("9876543210", contact.getPhone());
        assertEquals("123456789012345678901234567890",
                contact.getAddress());
        assertEquals("123", contact.getContactID());
    }

    // Invalid updates should leave the original values unchanged.
    @Test
    public void testInvalidFirstNameUpdates() {
        Contact contact = new Contact(
                "123", "Kyle", "Moreland", "0123456789", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("ABCDEFGHIJK");
        });

        assertEquals("Kyle", contact.getFirstName());
    }

    @Test
    public void testInvalidLastNameUpdates() {
        Contact contact = new Contact(
                "123", "Kyle", "Moreland", "0123456789", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("ABCDEFGHIJK");
        });

        assertEquals("Moreland", contact.getLastName());
    }

    @Test
    public void testInvalidPhoneUpdates() {
        Contact contact = new Contact(
                "123", "Kyle", "Moreland", "0123456789", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("123456789");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("12345678901");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("123456789A");
        });

        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    public void testInvalidAddressUpdates() {
        Contact contact = new Contact(
                "123", "Kyle", "Moreland", "0123456789", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress("1234567890123456789012345678901");
        });

        assertEquals("123 Main St", contact.getAddress());
    }
}
