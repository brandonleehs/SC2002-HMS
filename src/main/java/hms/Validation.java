package hms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Refactor email validation, move print out of validation
public final class Validation {
	private Validation() {
	}

	public static boolean validateEmailAddress(String emailAddress) {
		// A lenient regex pattern for a traditional email address {local}@{domain}
		// Local part allows only {_, .} special characters.
		String emailAddressPattern = "^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
		Pattern pattern = Pattern.compile(emailAddressPattern);
		Matcher matcher = pattern.matcher(emailAddress);
		if (matcher.matches()) {
			System.out.println("Email address is valid.");
			return true;
		}
		System.out.println("Email address is invalid.");
		return false;

	}

	public static boolean validatePassword(String password) {
		boolean valid = true;

		if (password.length() < 8) {
			valid = false;
			System.out.println("\u2717 Password must be at least 8 characters long.");
		} else {
			System.out.println("\u2713 Password contains at least 8 characters long.");
		}
		if (!Pattern.compile(".*[A-Z].*").matcher(password).matches()) {
			valid = false;
			System.out.println("\u2717 Password must contain at least one uppercase letter.");
		} else {
			System.out.println("\u2713 Password contains at least one uppercase letter.");
		}
		if (!Pattern.compile(".*[a-z].*").matcher(password).matches()) {
			valid = false;
			System.out.println("\u2717 Password must contain at least one lowercase letter.");
		} else {
			System.out.println("\u2713 Password contains at least one lowercase letter.");
		}
		if (!Pattern.compile(".*\\d.*").matcher(password).matches()) {
			valid = false;
			System.out.println("\u2717 Password must contain at least one digit.");
		} else {
			System.out.println("\u2713 Password contains at least one digit.");
		}
		if (!Pattern.compile(".*[!@#$%^&*].*").matcher(password).matches()) {
			valid = false;
			System.out.println("\u2717 Password must contain at least one special character (!, @, #, $, %, ^, &, *).");
		} else {
			System.out.println("\u2713 Password contains at least one special character (!, @, #, $, %, ^, &, *).");
		}
		if (Pattern.compile("\\s").matcher(password).find()) {
			valid = false;
			System.out.println("\u2717 Password must not contain any spaces or tabs.");
		} else {
			System.out.println("\u2713 Password does not contain any spaces or tabs.");
		}

		if (valid) {
			System.out.println("\\u2713 Password is valid.");
		} else {
			System.out.println("\u2717 Password is invalid.");
		}
		return valid;
	}
}
