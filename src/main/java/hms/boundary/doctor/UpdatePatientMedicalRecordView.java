package hms.boundary.doctor;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The UpdatePatientMedicalRecordView class is responsible for handling user
 * input and displaying options for updating patient medical records, including
 * adding prescriptions, adding or setting consultation notes, and handling
 * appointment records.
 */
public class UpdatePatientMedicalRecordView extends View {

	/**
	 * Displays the header for the "Update Patient Medical Record" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Update Patient Medical Record");
	}

	/**
	 * Displays the available options for updating patient medical records.
	 * 
	 * @return the user's choice (1-4) for updating records or returning to the main
	 *         menu.
	 */
	public int displayOptions() {
		String options = "Please select an option:\r\n" + "1. Add Prescription\r\n" + "2. Add Consultation Notes\r\n"
				+ "3. Set Consultation Notes \r\n" + "4. Return to Main Menu";
		String prompt = "Enter choice (1-3):";

		System.out.println(options);
		System.out.println(prompt);
		int choice;
		try {
			choice = InputHandler.getChoice(1, 4);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Displays a prompt to add the quantity of medicine to be prescribed.
	 * 
	 * @return the quantity of the medicine to be added (1-999).
	 */
	public int displayAddPrescriptionQtyPrompt() {
		System.out.print("Enter quantity of Medicine to be added: ");
		int amt;
		try {
			amt = InputHandler.getChoice(1, 999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return amt;
	}

	/**
	 * Displays a prompt to select an appointment by its number.
	 * 
	 * @param size the total number of appointments.
	 * @return the index of the selected appointment.
	 */
	public int displayApptOptionPrompt(int size) {
		System.out.print("Please enter the appointment number: ");
		int i;
		try {
			i = InputHandler.getChoice(1, size);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i - 1;
	}

	/**
	 * Displays a prompt to set consultation notes for a patient.
	 * 
	 * @return the consultation notes entered by the user.
	 */
	public String displaySetConsultationNotesPrompt() {
		System.out.println("Enter consultation notes to add:");
		return InputHandler.getString();
	}

	/**
	 * Displays a prompt to select the name of a medicine to be added to the
	 * prescription.
	 * 
	 * @param size the total number of medicines available for selection.
	 * @return the index of the selected medicine.
	 */
	public int displayAddPrescriptionNamePrompt(int size) {
		System.out.print("Enter medicine index to be added: ");
		int i;
		try {
			i = InputHandler.getChoice(1, size);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i - 1;
	}

	/**
	 * Displays a prompt to remove a medicine from the prescription.
	 */
	public void displayRemovePrescriptionNamePrompt() {
		System.out.print("Enter medicine name to be removed:");
	}

	/**
	 * Displays a message indicating that the user is returning to the main menu.
	 */
	public void displayReturnMenu() {
		System.out.println("Returning to Menu.");
	}

	/**
	 * Displays a message indicating that no appointment records were found.
	 */
	public void displayNoRecords() {
		System.out.println("No Appointment records found.");
	}
}
