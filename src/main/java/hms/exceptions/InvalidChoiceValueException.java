package hms.exceptions;

/**
 * Custom exception thrown when an input choice is invalid, typically when a user selects
 * an option that does not match the expected values.
 * This exception extends {@link Exception}.
 */
@SuppressWarnings("serial")
public class InvalidChoiceValueException extends Exception {

	/** The default error message for invalid choice input */
	public static final String INVALID_CHOICE_VALUE_MESSAGE = "Input is not a valid choice. Please check and try again.";

	/**
     * Constructs a new {@code InvalidChoiceValueException} with the specified detail message.
     *
     * @param message the detail message which provides additional information about the exception.
     */
	public InvalidChoiceValueException(String message) {
		super(message);
	}

	/**
     * Constructs a new {@code InvalidChoiceValueException} with the specified detail message and cause.
     *
     * @param message the detail message which provides additional information about the exception.
     * @param cause the cause of the exception, which can be retrieved later by {@link Throwable#getCause()}.
     */
	public InvalidChoiceValueException(String message, Throwable cause) {
		super(message, cause);
	}
}
