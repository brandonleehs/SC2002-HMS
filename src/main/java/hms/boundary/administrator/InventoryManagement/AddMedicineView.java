package hms.boundary.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The view class for adding medicine to the inventory. It provides prompts to
 * the user for selecting a medicine, specifying the amount to be added, and
 * confirming successful addition of medicine to the inventory.
 */
public class AddMedicineView extends View {

	/**
	 * Displays the header for the "Add Medicine" section.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Add Medicine");
	}

	/**
	 * Prompts the user to enter the index of the medicine to be added to the
	 * inventory. It ensures the index is within a valid range based on the list of
	 * available medicine names.
	 * 
	 * @param medicineNames The list of available medicine names for selection.
	 * @return The index of the selected medicine, or -1 if there is an error in
	 *         input.
	 */
	public int MedicineIndexPrompt(List<String> medicineNames) {
		int choice;
		System.out.println("Enter Index of Medicine to be added:");
		try {
			choice = InputHandler.getChoice(1, medicineNames.size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Prompts the user to enter the amount of medicine to be added to the
	 * inventory. Ensures the entered value is within a valid range.
	 * 
	 * @return The amount of medicine to be added, or -1 if there is an error in
	 *         input.
	 */
	public int MedicineAmountPrompt() {
		int amount;
		System.out.println("Enter Amount of Medicine to be added:");
		try {
			amount = InputHandler.getChoice(1, 9999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			amount = -1;
		}
		return amount;
	}

	/**
	 * Displays a message indicating that the medicine has been successfully added
	 * to the inventory.
	 */
	public void SuccessfulRequestPrompt() {
		System.out.println("Medicine successfully added");
	}
}
