package hms.boundary.patient;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class UpdatePersonalInfoView extends View {

	public void displayPhoneNumberUpdate() {
		System.out.println("Phone number updated.");
	}

	public void displayEmailAddressUpdate() {
		System.out.println("Email address updated.");
	}

	public void displayReturnMenu() {
		System.out.println("Returning to Menu.");
	}

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

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Update Personal Information");
	}

}
