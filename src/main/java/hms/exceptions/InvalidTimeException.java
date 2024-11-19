package hms.exceptions;


/**
 * Custom exception thrown when an input time does not match the expected format,
 * typically when the time is not in the form "HH-MM".
 * This exception extends {@link Exception}.
 */
@SuppressWarnings("serial")
public class InvalidTimeException extends Exception {

	/** The default error message for invalid time input */
	public static final String INVALID_TIME_MESSAGE = "Input is not of form HH-MM. Please check and try again.";

	/**
     * Constructs a new {@code InvalidTimeException} with the specified detail message.
     *
     * @param message the detail message which provides additional information about the exception.
     */
	public InvalidTimeException(String message) {
		super(message);
	}

	/**
     * Constructs a new {@code InvalidTimeException} with the specified detail message and cause.
     *
     * @param message the detail message which provides additional information about the exception.
     * @param cause the cause of the exception, which can be retrieved later by {@link Throwable#getCause()}.
     */
	public InvalidTimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
