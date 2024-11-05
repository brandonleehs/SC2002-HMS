package hms.control;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.Prompt;
import hms.boundary.InputHandler;
import hms.boundary.patient.ScheduleAppointmentView;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;

public class ScheduleAppointmentController extends Controller {
	private ScheduleAppointmentView scheduleAppointmentView;
	private Patient patient;

	public ScheduleAppointmentController(Patient patient) {
		this.scheduleAppointmentView = new ScheduleAppointmentView();
		this.patient = patient;
	}

	@Override
	public void navigate() {
		this.scheduleAppointmentView.displayHeader();
		Prompt.displayDatePrompt();
		LocalDate date = InputHandler.getDate();

		Prompt.displayTimePrompt();
		LocalTime time = InputHandler.getTime();

		Prompt.displayDoctorPrompt();
		this.scheduleAppointmentView.displayDoctorsAll(doctorRepository);

		int choice = InputHandler.getChoice();
		Doctor doctor = doctorRepository.getAll().get(choice - 1);
		Appointment appointment = new Appointment(this.patient.getId(), doctor.getId(), date, time);

		if (patient.scheduleAppointment(doctor, appointment)) {
			this.scheduleAppointmentView.displayScheduleSuccess();
		} else {
			this.scheduleAppointmentView.displayScheduleFailure();
		}
	}
}
