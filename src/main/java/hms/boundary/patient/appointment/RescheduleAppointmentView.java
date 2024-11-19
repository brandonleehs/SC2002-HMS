package hms.boundary.patient.appointment;

import hms.boundary.InputHandler;
import hms.entity.appointment.Appointment;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.repository.DoctorRepository;

/**
 * The RescheduleAppointmentView class extends AppointmentView to provide
 * functionality for rescheduling appointments. It includes methods for
 * displaying scheduled appointments, prompting the user for rescheduling, and
 * providing feedback on rescheduling outcomes.
 */
public class RescheduleAppointmentView extends AppointmentView {
	/**
	 * Displays the list of scheduled appointments for the specified patient.
	 *
	 * @param patient          the patient whose appointments are displayed.
	 * @param doctorRepository the repository to fetch doctor information for the
	 *                         appointments.
	 * @return 0
	 */
	@Override
	public int displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 13 + "s | %-" + (WIDTH - 49) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Doctor Name");

		for (int i = 0; i < patient.getScheduledAppointmentList().size(); i++) {
			Appointment appointment = patient.getScheduledAppointmentList().get(i);
			System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(),
					appointment.getAppointmentStatus(), doctorRepository.getById(appointment.getDoctorId()).getName());
		}
		System.out.println();
		return 0;
	}

	/**
	 * Prompts the user to select an appointment by its index for rescheduling.
	 *
	 * @param size the number of available appointments to choose from.
	 * @return the index of the selected appointment (0-based), or -1 if an invalid
	 *         choice is made.
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
	 * Displays the header for the "Reschedule Appointment" view.
	 */
	public void displayHeader() {
		displayBorderedText(WIDTH, "Reschedule Appointment");
	}

	/**
	 * Displays a success message indicating that the appointment was rescheduled
	 * successfully.
	 */
	public void displayRescheduleSuccess() {
		System.out.println("Appointment rescheduled.");
	}

	/**
	 * Displays a failure message indicating that the rescheduling attempt was
	 * unsuccessful.
	 */
	public void displayRescheduleFailure() {
		System.out.println("Appointment unavailable. Please check availability again.");
	}

	/**
	 * Displays a message indicating that there are no appointments available for
	 * rescheduling.
	 */
	public void displayNoAppointments() {
		System.out.println("No appointments to reschedule.");
	}

}
