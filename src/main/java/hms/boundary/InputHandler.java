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

public class InputHandler {
	private static final Scanner scanner = new Scanner(System.in);

	// low and high define the range of possible choice values
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

	public static String getString() {
		return scanner.nextLine();
	}

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

	public Scanner getScanner() {
		return scanner;
	}
}
