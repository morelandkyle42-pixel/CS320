//Kylescott Moreland 20 SEP 2026
//SNHU CS320
package contactservice;

import java.util.HashMap;

public class ContactService {

    // Store contacts using their IDs as keys.
    private final HashMap<String, Contact> contacts = new HashMap<>();

    // Add a contact if its ID is not already used.
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }

        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID already exists");
        }

        contacts.put(contact.getContactID(), contact);
    }

    // Find a contact by its ID.
    public Contact getContact(String contactID) {
        if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact ID not found");
        }

        return contacts.get(contactID);
    }

    // Delete a contact by its ID.
    public void deleteContact(String contactID) {
        getContact(contactID);
        contacts.remove(contactID);
    }

    // Update the requested field using the contact's setters.
    public void updateFirstName(String contactID, String firstName) {
        getContact(contactID).setFirstName(firstName);
    }

    public void updateLastName(String contactID, String lastName) {
        getContact(contactID).setLastName(lastName);
    }

    public void updatePhone(String contactID, String phone) {
        getContact(contactID).setPhone(phone);
    }

    public void updateAddress(String contactID, String address) {
        getContact(contactID).setAddress(address);
    }
}
