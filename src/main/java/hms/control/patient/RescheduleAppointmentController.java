package hms.control.patient;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.Prompt;
import hms.boundary.patient.appointment.RescheduleAppointmentView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;

/**
 * Controller for managing the rescheduling of a patient's appointments.
 * This class allows a patient to reschedule an existing appointment by selecting a new date, time, and doctor.
 */
public class RescheduleAppointmentController extends Controller {
	private RescheduleAppointmentView rescheduleAppointmentView;
	private Patient patient;

	/**
	 * Constructs an instance of RescheduleAppointmentController with the given patient.
	 * Initializes the view for rescheduling appointments.
	 *
	 * @param patient The patient whose appointment is to be rescheduled.
	 */
	public RescheduleAppointmentController(Patient patient) {
		this.patient = patient;
		this.rescheduleAppointmentView = new RescheduleAppointmentView();
	}

	/**
	 * Navigates through the process of rescheduling a patient's appointment.
	 */
	@Override
	public void navigate() {
		this.rescheduleAppointmentView.displayHeader();
		if (patient.getScheduledAppointmentList().isEmpty()) {
			this.rescheduleAppointmentView.displayNoAppointments();
		} else {
			rescheduleAppointment();
		}
	}

	/**
	 * Handles the process of rescheduling an existing appointment.
	 */
	private void rescheduleAppointment() {
		this.rescheduleAppointmentView.displayAppointments(this.patient, doctorRepository);
		int choice = this.rescheduleAppointmentView
				.displayAppointmentPrompt(this.patient.getScheduledAppointmentList().size());

		if (choice == -1) {
			return;
		}

		Appointment oldAppointment = this.patient.getScheduledAppointmentList().get(choice);

		LocalDate date = Prompt.displayDatePrompt();

		LocalTime time = Prompt.displayTimePrompt();

		Prompt.displayDoctorPrompt();

		choice = this.rescheduleAppointmentView.displayDoctorsAll(doctorRepository);

		if (choice == -1) {
			return;
		}

		Doctor newDoctor = doctorRepository.getAll().get(choice);
		Appointment newAppointment = new Appointment(this.patient.getId(), newDoctor.getId(), date, time);
		Doctor oldDoctor = doctorRepository.getById(oldAppointment.getDoctorId());

		if (patient.rescheduleAppointment(oldDoctor, newDoctor, oldAppointment, newAppointment)) {
			this.rescheduleAppointmentView.displayRescheduleSuccess();
		} else {
			this.rescheduleAppointmentView.displayRescheduleFailure();
		}
	}

}
