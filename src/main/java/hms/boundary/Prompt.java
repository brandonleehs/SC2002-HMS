package hms.boundary;

public class Prompt {

	public static void displayDatePrompt() {
		System.out.print("Please enter a date (YYYY-MM-DD): ");
	}

	public static void displayTimePrompt() {
		System.out.println("Note that the time must be in a half hour interval (e.g. 09:00, 09:30, etc).");
		System.out.print("Please enter a time (HH:MM): ");
	}

	public static void displayDoctorPrompt() {
		System.out.println("Please select from the following doctors:");
	}

	public static void displayPhoneNumberPrompt() {
		System.out.print("Enter phone number: ");
	}

	public static void displayEmailAddressPrompt() {
		System.out.print("Enter email address: ");
	}

	public static void displayStartTimePrompt() {
		System.out.print("Choose a start time. ");
		displayTimePrompt();
	}

	public static void displayEndTimePrompt() {
		System.out.print("Choose a end time. ");
		displayTimePrompt();
	}

	public static void displayPatientIdPrompt() {
		System.out.print("Enter patient ID: ");
	}
}
