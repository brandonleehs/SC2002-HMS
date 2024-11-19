package hms.boundary.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The view class for updating the stock level of medicines in the inventory. It
 * allows the administrator to set the current stock levels for selected
 * medicines.
 */
public class UpdateMedicineView extends View {

	/**
	 * Displays the header for the "Set Stock Level of Medicine" section.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Set stock level of Medicine");
	}

	/**
	 * Prompts the administrator to enter the index of the medicine to update the
	 * stock level for.
	 * 
	 * @param medicineNames A list of available medicine names to choose from.
	 * @return The index of the medicine to update, or -1 if the input is invalid.
	 */
	public int MedicineIndexPrompt(List<String> medicineNames) {
		int choice;
		System.out.println("Enter Index of Medicine to be set:");
		try {
			choice = InputHandler.getChoice(1, medicineNames.size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Prompts the administrator to enter the amount of the selected medicine to set
	 * as the current stock level.
	 * 
	 * @return The updated stock level for the selected medicine, or -1 if the input
	 *         is invalid.
	 */
	public int MedicineAmountPrompt() {
		int amount;
		System.out.println("Enter amount of currently available Medicine to be set at:");
		try {
			amount = InputHandler.getChoice(1, 9999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			amount = -1;
		}
		return amount;
	}

	/**
	 * Displays a message confirming that the medicine stock level has been
	 * successfully updated.
	 */
	public void SuccessfulUpdatePrompt() {
		System.out.println("Medicine successfully updated");
	}
}
