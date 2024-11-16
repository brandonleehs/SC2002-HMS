package hms.boundary.pharmacist;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class SubmitReplenishmentRequestView extends View {

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

	public void SuccessfulRequestPrompt() {
		System.out.println("Request successfully made");
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Submit Replenishment Request");
	}
}
