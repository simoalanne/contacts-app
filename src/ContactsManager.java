import java.util.ArrayList;
import java.io.Console;

/**
 * A class responsible for managing contacts.
 * It provides methods to create, update, delete, and read contacts.
 */
public class ContactsManager {
    /** The console object for reading user input. */
    private static Console c = System.console();
    /** The ArrayList for storing the contacts. */
    private static ArrayList<Person> contacts = new ArrayList<>();

    /**
     * Creates a new contact by creating a new Person object.
     * Sets the social security number of the contact and then invokes
     * a second method to set the rest of the details.
     */
    public static void createNewContact() {
        Person person = new Person();
        do {
            try {
                boolean contactExists = false;
                System.out.println("Enter a Finnish social security number:");
                String ssn = c.readLine();

                // Social security
                for (Person contact : contacts) {
                    if (ssn.equals(contact.getSsn())) {
                        System.out.println(
                            "Contact with this social"
                            + " security number already exists."
                        );
                        contactExists = true;
                        break;
                    }
                }

                if (!contactExists) {
                    person.setSsn(ssn);
                    updateAllInformation(person);
                    contacts.add(person);
                    System.out.println("Contact saved successfully");
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Is used in updating the specified contact by providing a menu to modify
     * a chosen detail or all details of the contact.
     *
     * @param person The contact to be updated.
     */
    public static void updateContact(final Person person) {
        if (person == null) {
            return;
        }

        while (true) {
            System.out.println(
            "Type 1 to update first name\n"
            + "Type 2 to update last name\n"
            + "Type 3 to update phone number\n"
            + "Type 4 to update address\n"
            + "Type 5 to update email address\n"
            + "Type 6 to update all of the above\n"
            + "Type 7 to go back to the main menu"
            );

            switch (c.readLine()) {
                case "1" :
                    updateFirstName(person);
                    break;
                case "2" :
                    updateLastName(person);
                    break;
                case "3":
                    updatePhoneNumber(person);
                    break;
                case "4":
                    updateAddress(person);
                    break;
                case "5":
                    updateEmailAddress(person);
                    break;
                case "6" :
                    updateAllInformation(person);
                    break;
                case "7" :
                    return;
                default :
                    System.out.println("Invalid choice");
            }
        }
    }

    /**
     * Updates the first name of the specified contact.
     *
     * @param person The contact to be updated.
     */
    public static void updateFirstName(final Person person) {
        do {
            try {
                System.out.println("Enter a first name:");
                person.setFirstName(c.readLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Updates the last name of the specified contact.
     *
     * @param person The contact to be updated.
     */
    public static void updateLastName(final Person person) {
        do {
            try {
                System.out.println("Enter a last name:");
                person.setLastName(c.readLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Updates the phone number of the specified contact.
     *
     * @param person The contact to be updated.
     */
    public static void updatePhoneNumber(final Person person) {
        do {
            try {
                System.out.println("Enter a phone number:");
                person.setPhoneNumber(c.readLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Updates the address of the specified contact.
     *
     * @param person The contact to be updated.
     */
    public static void updateAddress(final Person person) {
        do {
            try {
                System.out.println("Enter an address: (optional)");
                person.setAddress(c.readLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Updates the email address of the specified contact.
     *
     * @param person The contact to be updated.
     */
    public static void updateEmailAddress(final Person person) {
        do {
            try {
                System.out.println("Enter an email address: (optional)");
                person.setEmailAddress(c.readLine().toLowerCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Updates all details except the social security
     * number of the specified contact.
     *
     * @param person The contact to be updated.
     */
    public static void updateAllInformation(final Person person) {
        updateFirstName(person);
        updateLastName(person);
        updatePhoneNumber(person);
        updateAddress(person);
        updateEmailAddress(person);
    }

    /**
     * Deletes specific contact or
     * all contacts based on user input.
     */
    public static void deleteContacts() {
        while (true) {
            if (contacts.isEmpty()) {
                System.out.println(
                    "No contacts left.\n"
                    + "Returning to the main menu."
                    );
                return;
            }
            System.out.println(
                "Type 1 to delete contact by its SSN:\n"
                + "Type 2 to delete all contacts:\n"
                + "Type 3 to go back to the main menu:"
            );
            switch (c.readLine()) {
                case "1" :
                    Person person = checkForSsn();
                    if (person != null) {
                        contacts.remove(person);
                        System.out.println("Contact deleted successfully.");
                    }
                    break;
                case "2" :
                    contacts.clear();
                    System.out.println(
                        "All contacts deleted successfully.\n"
                         + "returning to the main menu."
                    );
                    return;
                case "3" :
                    return;
                default :
                    System.out.println("Invalid choice");
            }
        }
    }

    /**
     * Reads and displays all contacts that
     * are stored in the contacts ArrayList.
     */
    public static void readContacts() {
        System.out.println("Contacts:");
        for (Person contact : contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }

    /**
     * Asks for a social security number via the console and
     * then checks if it can find a contact that has that.
     *
     * @return The contact if found, otherwise null.
     */
    public static Person checkForSsn() {
        System.out.println(
            "Enter the social security number\n"
            + "of the contact you wish to modify:"
        );
        String findSsn = c.readLine();
        for (Person contact: contacts) {
            if (contact.getSsn().equals(findSsn)) {
                System.out.println("Found a contact.");
                return contact;
            }
        }
        System.out.println(
            "No contact found with this social security number.\n"
            + "Returning to the previous selection..."
        );
        return null;
    }

    /**
     * Gets the ArrayList of contacts.
     *
     * @return The ArrayList of contacts.
     */
    public static ArrayList<Person> getContacts() {
        return contacts;
    }

    /**
     * Gets the number of contacts in the
     * contacts ArrayList.
     *
     * @return The number of contacts.
     */
    public static int getContactsSize() {
        return contacts.size();
    }
}
