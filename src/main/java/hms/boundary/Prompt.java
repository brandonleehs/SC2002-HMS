package hms.boundary;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

public class Prompt {

	public static LocalDate displayDatePrompt() {
		System.out.print("Please enter a date (YYYY-MM-DD): ");
		LocalDate date;
		try{
			date = InputHandler.getDate();
		} catch (InvalidDateException e) {
			return null;
		}
		return date;
	}

	public static LocalTime displayTimePrompt() {
		System.out.println("Note that the time must be in a half hour interval (e.g. 09:00, 09:30, etc).");
		System.out.print("Please enter a time (HH:MM): ");
		LocalTime time;
		try{
			time = InputHandler.getTime();
		} catch (InvalidTimeException e) {
			return null;
		}
		return time;
	}

	public static void displayDoctorPrompt() {
		System.out.println("Please select from the following doctors:");
	}

	public static String displayPhoneNumberPrompt() {
		System.out.print("Enter phone number: ");
		return InputHandler.getString();
	}

	public static String displayEmailAddressPrompt() {
		System.out.print("Enter email address: ");
		return InputHandler.getString();
	}

	public static LocalTime displayStartTimePrompt() {
		System.out.print("Choose a start time. ");
		return displayTimePrompt();
	}

	public static LocalTime displayEndTimePrompt() {
		System.out.print("Choose a end time. ");
		return displayTimePrompt();
	}

	public static void displayPatientIdPrompt() {
		System.out.print("Enter patient ID: ");
	}

	public static String displayNewPasswordPrompt() {
		System.out.print("Welcome to HMS.\r\nPlease enter your new password: ");
		return InputHandler.getString();
	}
}
