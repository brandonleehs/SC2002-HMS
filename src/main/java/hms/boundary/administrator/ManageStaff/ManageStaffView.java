package hms.boundary.administrator.ManageStaff;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The ManageStaffView class handles the user interface for managing hospital
 * staff. It displays the options available to the administrator for adding,
 * updating, removing, and viewing staff, as well as returning to the main menu.
 */
public class ManageStaffView extends View {

	/**
	 * Displays the header for the Manage Hospital Staff view. It provides a
	 * bordered text representation with the title "Manage Hospital Staff".
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Manage Hospital Staff");
	}

	/**
	 * Displays the options available to the administrator for managing staff. The
	 * options include adding staff, updating staff, removing staff, displaying all
	 * staff, or returning to the main menu.
	 */
	public void displayOptions() {
		System.out.println("1. Add Staff");
		System.out.println("2. Update Staff");
		System.out.println("3. Remove Staff");
		System.out.println("4. Display All Staff");
		System.out.println("5. Return to main menu");
	}

	/**
	 * Displays a success message indicating that a user has been successfully
	 * removed.
	 */
	public void displayRemoveSuccess() {
		System.out.println("User successfully removed.");
	}

	/**
	 * Displays an error message indicating that the user was not found.
	 */
	public void displayUserNotFound() {
		System.out.println("User not found.");
	}

	/**
	 * Retrieves an integer choice from the user from the menu and returns the
	 * choice. The valid choices are between 1 and 5, corresponding to the available
	 * options.
	 * 
	 * @return the user's choice as an integer. If invalid input is provided,
	 *         returns -1.
	 */
	public int getChoice() {
		int choice;
		try {
			choice = InputHandler.getChoice(1, 5);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Prints a message indicating that the system is returning to the main menu.
	 */
	public void printExit() {
		System.out.println("Returning to main menu...");
	}
}