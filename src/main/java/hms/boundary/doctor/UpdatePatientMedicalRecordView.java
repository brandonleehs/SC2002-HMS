package hms.boundary.doctor;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class UpdatePatientMedicalRecordView extends View {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Update Patient Medical Record");
	}

	public int displayOptions() {
		String options = "Please select an option:\r\n" + "1. Add Prescription\r\n" + "2. Add Consultation Notes\r\n"
				+ "3. Set Consultation Notes \r\n" + "4. Return to Main Menu";
		String prompt = "Enter choice (1-3):";

		System.out.println(options);
		System.out.println(prompt);
		int choice;
        try{
            choice = InputHandler.getChoice(1, 4);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return choice;
	}

	public int displayAddPrescriptionQtyPrompt() {
		System.out.print("Enter quantity of Medicine to be added:");
		int amt;
        try{
            amt = InputHandler.getChoice(1, 999);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return amt;
	}

	public int displayApptOptionPrompt(int size) {
		System.out.print("Please enter the appointment number: ");
		int i;
        try{
            i = InputHandler.getChoice(1, size);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return i-1;
    }

	public String displaySetConsultationNotesPrompt() {
		System.out.println("Enter consultation notes to add:");
		return InputHandler.getString();
	}

	public int displayAddPrescriptionNamePrompt(int size) {
		System.out.print("Enter medicine name to be added:");
		int i;
        try{
            i = InputHandler.getChoice(1, size);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return i-1;
	}

	public void displayRemovePrescriptionNamePrompt() {
		System.out.print("Enter medicine name to be removed:");
	}

	public void displayReturnMenu() {
		System.out.println("Returning to Menu.");
	}

	public void displayNoRecords() {
		System.out.println("No Appointment records found.");
	}
}
