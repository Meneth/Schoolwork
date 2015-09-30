package encapsulation;
import java.util.Date;

public class Person {
	private String givenName; // One could instead use a String[] for the full name
	private String surname;
	private String email; // One could instead use only the domain, and generate the name directly from the name
	private String personNumber;
	private Date birthday;
	private char gender;
	
	public Person() {
		givenName = null;
		surname = null;
		email = null;
		birthday = null;
		personNumber = null;
		gender = '\0'; // Null
	}
	private static void validateName(String name) {
		for(int i = 0; i < name.length(); i++) { // Simple loop through the name
			if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ') {
				throw new IllegalArgumentException("The name can only contain letters. The first invalid character was: " + name.charAt(i));
			}
		}
		String[] nameSplit = name.split(" ");
		if (nameSplit.length != 2) { // Exactly two names are required. No middle names allowed
			throw new IllegalArgumentException("The name must have exactly one first and last name separated by a space.");
		}
		if (nameSplit[0].length() < 2 || nameSplit[1].length() < 2) { // Minimum length requirements
			throw new IllegalArgumentException("The first and last names must each be at least two letters long.");
		}
	}
	public void setName(String name) {
		validateName(name);
		String[] nameSplit = name.split(" ");
		givenName = nameSplit[0];
		surname = nameSplit[1];
		email = null; // Address no longer valid
	}
	private static void validateEmail(String[] email) { // All argument exceptions
		if (email.length != 2) { // Exactly one @-symbol
			throw new IllegalArgumentException("The email must have exactly one @ sign.");
		}
		if (email[0].split("\\.").length != 2) {
			throw new IllegalArgumentException("The first part of the address must have exactly one period.");
		}
		if (email[1].split("\\.").length < 2) {
			throw new IllegalArgumentException("The domain must contain at least one period.");
		}
	}
	public void setEmail(String email) { // Country code check not implemented, 
		if (email == null) {             // as it is a very strange limitation which would even bar .com
			this.email = null;
			return; // End it here as the rest of the checks will break when trying to check a null
		}
		if (givenName == null) {
			throw new IllegalStateException("Cannot set an email before the name is set.");
		}
		String[] emailSplit = email.toLowerCase().split("@");
		for (int i = 0; i < emailSplit.length; i++) {
			System.out.println(emailSplit[i]);
		}
		validateEmail(emailSplit);
		String[] emailNameSplit = emailSplit[0].split("\\.");
		if (!givenName.toLowerCase().equals(emailNameSplit[0])) {
			throw new IllegalStateException("The first name in the address did not correspond with the user's first name.");
		}
		if (!surname.toLowerCase().equals(emailNameSplit[1])) {
			throw new IllegalStateException("The last name in the address did not correspond with the user's last name.");
		}
		this.email = email;
	}
	public void setBirthday(Date birthday) {
		Date date = new Date();       // Usually it'd work fine if declared when the person-class is created. However:
		if (birthday.before(date)) {  // Imagine a woman is pregnant, so she creates a person-class for her baby. Once it is born she tries to set its birthday
			this.birthday = birthday; // This fails, since the date is set to when the class was created, rather than when she tried to set the birthday
		} else { // I'd argue that this is a state error, not an argument error. JExericse disagrees
			throw new IllegalArgumentException("The date cannot be in the future.");
		}
	}
	private static void validateGender(char gender) {
		if (gender != 'F' && gender != 'M' && gender != '\0') {
			throw new IllegalArgumentException("The gender supplied was invalid.");
		}
	}
	public void setGender(char gender) {
		validateGender(gender);
		this.gender = gender;
	}
	private static void validateSSN(String num) {
		for (int i = 0; i < 11; i++) {
			if (!Character.isDigit(num.charAt(i))) {
				throw new IllegalArgumentException("The SSN must only consist of numbers.");
			}
		}
		if (num.length() != 11) {
			throw new IllegalArgumentException("The SSN is of the wrong length.");
		}
		int VSK1 = Character.getNumericValue(num.charAt(0)) * 3; // Not the cleanest method, but it works
		VSK1 += Character.getNumericValue(num.charAt(1)) * 7;    // Alternate implementation would be a for loop
		VSK1 += Character.getNumericValue(num.charAt(2)) * 6;
		VSK1 += Character.getNumericValue(num.charAt(3)) * 1;
		VSK1 += Character.getNumericValue(num.charAt(4)) * 8;
		VSK1 += Character.getNumericValue(num.charAt(5)) * 9;
		VSK1 += Character.getNumericValue(num.charAt(6)) * 4;
		VSK1 += Character.getNumericValue(num.charAt(7)) * 5;
		VSK1 += Character.getNumericValue(num.charAt(8)) * 2;
		if (Character.getNumericValue(num.charAt(9)) != (11 - (VSK1 % 11))) {
			throw new IllegalArgumentException("The first control number is invalid.");
		}
		int VSK2 = Character.getNumericValue(num.charAt(0)) * 5;
		VSK2 += Character.getNumericValue(num.charAt(1)) * 4;
		VSK2 += Character.getNumericValue(num.charAt(2)) * 3;
		VSK2 += Character.getNumericValue(num.charAt(3)) * 2;
		VSK2 += Character.getNumericValue(num.charAt(4)) * 7;
		VSK2 += Character.getNumericValue(num.charAt(5)) * 6;
		VSK2 += Character.getNumericValue(num.charAt(6)) * 5;
		VSK2 += Character.getNumericValue(num.charAt(7)) * 4;
		VSK2 += Character.getNumericValue(num.charAt(8)) * 3;
		VSK2 += Character.getNumericValue(num.charAt(9)) * 2;
		if (Character.getNumericValue(num.charAt(10)) != (11 - (VSK2 % 11))) {
			throw new IllegalArgumentException("The second  control number is invalid.");
		}
	}
	@SuppressWarnings("deprecation")
	public void setSSN(String num) {
		if (birthday == null) {
			throw new IllegalStateException("The SSN cannot be set before the person's birthday is set.");
		}
		if (gender == '\0') {
			throw new IllegalStateException("The SSN cannot be set before the person's gender is set.");
		}
		validateSSN(num);
		if (Integer.parseInt(num.substring(0, 2)) != birthday.getDate()) {
			throw new IllegalStateException("The SSN has the wrong day specified.");
		}
		if (Integer.parseInt(num.substring(2, 4)) != birthday.getMonth() + 1) {
			throw new IllegalStateException("The SSN has the wrong day specified.");
		}
		if (Integer.parseInt(num.substring(4, 6)) != birthday.getYear()) {
			throw new IllegalStateException("The SSN has the wrong day specified.");
		}
		if (gender == 'F') {
			if (Character.getNumericValue(num.charAt(8)) % 2 == 1) {
				throw new IllegalStateException("The 9th number must be even for women.");
			}
		} else {
			if (Character.getNumericValue(num.charAt(8)) % 2 == 0) {
				throw new IllegalStateException("The 9th number must be odd for men.");
			}
		}
		personNumber = num;
	}
	public String getName() {
		return givenName + " " + surname;
	}
	public String getEmail() {
		return email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public char getGender() {
		return gender;
	}
	public String getSocialsec() {
		return personNumber;
	}
}
