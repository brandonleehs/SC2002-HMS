package hms.control.patient;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.Prompt;
import hms.boundary.patient.appointment.ScheduleAppointmentView;
import hms.control.Controller;
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
		
		LocalDate date = Prompt.displayDatePrompt();

		
		LocalTime time = Prompt.displayTimePrompt();

		Prompt.displayDoctorPrompt();
		
		int choice = this.scheduleAppointmentView.displayDoctorsAll(doctorRepository);

		Doctor doctor = doctorRepository.getAll().get(choice - 1);
		Appointment appointment = new Appointment(this.patient.getId(), doctor.getId(), date, time);

		if (patient.scheduleAppointment(doctor, appointment)) {
			this.scheduleAppointmentView.displayScheduleSuccess();
		} else {
			this.scheduleAppointmentView.displayScheduleFailure();
		}
	}
}
