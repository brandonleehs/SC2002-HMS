package hms.control.patient;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.Prompt;
import hms.boundary.patient.appointment.ScheduleAppointmentView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;

/**
 * Controller class for scheduling an appointment for a patient.
 * Handles the user input for selecting a date, time, and doctor, and then attempts to schedule the appointment.
 */
public class ScheduleAppointmentController extends Controller {
	private ScheduleAppointmentView scheduleAppointmentView;
	private Patient patient;

	/**
	 * Constructs a new ScheduleAppointmentController with the specified patient.
	 *
	 * @param patient the patient for whom the appointment is being scheduled
	 */
	public ScheduleAppointmentController(Patient patient) {
		this.scheduleAppointmentView = new ScheduleAppointmentView();
		this.patient = patient;
	}

	/**
	 * Displays the prompts for the patient to schedule an appointment.
	 * This includes selecting a date, time, and doctor, then attempts to create and schedule the appointment.
	 * If the scheduling is successful, a success message is displayed; otherwise, a failure message is shown.
	 */
	@Override
	public void navigate() {
		this.scheduleAppointmentView.displayHeader();

		LocalDate date = Prompt.displayDatePrompt();

		if (date == null) {
			return;
		}

		LocalTime time = Prompt.displayTimePrompt();

		if (time == null) {
			return;
		}

		Prompt.displayDoctorPrompt();

		int choice = this.scheduleAppointmentView.displayDoctorsAll(doctorRepository);

		if (choice == -1) {
			return;
		}

		Doctor doctor = doctorRepository.getAll().get(choice);
		Appointment appointment = new Appointment(this.patient.getId(), doctor.getId(), date, time);

		if (patient.scheduleAppointment(doctor, appointment)) {
			this.scheduleAppointmentView.displayScheduleSuccess();
		} else {
			this.scheduleAppointmentView.displayScheduleFailure();
		}
	}
}
