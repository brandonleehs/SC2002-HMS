package hms.boundary.administrator.ManageStaff;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The RemoveStaffView class handles the user interface for removing hospital
 * staff. It prompts the administrator to select a staff role, enter a user ID,
 * and displays appropriate success or error messages for staff removal.
 */
public class RemoveStaffView extends View {
	int choice;

	public RemoveStaffView() {
		choice = 0;
	}

	/**
	 * Displays the header for the Remove Staff view. It provides a bordered text
	 * representation with the title "Remove Staff".
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Remove Staff");
	}

	/**
	 * Prompts the user to choose a role for the staff to be removed. The available
	 * choices are Doctor, Pharmacist, Receptionist, or Cancel.
	 * 
	 * @return the user's choice as an integer (1 for Doctor, 2 for Pharmacist, 3
	 *         for Receptionist, 4 for Cancel).
	 */
	public int getRoleChoice() {
		System.out.println("Choose role (Doctor/Pharmacist/Receptionist): ");
		System.out.println("1: Doctor\r\n2: Pharmacist\r\n3: Receptionist\r\n4: Cancel");
		choice = 0;
		try {
			choice = InputHandler.getChoice(1, 4);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return 4;
		}
		return choice;
	}

	/**
	 * Prompts the user to enter the ID of the staff member to be removed.
	 * 
	 * @return the user ID entered by the administrator as a String.
	 */
	public String getID() {
		System.out.println("Enter User ID of Staff:");
		return InputHandler.getString();
	}

	/**
	 * Displays an error message indicating that the staff member does not exist.
	 */
	public void staffDoesNotExist() {
		System.out.println("Staff does not exist");
	}

	/**
	 * Displays a success message indicating that the staff removal was successful.
	 */
	public void staffRemoveSuccessful() {
		System.out.println("Staff removal successful");
	}
}
