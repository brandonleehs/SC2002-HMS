package hms.boundary.doctor;

import hms.boundary.View;

public class UpdatePatientMedicalRecordView extends View {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Update Patient Medical Record");
	}

	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. Add Prescription\r\n" + "2. Add Consultation Notes\r\n"
				+ "3. Set Consultation Notes \r\n" + "4. Return to Main Menu";
		String prompt = "Enter choice (1-3):";

		System.out.println(options);
		System.out.println(prompt);
	}

	public void displayAddPrescriptionQtyPrompt() {
		System.out.print("Enter quantity of Medicine to be added:");
	}

	public void displayApptOptionPrompt() {
		System.out.print("Please enter the appointment number: ");
	}

	public void displayAddConsultationNotesPrompt() {
		System.out.println("Enter consultation notes to be added:");
	}

	public void displaySetConsultationNotesPrompt() {
		System.out.println("Enter consultation notes to set:");
	}

	public void displayAddPrescriptionNamePrompt() {
		System.out.print("Enter medicine name to be added:");
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
