package hms.boundary.doctor;

import hms.boundary.InputHandler;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class CompleteAppointmentView extends UpdatePatientMedicalRecordView {
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Complete Appointment");
	}

	public void displayNoAppointments() {
		System.out.println("No Appointments. Returning to main menu.");
	}

	public int displayApptOptionPrompt(int size) {
		System.out.println("Please enter the appointment index:");
		int i;
		try {
			i = InputHandler.getChoice(1, size);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i - 1;
	}

	public String displaySetConsultationNotesPrompt() {
		System.out.println("Enter consultation notes:");
		return InputHandler.getString();
	}

	public String displayServiceTypePrompt() {
		System.out.println("Enter service type:");
		return InputHandler.getString();
	}

	public int displayPrescriptionChoicePrompt() {
		System.out.print("Enter number of prescriptions to give (0-999): ");
		int amt;
		try {
			amt = InputHandler.getChoice(0, 999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return amt;
	}

	public int displayAddPrescriptionNamePrompt(int size) {
		System.out.print("Enter index of Medicine to be added: ");
		int i;
		try {
			i = InputHandler.getChoice(1, size);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i - 1;
	}

	public int displayAddPrescriptionAmountPrompt() {
		System.out.print("Enter amount of Medicine to be prescribed (1-999): ");
		int amt;
		try {
			amt = InputHandler.getChoice(1, 999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return amt;
	}

	public void SuccessfulPrescription() {
		System.out.println("Medicine prescribed successfully");
	}
}