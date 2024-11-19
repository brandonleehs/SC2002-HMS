package hms.boundary.patient.appointment;

/**
 * The ScheduleAppointmentView class extends AppointmentView to provide
 * functionality for scheduling new appointments. It includes methods for
 * displaying the scheduling header and providing feedback on the scheduling
 * outcome.
 */
public class ScheduleAppointmentView extends AppointmentView {
	/**
	 * Displays the header for the "Schedule Appointment" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Schedule Appointment");
	}

	/**
	 * Displays a success message indicating that the appointment was scheduled
	 * successfully.
	 */
	public void displayScheduleSuccess() {
		System.out.println("Appointment scheduled.");
	}

	/**
	 * Displays a failure message indicating that the scheduling attempt was
	 * unsuccessful.
	 */
	public void displayScheduleFailure() {
		System.out.println("Appointment unavailable. Please check availability again.");
	}
}
