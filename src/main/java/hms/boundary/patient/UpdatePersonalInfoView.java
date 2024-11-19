package hms.boundary.patient;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The UpdatePersonalInfoView class handles the interface for patients to update
 * their personal information, such as phone number and email address.
 */
public class UpdatePersonalInfoView extends View {

	/**
	 * Displays a message indicating that the phone number has been successfully
	 * updated.
	 */
	public void displayPhoneNumberUpdate() {
		System.out.println("Phone number updated.");
	}

	/**
	 * Displays a message indicating that the email address has been successfully
	 * updated.
	 */
	public void displayEmailAddressUpdate() {
		System.out.println("Email address updated.");
	}

	/**
	 * Displays a message indicating that the email address has not been updated.
	 */
	public void displayNoEmailAddressUpdate() {
		System.out.println("Email address not updated.");
	}

	/**
	 * Displays a message indicating a return to the main menu.
	 */
	public void displayReturnMenu() {
		System.out.println("Returning to Menu.");
	}

	/**
	 * Displays the menu options for updating personal information and prompts the
	 * user to make a choice.
	 *
	 * @return the user's choice as an integer, or -1 if an invalid choice is
	 *         entered.
	 */
	public int displayOptions() {
		String options = "Please select an option:\r\n" + "1. Update Phone Number\r\n" + "2. Update Email Address\r\n"
				+ "3. Return to main menu";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-3):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
		int choice;
		try {
			choice = InputHandler.getChoice(1, 3);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}

		return choice;
	}

	/**
	 * Displays a header for the "Update Personal Information" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Update Personal Information");
	}

}
