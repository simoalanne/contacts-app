import java.io.Console;

/**
 * The main class for the ContactsApp application.
 * This class manages the execution of the ContactsApp.
 */
public class ContactsApp {
    /** The console object for reading user input. */
    private static Console c = System.console();

    /** A flag indicating whether the application is running. */
    private static boolean appRunning = true;

    /**
     * The main entry point for the ContactsApp application.
     * Invokes methods within the class and in other classes to
     * load, manage, and save contacts. Contacts are loaded if available,
     * then managed, and finally, saved to a file if there are any.
     *
     * @param args The command line arguments (not used in this application).
     * @author Simo Alanne
     */
    public static void main(final String[] args) {
        ContactsSerializer.loadContactsFromFile();
        manageContacts();
        ContactsSerializer.saveContactsToFile();
    }
    /**
     * Manages the contacts app by providing a Command Line Interface (CLI)
     * that depends on contacts amount.
     * If there are no contacts the simplified CLI is chosen.
     * If at least one contact exists then the full CLI is chosen.
     */
    public static void manageContacts() {
        System.out.println("Hello and welcome to the contacts app!");
        while (appRunning) {
            if (ContactsManager.getContacts().isEmpty()) {
                System.out.println("No contacts available.");
                menuWithoutContacts();
            } else {
                menuWithContacts();
            }
        }
    }

    /**
     * Displays the main menu when at least one contact is available.
     * It provides options to create, read, update and
     * delete contacts, and to save and exit the program.
     */
    public static void menuWithContacts() {
        System.out.println(
            "What would you like to do?\n"
            + "Type 1 to create a new contact\n"
            + "Type 2 to read contacts\n"
            + "Type 3 to edit contacts\n"
            + "Type 4 to delete contacts\n"
            + "Type 5 to save and exit the program"
        );

        switch (c.readLine()) {
            case "1":
                ContactsManager.createNewContact();
                break;
            case "2":
                ContactsManager.readContacts();
                break;
            case "3":
                ContactsManager.updateContact(ContactsManager.checkForSsn());
                break;
            case "4":
                ContactsManager.deleteContacts();
                break;
            case "5":
                appRunning = false;
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    /**
     * Displays the main menu when no contacts are available.
     * It provides options to create a new contact
     * and to save and exit the program.
     */
    public static void menuWithoutContacts() {
        System.out.println(
            "What would you like to do?\n"
            + "Type 1 to create a new contact\n"
            + "Type 2 to save and exit the program"
        );

        switch (c.readLine()) {
            case "1":
                ContactsManager.createNewContact();
                break;
            case "2":
                appRunning = false;
                System.out.println("Exiting program...");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
