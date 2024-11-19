package hms.boundary.patient;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The PatientMenuView class represents the interface for a patient's menu,
 * allowing them to navigate through various options such as viewing medical
 * records, updating information, scheduling appointments, and logging out.
 */
public class PatientMenuView extends View {
	private Patient patient;

	public PatientMenuView(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Displays the menu options available to the patient and prompts them to make a
	 * choice.
	 *
	 * @return the patient's choice as an integer, or -1 if an invalid choice is
	 *         entered.
	 */
	public int displayOptions() {
		String options = "Please select an option:\r\n" + "1. View Medical Record\r\n"
				+ "2. Update Personal Information\r\n" + "3. View Available Appointment Slots\r\n"
				+ "4. Schedule an Appointment\r\n" + "5. Reschedule an Appointment\r\n" + "6. Cancel an Appointment\r\n"
				+ "7. View Scheduled Appointments\r\n" + "8. View Past Appointment Outcome Records\r\n"
				+ "9. Change Password\r\n" + "10. Logout";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-10):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);

		int choice;
		try {
			choice = InputHandler.getChoice(1, 10);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Displays a personalized header for the patient menu, including the patient's
	 * name.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, String.format("Welcome, %s.", patient.getName()));
	}
}
