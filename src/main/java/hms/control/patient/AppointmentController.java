package hms.control.patient;

import hms.boundary.patient.appointment.AppointmentView;
import hms.control.Controller;
import hms.entity.user.Patient;

/**
 * Controller for managing the patient's appointments. This class handles the display of the list of scheduled appointments
 * for a specific patient and allows them to view their upcoming appointments.
 */
public class AppointmentController extends Controller {
	private Patient patient;
	private AppointmentView appointmentView;

	/**
	 * Constructs an instance of AppointmentController with the given patient.
	 * Initializes the view for displaying the patient's scheduled appointments.
	 *
	 * @param patient The patient whose scheduled appointments are to be displayed.
	 */
	public AppointmentController(Patient patient) {
		this.patient = patient;
		this.appointmentView = new AppointmentView();
	}

	/**
	 * Navigates through the process of displaying the patient's scheduled appointments.
	 */
	@Override
	public void navigate() {
		this.appointmentView.displayHeader();
		if (patient.getScheduledAppointmentList().isEmpty()) {
			this.appointmentView.displayNoAppointments();
		} else {
			this.appointmentView.displayAppointments(patient, doctorRepository);
		}
	}

}
