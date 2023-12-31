import java.util.regex.Pattern;

/**
 * A utility class for validating contact details such as
 * social security numbers, names, phone numbers, addresses,
 * and email addresses based on specific regex patterns.
 */
public class ContactsValidation {
    /** The pattern for validating Finnish social security numbers. */
    private static final Pattern FINNISH_SSN_PATTERN = Pattern.compile(
        // This follows the rules applying to finnish social security numbers.
        "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[0-2])\\d{2}[A-]"
        + "(00[2-9]|[0-8][0-9][0-9])([0-9A-FHJ-NPR-Y])$"
    );

    /** The pattern for validating names. */
    private static final Pattern NAME_PATTERN = Pattern.compile(
        // This allows alphabets, spaces and hyphens.
        "^[A-ZÅÄÖa-zåäö \\-]+$"
    );

    /** The pattern for validating phone numbers. */
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(
        // This allows optional country code followed by 7 to 20 digits.
        "^(\\+\\d{1,3})?\\d{7,20}$"
    );

    /** The pattern for validating addresses. */
    private static final Pattern ADDRESS_PATTERN = Pattern.compile(
        // This allows blank inputs or typical characters found in addresses.
        "^$|^[A-Z-ÅÄÖa-zäåö\\d ,.-]+$"
    );

    /** The pattern for validating email addresses. */
    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
        // This allows blank inputs or alternatively standard email formats.
        "^$|^([a-z0-9-]+(\\.[a-z0-9-]+)?){1,64}@([a-z0-9-]+\\.[a-z0-9-]+)$"
    );

    /**
     * Validates whether the given string has a valid Finnish SSN format.
     *
     * @param ssn The social security number to validate.
     * @return {@code true} if valid, {@code false} otherwise.
     */
    public static boolean isValidFinnishSsnFormat(final String ssn) {
        return Pattern.matches(FINNISH_SSN_PATTERN.pattern(), ssn);
    }

    /**
     * Validates whether the given string's last character is valid.
     * This validation is based on specific algortihm where the last
     * character (control character) is chosen by dividing the first
     * 9 or 8 numbers from the string (social security number) with the
     * amount of possible control characters and counting the modulo
     * of that operation. If the control character in the social
     * security number matches the control character of algorithm
     * then the ssn is valid.
     *
     * Note that this method should be used in combination with the
     * {@link #isValidFinnishSsnFormat(String)} to first ensure that
     * the social security number has a valid format. This is done to
     * prevent exceptions from being thrown when the string can't be
     * parsed correctly or when the string is too short or too long.
     * @param ssn The social security number to validate.
     * @return {@code true} if valid {@code false} otherwise
     * @throws NumberFormatException if the SSN cannot be parsed correctly.
     * @throws IndexOutOfBoundsException if the SSN is not on correct format.
     */
    public static boolean isValidControlCharacter(final String ssn) {
        final int ssnDateDigitsEndIndex = 6;
        final int ssnNextDigitsStartIndex = 7;
        final int ssnDigits = Integer.parseInt(
            (ssn.charAt(0) == '0')
            ? ssn.substring(1, ssnDateDigitsEndIndex)
            + ssn.substring(ssnNextDigitsStartIndex, ssn.length() - 1)
            : ssn.substring(0, ssnDateDigitsEndIndex)
            + ssn.substring(ssnNextDigitsStartIndex, ssn.length() - 1)
        );
        final char ssnControlCharacter = ssn.charAt(ssn.length() - 1);
        final char[] controlCharacters = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'H', 'J', 'K', 'L',
            'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'
        };
        final int divider = controlCharacters.length;
        return ssnControlCharacter == controlCharacters[ssnDigits % divider];
    }

    /**
     * Validates whether the given string is a valid name.
     *
     * @param name The name to validate.
     * @return {@code true} if valid, {@code false} otherwise.
     */
    public static boolean isValidName(final String name) {
        return Pattern.matches(NAME_PATTERN.pattern(), name);
    }

    /**
     * Validates whether the given string is a valid phone number.
     *
     * @param phoneNumber The phone number to validate.
     * @return {@code true} if valid, {@code false} otherwise.
     */
    public static boolean isValidPhoneNumber(final String phoneNumber) {
        return Pattern.matches(PHONE_NUMBER_PATTERN.pattern(), phoneNumber);
    }

    /**
     * Validates whether the given string is a valid address.
     *
     * @param address The address to validate.
     * @return {@code true} if valid, {@code false} otherwise.
     */
    public static boolean isValidAddress(final String address) {
        return Pattern.matches(ADDRESS_PATTERN.pattern(), address);
    }

    /**
     * Validates whether the given string is a valid email address.
     *
     * @param emailAddress The email address to validate.
     * @return {@code true} if valid, {@code false} otherwise.
     */
    public static boolean isValidEmailAddress(final String emailAddress) {
        return Pattern.matches(EMAIL_ADDRESS_PATTERN.pattern(), emailAddress);
    }
}
