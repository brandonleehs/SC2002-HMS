package hms.exceptions;

/**
 * Custom exception thrown when an invalid input format is detected, typically when 
 * an input that is expected to be an integer is not in the correct format.
 * This exception extends {@link Exception}.
 */
@SuppressWarnings("serial")
public class InvalidChoiceFormatException extends Exception {
	
	/** The default error message for invalid input format */
	public static final String INVALID_CHOICE_FORMAT_MESSAGE = "Input is not of integer format. Please check and try again.";

	/**
     * Constructs a new {@code InvalidChoiceFormatException} with the specified detail message.
     *
     * @param message the detail message which provides additional information about the exception.
     */
	public InvalidChoiceFormatException(String message) {
		super(message);
	}

	/**
     * Constructs a new {@code InvalidChoiceFormatException} with the specified detail message and cause.
     *
     * @param message the detail message which provides additional information about the exception.
     * @param cause the cause of the exception, which can be retrieved later by {@link Throwable#getCause()}.
     */
	public InvalidChoiceFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
