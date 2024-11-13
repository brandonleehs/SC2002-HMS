package hms.exceptions;

@SuppressWarnings("serial")
public class InvalidTimeException extends Exception {
	public static final String INVALID_TIME_MESSAGE = "Input is not of form HH-MM. Please check and try again.";

	public InvalidTimeException(String message) {
		super(message);
	}

	public InvalidTimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
