package hms.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for validating email addresses and passwords based on
 * specific criteria. This class cannot be instantiated.
 */
public final class Validation {
	private Validation() {
	}

	/**
	 * Validates an email address based on a specified pattern.
	 * 
	 * <p>
	 * The pattern checks for a traditional email format with a local part followed
	 * by the "@" symbol and a domain part: {@code {local}@{domain}}. The local part
	 * of the email address only allows alphanumeric characters, underscores
	 * ({@code _}), and dots ({@code .}) as special characters. The domain part must
	 * follow a valid structure with alphanumeric characters and optional hyphens
	 * ({@code -}) or dots ({@code .}).
	 * </p>
	 * 
	 * @param emailAddress the email address to validate
	 * @return {@code true} if the email address is valid; {@code false} otherwise
	 */
	public static boolean validateEmailAddress(String emailAddress) {
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

	/**
	 * Validates a password against specific criteria:
	 * <ul>
	 * <li>Must be at least 8 characters long</li>
	 * <li>Must contain at least one uppercase letter</li>
	 * <li>Must contain at least one lowercase letter</li>
	 * <li>Must contain at least one digit</li>
	 * <li>Must contain at least one special character (!, @, #, $, %, ^, &, *)</li>
	 * <li>Must not contain any spaces or tabs</li>
	 * </ul>
	 * Prints detailed validation results to the console.
	 *
	 * @param password the password to validate
	 * @return {@code true} if the password meets all criteria; {@code false}
	 *         otherwise
	 */
	public static boolean validatePassword(String password) {
		boolean valid = true;

		if (password.length() < 8) {
			valid = false;
			System.out.println("[!] Password must be at least 8 characters long.");
		} else {
			System.out.println("[X] Password contains at least 8 characters long.");
		}
		if (!Pattern.compile(".*[A-Z].*").matcher(password).matches()) {
			valid = false;
			System.out.println("[!] Password must contain at least one uppercase letter.");
		} else {
			System.out.println("[X] Password contains at least one uppercase letter.");
		}
		if (!Pattern.compile(".*[a-z].*").matcher(password).matches()) {
			valid = false;
			System.out.println("[!] Password must contain at least one lowercase letter.");
		} else {
			System.out.println("[X] Password contains at least one lowercase letter.");
		}
		if (!Pattern.compile(".*\\d.*").matcher(password).matches()) {
			valid = false;
			System.out.println("[!] Password must contain at least one digit.");
		} else {
			System.out.println("[X] Password contains at least one digit.");
		}
		if (!Pattern.compile(".*[!@#$%^&*].*").matcher(password).matches()) {
			valid = false;
			System.out.println("[!] Password must contain at least one special character (!, @, #, $, %, ^, &, *).");
		} else {
			System.out.println("[X] Password contains at least one special character (!, @, #, $, %, ^, &, *).");
		}
		if (Pattern.compile("\\s").matcher(password).find()) {
			valid = false;
			System.out.println("[!] Password must not contain any spaces or tabs.");
		} else {
			System.out.println("[X] Password does not contain any spaces or tabs.");
		}

		if (valid) {
			System.out.println("[X] Password is valid.");
		} else {
			System.out.println("[!] Password is invalid.");
		}
		return valid;
	}
}
