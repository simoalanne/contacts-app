import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A utility class for serializing and
 * deserializing contacts to and from a file.
 * This class provides methods to load contacts from
 * a serialized file into the ContactsManager and saves
 * contacts from the ContactsManager to a serialized file.
 */
public class ContactsSerializer {
    /** The default file name for saving and loading contacts. */
    private static String fileName = "SavedContacts.ser";

    /** The file object representing the contact serialization file. */
    private static File file = new File(fileName);

    /**
     * If file exits, this method loads contacts from the serialized file
     * and adds them to the ContactsManager. If the exists but is
     * corrupted, it attemps to delete the file and exits the program.
     */
    public static void loadContactsFromFile() {
        if (file.exists()) {
            try (
                ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    /*
                     * Loading Person objects from the file
                     * till the end of file exception occurs.
                     */
                    try {
                        Person person = (Person) ois.readObject();
                        ContactsManager.getContacts().add(person);
                    } catch (EOFException e) {
                        break;
                    }
                }
                System.out.println(
                    "Loaded " + ContactsManager.getContactsSize()
                    + " contact/contacts from: " + fileName
                );
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(
                    "Sorry but unfortunately " + fileName + " is corrupted\n"
                    + "and all the saved contacts are lost.\n"
                    + "Trying to delete the corrupted file now, please wait..."
                );
                try {
                    /*
                     * Attempts to run the garbage collector to
                     * free up system resources before file deletion.
                     */
                    System.gc();
                    // Sleeping for 3000ms so that gc finishes it's operation.
                    final int sleepDuration = 3000;
                    Thread.sleep(sleepDuration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                deleteFile();
                System.out.println("Please restart the program.");
                System.exit(1);
            }
        }
    }

    /**
     * Saves contacts from ContactsManager to the serialized file.
     * If there are no contacts to save and the file exists, file is deleted.
     */
    public static void saveContactsToFile() {
        if (ContactsManager.getContacts().isEmpty() && file.exists()) {
            System.out.println("No contacts to save.");
            deleteFile();
        }

        if (!ContactsManager.getContacts().isEmpty()) {
            try (
                ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(file))) {

                // Attempts to write the objects into the serialized file.
                for (Person person : ContactsManager.getContacts()) {
                    oos.writeObject(person);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to save contact/contacts to\n"
                 + fileName + ". Please try again."
                );
            }
            System.out.println(
                "Saved " + ContactsManager.getContactsSize()
                + " contact/contacts to: " + fileName
            );
        }
    }

    /**
     * Attempts to delete the contact serialization file.
     * Displays a message based on the success or failure of the deletion.
     */
    private static void deleteFile() {
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println(
                "Failed to delete the file\n."
                + "Please try again or delete the file manually."
            );
        }
    }
}
