package hms.boundary.pharmacist;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * View class responsible for managing the user interface for submitting replenishment requests.
 * This class provides methods to prompt the user for necessary details such as the index and amount of 
 * medicine to be replenished and confirms successful submission.
 */
public class SubmitReplenishmentRequestView extends View {

	/**
     * Prompts the user to enter the index of the medicine to be replenished.
     *
     * @param size The total number of available medicines in the inventory.
     * @return The selected index (1 to size), or -1 if the input is invalid.
     */
	public int MedicineIndexPrompt(int size) {
		System.out.println("Enter Index of Medicine to be replenished:");
		int i;
		try {
			i = InputHandler.getChoice(1, size);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i;
	}

	/**
     * Prompts the user to enter the amount of medicine to be replenished.
     *
     * @return The entered amount (1 to 999), or -1 if the input is invalid.
     */
	public int MedicineAmountPrompt() {
		System.out.println("Enter Amount of Medicine to be replenished (1-999):");
		int amt;
		try {
			amt = InputHandler.getChoice(1, 999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return amt;
	}

	/**
     * Displays a message indicating the successful submission of a replenishment request.
     */
	public void SuccessfulRequestPrompt() {
		System.out.println("Request successfully made");
	}

	/**
     * Displays the header for the replenishment request submission screen.
     * Includes a bordered title for the view.
     */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Submit Replenishment Request");
	}
}
