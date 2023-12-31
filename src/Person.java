import java.io.Serializable;

/**
 * The class Represents a person with contact details including
 * a Finnish social security number, first name, last name,
 * phone number, home address, and email address.
 * It implements the Serializable interface to enable object serialization.
 */
public class Person implements Serializable {
    /** The Finnish social security number of the person. */
    private String ssn;

    /** The first name of the person. */
    private String firstName;

    /** The last name of the person. */
    private String lastName;

    /** The phone number of the person. */
    private String phoneNumber;

    /** The home address of the person. */
    private String address;

    /** The email address of the person. */
    private String emailAddress;

    /** A message indicating successful detail saving. */
    private String message = "Detail saved successfully";

    /**
     * Sets the Finnish social security number of the person.
     *
     * @param ssn The Finnish social security number to set.
     * @throws IllegalArgumentException If the SSN is invalid.
     */
    public void setSsn(final String ssn) {
        if (
            !ContactsValidation.isValidFinnishSsnFormat(ssn)
            || !ContactsValidation.isValidControlCharacter(ssn)) {
                throw new IllegalArgumentException(
                    "Invalid Finnish social security number.\n"
                    + "Accepted format: 'ddmmyyA-xxxc', where\n"
                    + "'dd' is the day, 'mm' the month, 'yy' the year\n"
                    + "and 'A-' the century ('A' for 1900 or '-' for 2000).\n"
                    + "'xxx' is 3 digits between 002-899 and 'c' is\n"
                    + "the control character. Example of a valid\n"
                    + "social security number: 010203A456R"
                );
            }

        this.ssn = ssn;
        System.out.println(message);
        return;
    }

    /**
     * Gets the Finnish social security number of the person.
     *
     * @return The Finnish social security number.
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName The first name to set.
     * @throws IllegalArgumentException If thefirst name is invalid.
     */
    public void setFirstName(final String firstName) {
        if (ContactsValidation.isValidName(firstName)) {
            this.firstName = firstName;
            System.out.println(message);
        } else {
            throw new IllegalArgumentException(
                "Invalid first name. A-ö, spaces and hyphens are accepted."
            );
        }
    }

    /**
     * Gets the first name of the person.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName The last name to set.
     * @throws IllegalArgumentException If the last name is invalid.
     */
    public void setLastName(final String lastName) {
        if (ContactsValidation.isValidName(lastName)) {
            this.lastName = lastName;
            System.out.println(message);
        } else {
            throw new IllegalArgumentException(
                "Invalid last name. A-ö, spaces and hyphens are accepted."
            );
        }
    }

    /**
     * Gets the last name of the person.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber The phone number to set.
     * @throws IllegalArgumentException If the phone number is invalid.
     */
    public void setPhoneNumber(final String phoneNumber) {
        if (ContactsValidation.isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            System.out.println(message);
        } else {
            throw new IllegalArgumentException(
                "Invalid phone number.\n"
                + "country code is optional and no spaces are accepted.\n"
                + "Phone number is minimum of 7 and maximum of 20 digits.\n"
                + "Examples: +358123456789, 0123456, +123456789123456789."
            );
        }
    }

    /**
     * Gets the phone number of the person.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the home address of the person.
     *
     * @param address The home address to set.
     * @throws IllegalArgumentException If the address is invalid.
     */
    public void setAddress(final String address) {
        if (ContactsValidation.isValidAddress(address)) {
            this.address = address;
            System.out.println(message);
        } else {
            throw new IllegalArgumentException(
                "Invalid address. A-ö, numbers, spaces, hyphens,\n"
                + "periods and commas are accepted."
            );
        }
    }

    /**
     * Gets the home address of the person.
     *
     * @return The home address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the email address of the person.
     *
     * @param emailAddress The email address to set.
     * @throws IllegalArgumentException If the email address is invalid.
     */
    public void setEmailAddress(final String emailAddress) {
        if (ContactsValidation.isValidEmailAddress(emailAddress)) {
            this.emailAddress = emailAddress;
            System.out.println(message);
        } else {
            throw new IllegalArgumentException(
                "Invalid email address. Accepted format:\n"
                + "username@example.domain or user.name@example.domain."
            );
        }
    }

    /**
     * Gets the email address of the person.
     *
     * @return The email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Returns a string representation of the person's details.
     *
     * @return A formatted string containing the person's details.
     */
    public String toString() {
        return "Social security number: " + ssn + "\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Home Address: " + address + "\n"
                + "Email Address: " + emailAddress;
    }
}
