package hms.boundary.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The view class for removing medicines from the inventory in the hospital
 * management system. It allows the administrator to remove specific medicines
 * and handle cases of insufficient stock.
 */
public class RemoveMedicineView extends View {
	/**
	 * Displays the header for the "Remove Medicine" section.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Add Medicine");
	}

	/**
	 * Prompts the administrator to enter the index of the medicine to be removed
	 * from the inventory.
	 * 
	 * @param medicineNames A list of available medicine names to choose from.
	 * @return The index of the medicine to be removed, or -1 if the input is
	 *         invalid.
	 */
	public int MedicineIndexPrompt(List<String> medicineNames) {
		int choice;
		System.out.println("Enter Index of Medicine to be removed:");
		try {
			choice = InputHandler.getChoice(1, medicineNames.size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Prompts the administrator to enter the amount of the selected medicine to be
	 * removed.
	 * 
	 * @return The amount of medicine to be removed, or -1 if the input is invalid.
	 */
	public int MedicineAmountPrompt() {
		int amount;
		System.out.println("Enter Amount of Medicine to be removed:");
		try {
			amount = InputHandler.getChoice(1, 9999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			amount = -1;
		}
		return amount;
	}

	/**
	 * Displays a message indicating that there is insufficient stock to remove the
	 * medicine.
	 */
	public void InsufficientStockPrompt() {
		System.out.println("Insufficient stock, removal cancelled.");
	}

	/**
	 * Displays a message confirming that the medicine was successfully removed from
	 * the inventory.
	 */
	public void SuccessfulRemovePrompt() {
		System.out.println("Medicine successfully removed");
	}
}
