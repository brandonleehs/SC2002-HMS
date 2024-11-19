package hms.boundary.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The view class for setting stock warning amounts for medicines in the
 * inventory. It allows the administrator to specify stock warning levels for
 * specific medicines.
 */
public class SetStockWarningView extends View {
	/**
	 * Displays the header for the "Set Stock Warning Amount" section.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Set Stock Warning Amount");
	}

	/**
	 * Prompts the administrator to enter the index of the medicine to set a stock
	 * warning amount for.
	 * 
	 * @param medicineNames A list of available medicine names to choose from.
	 * @return The index of the medicine to set the warning amount, or -1 if the
	 *         input is invalid.
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
	 * Prompts the administrator to enter the amount of the selected medicine to be
	 * set as a stock warning.
	 * 
	 * @return The warning amount for the selected medicine, or -1 if the input is
	 *         invalid.
	 */
	public int MedicineAmountPrompt() {
		int amount;
		System.out.println("Enter Amount of Medicine to be set as Warning:");
		try {
			amount = InputHandler.getChoice(1, 9999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			amount = -1;
		}
		return amount;
	}

	/**
	 * Displays a message confirming that the stock warning amount has been
	 * successfully changed.
	 */
	public void SuccessfulChangePrompt() {
		System.out.println("Warning succesfully changed");
		System.out.println("-".repeat(WIDTH));
	}
}
