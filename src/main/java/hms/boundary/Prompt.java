package hms.boundary;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

/**
 * A utility class for displaying various prompts to the user. It provides
 * methods for prompting user input for dates, times, phone numbers, email
 * addresses, doctor selection, and patient IDs.
 */
public class Prompt {

	/**
	 * Displays a prompt asking the user to enter a date in the format (YYYY-MM-DD),
	 * and returns the parsed LocalDate if valid. Returns null if the input is
	 * invalid.
	 * 
	 * @return The parsed LocalDate object, or null if the input is invalid.
	 */
	public static LocalDate displayDatePrompt() {
		System.out.print("Please enter a date (YYYY-MM-DD): ");
		LocalDate date;
		try {
			date = InputHandler.getDate();
		} catch (InvalidDateException e) {
			return null;
		}
		return date;
	}

	/**
	 * Displays a prompt asking the user to enter a time in the format (HH:MM),
	 * noting that the time must be in a half-hour interval. Returns the parsed
	 * LocalTime if valid. Returns null if the input is invalid.
	 * 
	 * @return The parsed LocalTime object, or null if the input is invalid.
	 */
	public static LocalTime displayTimePrompt() {
		System.out.println("Note that the time must be in a half hour interval (e.g. 09:00, 09:30, etc).");
		System.out.print("Please enter a time (HH:MM): ");
		LocalTime time;
		try {
			time = InputHandler.getTime();
		} catch (InvalidTimeException e) {
			return null;
		}
		return time;
	}

	/**
	 * Displays a prompt asking the user to select a doctor from a list. This method
	 * does not return any value.
	 */
	public static void displayDoctorPrompt() {
		System.out.println("Please select from the following doctors:");
	}

	/**
	 * Displays a prompt asking the user to enter a phone number and returns the
	 * entered phone number as a string.
	 * 
	 * @return The phone number entered by the user.
	 */
	public static String displayPhoneNumberPrompt() {
		System.out.print("Enter phone number: ");
		return InputHandler.getString();
	}

	/**
	 * Displays a prompt asking the user to enter an email address and returns the
	 * entered email address as a string.
	 * 
	 * @return The email address entered by the user.
	 */
	public static String displayEmailAddressPrompt() {
		System.out.print("Enter email address: ");
		return InputHandler.getString();
	}

	/**
	 * Displays a prompt asking the user to choose a start time, and returns the
	 * parsed LocalTime if valid.
	 * 
	 * @return The parsed LocalTime object representing the start time.
	 */
	public static LocalTime displayStartTimePrompt() {
		System.out.print("Choose a start time. ");
		return displayTimePrompt();
	}

	/**
	 * Displays a prompt asking the user to choose an end time, and returns the
	 * parsed LocalTime if valid.
	 * 
	 * @return The parsed LocalTime object representing the end time.
	 */
	public static LocalTime displayEndTimePrompt() {
		System.out.print("Choose a end time. ");
		return displayTimePrompt();
	}

	/**
	 * Displays a prompt asking the user to enter a patient ID. This method does not
	 * return any value.
	 */
	public static void displayPatientIdPrompt() {
		System.out.print("Enter patient ID: ");
	}

	/**
	 * Displays a prompt asking the user to enter a new password, and returns the
	 * entered password as a string.
	 * 
	 * @return The new password entered by the user.
	 */
	public static String displayNewPasswordPrompt() {
		System.out.print("Welcome to HMS.\r\nPlease enter your new password: ");
		return InputHandler.getString();
	}
}
