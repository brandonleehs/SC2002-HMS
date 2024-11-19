package hms.boundary.doctor;

import hms.boundary.InputHandler;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The CompleteAppointmentView class is responsible for providing the user
 * interface to complete an appointment. It allows the doctor to view
 * appointments, input consultation notes, prescribe medicine, and manage
 * prescriptions.
 */
public class CompleteAppointmentView extends UpdatePatientMedicalRecordView {
	/**
	 * Displays the header for the "Complete Appointment" section. It provides a
	 * bordered text representation with the title "Complete Appointment".
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Complete Appointment");
	}

	/**
	 * Displays a message indicating there are no appointments and that the user
	 * will return to the main menu.
	 */
	public void displayNoAppointments() {
		System.out.println("No Appointments. Returning to main menu.");
	}

	/**
	 * Prompts the user to enter the index of the appointment they want to complete.
	 * 
	 * @param size the number of appointments available.
	 * @return the index of the selected appointment, or -1 if invalid input is
	 *         provided.
	 */
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

	/**
	 * Prompts the user to enter consultation notes for the appointment.
	 * 
	 * @return the consultation notes entered by the user.
	 */
	public String displaySetConsultationNotesPrompt() {
		System.out.println("Enter consultation notes:");
		return InputHandler.getString();
	}

	/**
	 * Prompts the user to enter the type of service provided during the
	 * appointment.
	 * 
	 * @return the service type entered by the user.
	 */
	public String displayServiceTypePrompt() {
		System.out.println("Enter service type:");
		return InputHandler.getString();
	}

	/**
	 * Prompts the user to enter the number of prescriptions to give for the
	 * appointment. The number must be between 0 and 999.
	 * 
	 * @return the number of prescriptions to be given, or -1 if invalid input is
	 *         provided.
	 */
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

	/**
	 * Prompts the user to select the medicine to be added to the prescription.
	 * 
	 * @param size the number of available medicines.
	 * @return the index of the selected medicine, or -1 if invalid input is
	 *         provided.
	 */
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

	/**
	 * Prompts the user to enter the amount of the selected medicine to be
	 * prescribed. The amount must be between 1 and 999.
	 * 
	 * @return the amount of the medicine to be prescribed, or -1 if invalid input
	 *         is provided.
	 */
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

	/**
	 * Displays a message indicating that the prescription was successfully added.
	 */
	public void SuccessfulPrescription() {
		System.out.println("Medicine prescribed successfully");
	}
}