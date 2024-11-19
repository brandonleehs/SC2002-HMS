package hms.boundary;

import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

/**
 * A utility class for displaying error messages related to various types of
 * invalid input, such as date, time, choice value, and choice format. This
 * class is not meant to be instantiated.
 */
public class ErrorMessage {

	private ErrorMessage() {
	}

	/**
	 * Displays an error message for an invalid date input.
	 */
	public static void displayInvalidDateError() {
		System.out.println(InvalidDateException.INVALID_DATE_MESSAGE);
	}

	/**
	 * Displays an error message for an invalid time input.
	 */
	public static void displayInvalidTimeError() {
		System.out.println(InvalidTimeException.INVALID_TIME_MESSAGE);
	}

	/**
	 * Displays an error message for an invalid choice value.
	 */
	public static void displayInvalidChoiceValueError() {
		System.out.println(InvalidChoiceValueException.INVALID_CHOICE_VALUE_MESSAGE);
	}

	/**
	 * Displays an error message for an invalid choice format.
	 */
	public static void displayInvalidChoiceFormatError() {
		System.out.println(InvalidChoiceFormatException.INVALID_CHOICE_FORMAT_MESSAGE);
	}
}
