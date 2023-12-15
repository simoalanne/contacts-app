import java.io.Serializable;
public class Person implements Serializable{
        private String ssn;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String address;
        private String emailAddress;
        private String message = "Infomation saved succesfully";

        public void setSsn(String ssn) {
            if (ContactValidation.isValidFinnishSSN(ssn)) {
                this.ssn = ssn;
                System.out.println(message);
            } else {
                throw new IllegalArgumentException("'ddmmyyA-xxxx'");
            }
        }

        public String getSsn() {
            return ssn;
        }

        public void setFirstName(String firstName) {
            if (ContactValidation.isValidName(firstName)) {
                this.firstName = firstName;
                System.out.println(message);
            } else {
                throw new IllegalArgumentException("Enter a valid first name");
            }
        }

        public String getFirstName() {
            return firstName;
        }

        public void setLastName(String lastName) {
            if (ContactValidation.isValidName(lastName)) {
                this.lastName = lastName;
                System.out.println(message);
            } else {
                throw new IllegalArgumentException("Enter a valid last name");
            }
        }

        public String getLastName() {
            return lastName;
        }

        public void setPhoneNumber(String phoneNumber) {
            if (ContactValidation.isValidPhoneNumber(phoneNumber)) {
                this.phoneNumber = phoneNumber;
                System.out.println(message);
            } else {
                throw new IllegalArgumentException("MAX 3 digit country code + 9 digits OR 10 numbers where the first is zero");
            }
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setEmailAddress(String emailAddress) {
            if (ContactValidation.isValidEmailAddress(emailAddress)) {
                this.emailAddress = emailAddress;
                System.out.println(message);
            } else {
                throw new IllegalArgumentException("Enter the email address in 'xx(.)xx@xx.xx'");
            }
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setAddress(String address) {
            if (ContactValidation.isValidAddress(address)) {
                this.address = address;
                System.out.println(message);
            } else {
                throw new IllegalArgumentException("Enter a valid address");
            }
        }

        public String getAddress() {
            return address;
        }

        public String toString() {
            return "SSN: " + ssn + "\n" +
            "First Name: " + firstName + "\n" +
            "Last Name " + lastName + "\n" +
            "Phone Number: " + phoneNumber + "\n" +
            "Home Address: " + address + "\n" +
            "Email Address: " + emailAddress;
        }
    }
