import java.util.ArrayList;
import java.io.Console;

class ContactsApp {
    private static Console c = System.console();
    private static ArrayList<Person> contacts = new ArrayList<>();
    private static String fileName = "savedContacts.ser";

    public static void main(String[] args) {
        ContactSerializer.loadContactsFromfile(fileName);
        manageContacts();
        ContactSerializer.saveContactsToFile(fileName);
    }

    public static void manageContacts() {
        boolean appRunning = true;

        while(appRunning) {
            System.out.println(
            "What would you like to do?\n" +
            "Type 1 to create a new contact\n" +
            "Type 2 to read all the currently saved contacts.\n" +
            "Type 3 to update specific part or all parts on an existing contact\n" +
            "Type 4 to delete an existing contact\n" +
            "Type 5 to delete all contacts\n" +
            "Type 6 to save and exit the program"
            );

            switch (c.readLine()) {
                case "1":
                    createNewContact();
                    break;
                case "2":
                    readContacts();
                    break;
                case "3":
                    updateContact(checkForSsn());
                    break;
                case "4":
                    deleteContact(checkForSsn());
                    break;
                case "5":
                    deleteAllContacts();
                    break;
                case "6":
                    appRunning = false;
                    break;
                default:
                    System.out.println("Enter a valid choise (1-5)");
            }
        }
    }

    public static void createNewContact() {
        Person person = new Person();
        do {
            try {
                boolean contactExists = false;
                System.out.println("Enter a Finnish social security number:");
                String ssn = c.readLine();

                for (Person contact : contacts) {
                    if (ssn.equals(contact.getSsn())) {
                        System.out.println("Contact with this SSN already exists");
                        contactExists = true;
                        break;
                    }
                }

                if (!contactExists) {
                    person.setSsn(ssn);
                    updateAllInformation(person);
                    contacts.add(person);
                    break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void updateContact(Person person) {
        if (person == null) {
            System.out.println("No person found with this ssn. Please search with a valid ssn.");
            return;
        }

        System.out.println(
        "Type 1 to update first name\n" +
        "Type 2 to update last name\n" +
        "Type 3 to update phone number\n" +
        "Type 4 to update address\n" +
        "Type 5 to update email address\n" +
        "Type 6 to update all of the above\n" +
        "Type 7 to go back to previous selection. Your changes will be saved."
        );

        switch (c.readLine().toLowerCase()) {
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
                System.out.println("Changes saved succesfully");
                return;
            default :
                System.out.println("Enter a valid choise (1-7)");
        }
    }

    public static void updateFirstName(Person person) {
        do {
            try {
            System.out.println("Enter first name:");
            person.setFirstName(c.readLine());
            break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void updateLastName(Person person) {
        do {
            try {
            System.out.println("Enter last name:");
            person.setLastName(c.readLine());
            break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void updatePhoneNumber(Person person) {
        do {
            try {
            System.out.println("Enter a valid phone number either with or without country code");
            person.setPhoneNumber(c.readLine());
            break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void updateAddress(Person person) {
        do {
            try {
            System.out.println("Enter address: (optional)");
            person.setAddress(c.readLine());
            break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void updateEmailAddress(Person person) {
        do {
            try {
            System.out.println("Enter email address: (optional)");
            person.setEmailAddress(c.readLine().toLowerCase());
            break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        System.out.println("Contact saved succesfully");
    }

    public static void updateAllInformation(Person person) {
        updateFirstName(person);
        updateLastName(person);
        updatePhoneNumber(person);
        updateAddress(person);
        updateEmailAddress(person);
        return;
    }

    public static void deleteContact(Person person) {
        if (person == null) {
            System.out.println("No contact found with this ssn");
            return;
        }
        contacts.remove(person);
        System.out.println("Contact deleted succesfully");
    }

    public static void deleteAllContacts() {
        if (person == null) {
            System.out.println("Nothing to delete");
        }
        System.out.println(
        "Are you absolutely sure that you want to delete all the contacts?\n" +
        "This operation is irreversible\n" +
        "Type yes to proceed or no to cancel"
        );

        while (true) {
            switch (c.readLine().toLowerCase()) {
                case "yes" :
                    contacts.clear();
                    System.out.println("All contacts deleted succefully");
                    return;
                case "no" :
                    System.out.println("Nothing deleted");
                    return;
                default :
                    System.out.println("Type yes or no");
            }
        }
    }

    public static void readContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to read!");
            return;
        }

        System.out.println("Contacts:");
        for (Person contact : contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }

    public static Person checkForSsn() {

        if (contacts.isEmpty()) {
            System.out.println("There are currently no saved contacts. Please create a contact first.");
        }

        System.out.println("Enter the social security number of the contact you wish to modify");
        String findSsn = c.readLine();
        for (Person contact: contacts) {
            if (contact.getSsn().equals(findSsn)) {
                return contact;
            }
        }
        return null;
    }

    public static ArrayList<Person> getContacts() {
        return contacts;
    }

    public static int getContactsSize() {
        return contacts.size();
    }
}
