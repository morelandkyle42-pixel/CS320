//Kylescott Moreland 20 SEP 2026
//SNHU CS320
package contactservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;
    private Contact contact;

    // Start each test with a fresh service and two contacts.
    @BeforeEach
    public void setUp() {
        service = new ContactService();

        contact = new Contact(
                "123", "Kyle", "Moreland", "0123456789", "123 Main St");

        service.addContact(contact);

        service.addContact(new Contact(
                "456", "Jane", "Smith", "5551234567", "456 Oak St"));
    }

    @Test
    public void testAddContact() {
        assertSame(contact, service.getContact("123"));
        assertEquals("Jane", service.getContact("456").getFirstName());
    }

    @Test
    public void testDuplicateID() {
        Contact duplicate = new Contact(
                "123", "John", "Jones", "9876543210", "789 Elm St");

        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(duplicate);
        });

        // The original contact should not be replaced.
        assertSame(contact, service.getContact("123"));
    }

    @Test
    public void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
    }

    @Test
    public void testDeleteContact() {
        service.deleteContact("123");

        assertThrows(IllegalArgumentException.class, () -> {
            service.getContact("123");
        });

        // Deleting one contact should leave the other contact available.
        assertEquals("Jane", service.getContact("456").getFirstName());
    }

    @Test
    public void testDeleteMissingContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("999");
        });
    }

    @Test
    public void testUpdateFirstName() {
        service.updateFirstName("123", "Alex");

        assertEquals("Alex", service.getContact("123").getFirstName());
        assertEquals("Jane", service.getContact("456").getFirstName());
        assertEquals("123", service.getContact("123").getContactID());
    }

    @Test
    public void testUpdateLastName() {
        service.updateLastName("123", "Jones");

        assertEquals("Jones", service.getContact("123").getLastName());
        assertEquals("Smith", service.getContact("456").getLastName());
        assertEquals("123", service.getContact("123").getContactID());
    }

    @Test
    public void testUpdatePhone() {
        service.updatePhone("123", "9876543210");

        assertEquals("9876543210", service.getContact("123").getPhone());
        assertEquals("5551234567", service.getContact("456").getPhone());
        assertEquals("123", service.getContact("123").getContactID());
    }

    @Test
    public void testUpdateAddress() {
        service.updateAddress("123", "789 Elm St");

        assertEquals("789 Elm St", service.getContact("123").getAddress());
        assertEquals("456 Oak St", service.getContact("456").getAddress());
        assertEquals("123", service.getContact("123").getContactID());
    }

    @Test
    public void testUpdateMissingContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("999", "Alex");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("999", "Jones");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("999", "9876543210");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("999", "789 Elm St");
        });
    }

    @Test
    public void testInvalidFirstNameUpdates() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("123", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("123", "ABCDEFGHIJK");
        });

        assertEquals("Kyle", service.getContact("123").getFirstName());
    }

    @Test
    public void testInvalidLastNameUpdates() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("123", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("123", "ABCDEFGHIJK");
        });

        assertEquals("Moreland", service.getContact("123").getLastName());
    }

    @Test
    public void testInvalidPhoneUpdates() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("123", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("123", "123456789");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("123", "12345678901");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("123", "123456789A");
        });

        assertEquals("0123456789", service.getContact("123").getPhone());
    }

    @Test
    public void testInvalidAddressUpdates() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("123", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress(
                    "123", "1234567890123456789012345678901");
        });

        assertEquals("123 Main St", service.getContact("123").getAddress());
    }
}