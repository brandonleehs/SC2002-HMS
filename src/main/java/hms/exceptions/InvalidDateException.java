package hms.exceptions;

@SuppressWarnings("serial")
public class InvalidDateException extends Exception {
	public static final String INVALID_DATE_MESSAGE = "Input is not of form YYYY-MM-DD. Please check and try again.";

	public InvalidDateException(String message) {
		super(message);
	}

	public InvalidDateException(String message, Throwable cause) {
		super(message, cause);
	}
}
