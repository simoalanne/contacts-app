import java.io.*;
public class ContactSerializer {

    public static void loadContactsFromfile(String fileName) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Person person = (Person) ois.readObject();
                    ContactsApp.getContacts().add(person);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + ContactsApp.getContactsSize() + " contacts from: " + fileName);
    }

    public static void saveContactsToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Person person : ContactsApp.getContacts()) {
                oos.writeObject(person);
            }
            System.out.println("Saved " + ContactsApp.getContactsSize() + " contacts to: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}