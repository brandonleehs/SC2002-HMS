package hms.exceptions;

/**
 * Custom exception thrown when an input date does not match the expected format,
 * typically when the date is not in the form "YYYY-MM-DD".
 * This exception extends {@link Exception}.
 */
@SuppressWarnings("serial")
public class InvalidDateException extends Exception {

	/** The default error message for invalid date input */
	public static final String INVALID_DATE_MESSAGE = "Input is not of form YYYY-MM-DD. Please check and try again.";

	/**
     * Constructs a new {@code InvalidDateException} with the specified detail message.
     *
     * @param message the detail message which provides additional information about the exception.
     */
	public InvalidDateException(String message) {
		super(message);
	}

	/**
     * Constructs a new {@code InvalidDateException} with the specified detail message and cause.
     *
     * @param message the detail message which provides additional information about the exception.
     * @param cause the cause of the exception, which can be retrieved later by {@link Throwable#getCause()}.
     */
	public InvalidDateException(String message, Throwable cause) {
		super(message, cause);
	}
}
