package hms.boundary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

/**
 * A utility class that handles user input by interacting with the console. It
 * provides methods to retrieve choices, strings, dates, and times from the
 * user, with error handling for invalid input formats.
 */
public class InputHandler {
	private static final Scanner scanner = new Scanner(System.in);

	/**
	 * Receives an integer input from the user and ensures that the input is within
	 * a specified range. Throws an InvalidChoiceFormatException if the input format
	 * is incorrect, or an InvalidChoiceValueException if the input is outside the
	 * valid range.
	 * 
	 * @param low  The lower bound of the valid range.
	 * @param high The upper bound of the valid range.
	 * @return The valid choice entered by the user.
	 * @throws InvalidChoiceFormatException if the input format is invalid.
	 * @throws InvalidChoiceValueException  if the input choice is outside the valid
	 *                                      range.
	 */
	public static int getChoice(int low, int high) throws InvalidChoiceFormatException, InvalidChoiceValueException {
		try {
			int choice = scanner.nextInt();
			scanner.nextLine();
			if (choice < low || choice > high) {
				ErrorMessage.displayInvalidChoiceValueError();
				throw new InvalidChoiceValueException(InvalidChoiceValueException.INVALID_CHOICE_VALUE_MESSAGE);
			}
			return choice;
		} catch (InputMismatchException e) {
			ErrorMessage.displayInvalidChoiceFormatError();
			scanner.nextLine(); // Clear buffer!
			throw new InvalidChoiceFormatException(InvalidChoiceFormatException.INVALID_CHOICE_FORMAT_MESSAGE, e);
		}
	}

	/**
	 * Receives a string from the user and returns the entered string.
	 * 
	 * @return The string entered by the user.
	 */
	public static String getString() {
		return scanner.nextLine();
	}

	/**
	 * Receives a date input from the user and parses it into a LocalDate object.
	 * Throws an InvalidDateException if the input does not match the expected date
	 * format (yyyy-MM-dd).
	 * 
	 * @return The parsed LocalDate object.
	 * @throws InvalidDateException if the input date format is invalid.
	 */
	public static LocalDate getDate() throws InvalidDateException {
		String dateString = scanner.nextLine();
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate date = LocalDate.parse(dateString, dateformatter);
			return date;
		} catch (DateTimeParseException e) {
			ErrorMessage.displayInvalidDateError();
			throw new InvalidDateException(InvalidDateException.INVALID_DATE_MESSAGE, e);
		}
	}

	/**
	 * Receives a time input from the user and parses it into a LocalTime object.
	 * Throws an InvalidTimeException if the input does not match the expected time
	 * format (HH:mm:ss).
	 * 
	 * @return The parsed LocalTime object.
	 * @throws InvalidTimeException if the input time format is invalid.
	 */
	public static LocalTime getTime() throws InvalidTimeException {
		String timeString = scanner.nextLine();
		try {
			LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_TIME);
			return time;
		} catch (DateTimeParseException e) {
			ErrorMessage.displayInvalidTimeError();
			throw new InvalidTimeException(InvalidTimeException.INVALID_TIME_MESSAGE, e);
		}
	}
}
