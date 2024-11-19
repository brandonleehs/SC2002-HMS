package hms.boundary.administrator.InventoryManagement;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The view class for managing the inventory in the hospital management system.
 * It provides options to the administrator for adding, updating, removing
 * medicine, and viewing inventory.
 */
public class InventoryManagementView extends View {

	/**
	 * Displays the header for the "Inventory Management" section.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Inventory Management");
	}

	/**
	 * Displays the list of options available to the administrator in the inventory
	 * management menu, including actions like adding, updating, and removing
	 * medicines, viewing inventory, and changing stock warnings.
	 */
	public void displayOptions() {
		displayHeader();
		System.out.println("1. Add more Medicine");
		System.out.println("2. Update Medicine Stock");
		System.out.println("3. Remove Medicine");
		System.out.println("4. View Inventory");
		System.out.println("5. Change Stock Warning");
		System.out.println("6. Exit");
		System.out.print("Enter choice: ");
	}

	/**
	 * Retrieves the choice from the user for an inventory management action.
	 * Ensures the entered value is within a valid range.
	 * 
	 * @return The selected choice, or -1 if the input is invalid.
	 */
	public int getChoice() {
		int choice;
		try {
			choice = InputHandler.getChoice(1, 6);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Displays a message indicating the exit of the inventory management process.
	 */
	public void printExit() {
		System.out.println("Exiting inventory management...");
	}
}