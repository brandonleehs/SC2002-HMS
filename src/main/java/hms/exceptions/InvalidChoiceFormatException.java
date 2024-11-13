package hms.exceptions;

@SuppressWarnings("serial")
public class InvalidChoiceFormatException extends Exception {
	public static final String INVALID_CHOICE_FORMAT_MESSAGE = "Input is not of integer format. Please check and try again.";

	public InvalidChoiceFormatException(String message) {
		super(message);
	}

	public InvalidChoiceFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
