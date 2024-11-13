package hms.boundary;

import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

public class ErrorMessage {

	public static void displayInvalidDateError() {
		System.out.println(InvalidDateException.INVALID_DATE_MESSAGE);
	}

	public static void displayInvalidTimeError() {
		System.out.println(InvalidTimeException.INVALID_TIME_MESSAGE);
	}

	public static void displayInvalidChoiceValueError() {
		System.out.println(InvalidChoiceValueException.INVALID_CHOICE_VALUE_MESSAGE);
	}

	public static void displayInvalidChoiceFormatError() {
		System.out.println(InvalidChoiceFormatException.INVALID_CHOICE_FORMAT_MESSAGE);
	}
}
