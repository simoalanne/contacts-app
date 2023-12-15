import java.util.regex.*;

public class ContactValidation {

    private static final Pattern FINNISH_SSN_PATTERN = Pattern.compile(
    "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[0-2])\\d{2}[A-](00[2-9]|[0-8][0-9][0-9])([0-9A-FHJ-NPR-Y])$"
    );

    private static final Pattern NAME_PATTERN = Pattern.compile(
    "^[A-ZÅÄÖ][a-zåäö]{1,29}$"
    );

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(
    "^(0|\\+\\d{1,3})\\d{9}$"
    );

    /* Logic is that a contact must have an address that starts with capital letter.
    Must Also have a street number of at least one digit but can have more.*/
    private static final Pattern ADDRESS_PATTERN = Pattern.compile(
    "^$|^[A-ZÅÄÖ][a-zäåö]{2,30}\\s\\d([A-Za-z\\d\\s]{0,10})$"

    );

    /*stackoverflow.com/questions/1423195/what-is-the-actual-minimum-length-of-an-email
    stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    This forces at least 1 letter and optional .x before @. After that at least 3 chars.
    */
    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
    "^$|^([a-z](\\.[a-z]?){1,64}@([a-z]{1,10}\\.[a-z]{1,10})$"
    );

    public static boolean isValidFinnishSSN(String ssn) {
        return Pattern.matches(FINNISH_SSN_PATTERN.pattern(), ssn);
    }

    public static boolean isValidName(String name) {
        return Pattern.matches(NAME_PATTERN.pattern(), name);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches(PHONE_NUMBER_PATTERN.pattern(), phoneNumber);
    }

    public static boolean isValidEmailAddress(String emailAddress) {
        return Pattern.matches(EMAIL_ADDRESS_PATTERN.pattern(), emailAddress);
    }

    public static boolean isValidAddress(String address) {
        return Pattern.matches(ADDRESS_PATTERN.pattern(), address);
    }
}
