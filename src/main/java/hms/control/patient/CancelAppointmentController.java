package hms.control.patient;

import hms.boundary.patient.appointment.CancelAppointmentView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;

/**
 * Controller for managing the cancellation of a patient's scheduled appointments.
 * This class allows a patient to cancel one of their upcoming appointments.
 */
public class CancelAppointmentController extends Controller {
	private CancelAppointmentView cancelAppointmentView;
	private Patient patient;

	/**
	 * Constructs an instance of CancelAppointmentController with the given patient.
	 * Initializes the view for canceling a scheduled appointment.
	 *
	 * @param patient The patient whose scheduled appointments are to be managed.
	 */
	public CancelAppointmentController(Patient patient) {
		this.patient = patient;
		this.cancelAppointmentView = new CancelAppointmentView();
	}

	/**
	 * Navigates through the process of canceling a scheduled appointment.
	 */
	@Override
	public void navigate() {
		this.cancelAppointmentView.displayHeader();
		if (patient.getScheduledAppointmentList().isEmpty()) {
			this.cancelAppointmentView.displayNoAppointments();
		} else {
			int choice = this.cancelAppointmentView.displayAppointments(patient, doctorRepository);
			if (choice == -1) return;

			Appointment appointment = this.patient.getScheduledAppointmentList().get(choice);

			Doctor doctor = doctorRepository.getById(appointment.getDoctorId());
			patient.cancelAppointment(doctor, appointment);
			this.cancelAppointmentView.displayCancelConfirmation();
		}
	}

}
