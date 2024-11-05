package hms.boundary;

public class ErrorMessage {

	public static void displayInvalidDateError() {
		System.out.println("Input is not of form YYYY-MM-DD. Please check and try again.");
	}

	public static void displayInvalidTimeError() {
		System.out.println("Input is not of form HH-MM. Please check and try again.");
	}

	public static void displayInvalidChoiceError() {
		System.out.println("Input is not a valid choice. Please check and try again.");
	}

	public static void displayInvalidChoiceFormat() {
		System.out.println("Input is not of integer format. Please check and try again.");
	}
}
