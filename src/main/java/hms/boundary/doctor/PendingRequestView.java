package hms.boundary.doctor;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.repository.PatientRepository;

/**
 * The PendingRequestView class provides the user interface for displaying and
 * managing pending appointment requests. It allows the doctor to view, confirm,
 * or cancel pending appointments.
 */
public class PendingRequestView extends View {
	/**
	 * Displays a list of pending appointments for the given doctor. The list shows
	 * details such as date, time, status, and the patient's name.
	 * 
	 * @param doctor            the doctor whose pending appointments are being
	 *                          displayed.
	 * @param patientRepository the repository that provides access to patient
	 *                          information.
	 */
	public void displayPendingAppointments(Doctor doctor, PatientRepository patientRepository) {
		List<Appointment> pendingAppointmentList = doctor.getPendingAppointmentList();

		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 13 + "s | %-" + (WIDTH - 49) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Patient Name");

		for (int i = 0; i < pendingAppointmentList.size(); i++) {
			Appointment appointment = pendingAppointmentList.get(i);
			System.out.printf(format, i + 1, appointment.getDate().toString(), appointment.getTime().toString(),
					appointment.getAppointmentStatus().toString(),
					patientRepository.getById(appointment.getPatientId()).getName());
		}
	}

	/**
	 * Displays the options for confirming or canceling a pending appointment.
	 * 
	 * @return the option selected by the doctor (1 for confirm, 2 for cancel), or
	 *         -1 if invalid input is provided.
	 */
	public int displayOptions() {
		System.out.println("Please select an option:");
		System.out.println("1. Confirm");
		System.out.println("2. Cancel");
		int choice;
		try {
			choice = InputHandler.getChoice(1, 2);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Prompts the doctor to select an appointment index from the list of pending
	 * appointments.
	 * 
	 * @param size the number of pending appointments in the list.
	 * @return the selected appointment index (adjusted to zero-based), or -1 if
	 *         invalid input is provided.
	 */
	public int displayAppointmentPrompt(int size) {
		System.out.println("Please select an appointment index:");
		int i;
		try {
			i = InputHandler.getChoice(1, size);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i - 1;
	}

	/**
	 * Displays a message indicating that there are no pending appointment requests.
	 */
	public void displayNoPending() {
		System.out.println("No pending Appointment requests.");
	}

	/**
	 * Displays the header for the pending requests view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Pending Requests");
	}
}