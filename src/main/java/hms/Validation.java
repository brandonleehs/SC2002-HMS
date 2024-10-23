package hms;

import java.util.regex.Pattern;

public final class Validation {
	private Validation() {
	}

	// TBD
	public static boolean validateEmailAddress(String emailAddress) {
		return true;
	}

	public static boolean validatePassword(String password) {
		boolean valid = true;

		if (password.length() < 8) {
			valid = false;
			System.out.println("Password must be at least 8 characters long.");
		}
		if (!Pattern.compile(".*[A-Z].*").matcher(password).matches()) {
			valid = false;
			System.out.println("Password must contain at least one uppercase letter.");
		}
		if (!Pattern.compile(".*[a-z].*").matcher(password).matches()) {
			valid = false;
			System.out.println("Password must contain at least one lowercase letter.");
		}
		if (!Pattern.compile(".*\\d.*").matcher(password).matches()) {
			valid = false;
			System.out.println("Password must contain at least one digit.");
		}
		if (!Pattern.compile(".*[!@#$%^&*].*").matcher(password).matches()) {
			valid = false;
			System.out.println("Password must contain at least one special character (!, @, #, $, %, ^, &, *).");
		}
		if (Pattern.compile("\\s").matcher(password).find()) {
			valid = false;
			System.out.println("Password must not contain any spaces or tabs.");
		}

		if (valid) {
			System.out.println("Password is valid.");
		} else {
			System.out.println("Password is invalid.");
		}
		return valid;
	}
}
