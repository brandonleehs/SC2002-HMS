package hms.boundary.pharmacist;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * Handles the user interface for updating prescription statuses in the Hospital Management System (HMS).
 * This class provides methods to interact with the pharmacist, allowing them to update medicine prescriptions,
 * select appointments, and handle success or failure notifications.
 */
public class UpdatePrescriptionStatusView extends View {
	
	/**
     * Prompts the user to select an appointment by its index.
     * 
     * @param size The total number of available appointments.
     * @return The index of the chosen appointment (1-based index), or -1 if an invalid choice is made.
     */
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

	/**
     * Prompts the user to select a medicine by its index.
     * 
     * @param size The total number of medicines in the prescription.
     * @return The index of the chosen medicine (1-based index), or -1 if an invalid choice is made.
     */
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

	/**
     * Displays a success message indicating that the medicine was successfully dispensed.
     */
	public void SuccessfulDispense() {
		System.out.println("Successfully dispensed medicine!");
	}

	/**
     * Displays a failure message indicating that the medicine dispensing operation was unsuccessful.
     */
	public void UnsuccessfulDispense() {
		System.out.println("Dispense unsuccessful.");
	}

	/**
     * Displays a message indicating that there are no prescription records available.
     */
	public void emptyRecords() {
		System.out.println("There are no records.");
	}

	/**
     * Displays the header for the "Update Prescription Status" section of the system.
     * Includes a bordered title for better visual clarity.
     */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Update Prescription Status");
	}
}
