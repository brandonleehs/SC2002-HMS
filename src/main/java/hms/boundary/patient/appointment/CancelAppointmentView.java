package hms.boundary.patient.appointment;

import hms.boundary.InputHandler;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.repository.DoctorRepository;

/**
 * The CancelAppointmentView class extends AppointmentView to provide
 * functionality for canceling appointments. It includes methods to display
 * scheduled appointments for cancellation, confirm cancellation, and handle
 * scenarios with no appointments available.
 */
public class CancelAppointmentView extends AppointmentView {
	/**
	 * Displays the list of appointments for the specified patient, allowing them to
	 * select one for cancellation.
	 *
	 * @param patient          the patient whose appointments are displayed.
	 * @param doctorRepository the repository to fetch doctor information for the
	 *                         appointments.
	 * @return the index of the appointment to cancel (0-based), or -1 if an invalid
	 *         choice is made.
	 */
	@Override
	public int displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		super.displayAppointments(patient, doctorRepository);
		System.out.println("Please choose an appointment to cancel: ");
		int i;
		try {
			i = InputHandler.getChoice(1, patient.getScheduledAppointmentList().size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return i - 1;
	}

	/**
	 * Displays a message indicating that there are no appointments available for
	 * cancellation.
	 */
	@Override
	public void displayNoAppointments() {
		System.out.println("No appointments to cancel.");
	}

	/**
	 * Displays the header for the "Cancel Appointment" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Cancel Appointment");
	}

	/**
	 * Displays a confirmation message after successfully canceling an appointment.
	 */
	public void displayCancelConfirmation() {
		System.out.println("Appointment has been cancelled.");
	}
}
