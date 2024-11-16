package hms.boundary.pharmacist;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class UpdatePrescriptionStatusView extends View {
	public int AppointmentPrompt(int size) {
		System.out.println("Enter Appointment index: ");
		int i;
		try {
			i = InputHandler.getChoice(1, size);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i;
	}

	public int MedicinePrompt(int size) {
		System.out.println("Enter Medicine Index: ");
		int i;
		try {
			i = InputHandler.getChoice(1, size);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i;
	}

	public int DecisionPrompt() {
		System.out.println("Enter 1 to Dispense, 2 to Undispense: ");
		int choice;
		try {
			choice = InputHandler.getChoice(1, 2);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	public void SuccessfulDispense() {
		System.out.println("Successfully dispensed medicine!");
	}

	public void UnsuccessfulDispense() {
		System.out.println("Dispense unsuccessful, insufficient medicine.");
	}

	public void emptyRecords() {
		System.out.println("There are no records.");
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Update Prescription Status");
	}
}
