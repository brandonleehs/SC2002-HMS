package hms.exceptions;

@SuppressWarnings("serial")
public class InvalidChoiceValueException extends Exception {
	public static final String INVALID_CHOICE_VALUE_MESSAGE = "Input is not a valid choice. Please check and try again.";

	public InvalidChoiceValueException(String message) {
		super(message);
	}

	public InvalidChoiceValueException(String message, Throwable cause) {
		super(message, cause);
	}
}
