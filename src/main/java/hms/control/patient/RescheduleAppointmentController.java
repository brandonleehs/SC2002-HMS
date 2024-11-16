package hms.control.patient;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.Prompt;
import hms.boundary.patient.appointment.RescheduleAppointmentView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;

public class RescheduleAppointmentController extends Controller {
	private RescheduleAppointmentView rescheduleAppointmentView;
	private Patient patient;

	public RescheduleAppointmentController(Patient patient) {
		this.patient = patient;
		this.rescheduleAppointmentView = new RescheduleAppointmentView();
	}

	@Override
	public void navigate() {
		this.rescheduleAppointmentView.displayHeader();
		if (patient.getScheduledAppointmentList().isEmpty()) {
			this.rescheduleAppointmentView.displayNoAppointments();
		} else {
			rescheduleAppointment();
		}
	}

	private void rescheduleAppointment() {
		this.rescheduleAppointmentView.displayAppointments(this.patient, doctorRepository);
		int choice = 		this.rescheduleAppointmentView.displayAppointmentPrompt(this.patient.getScheduledAppointmentList().size());

		Appointment oldAppointment = this.patient.getScheduledAppointmentList().get(choice);

		LocalDate date = Prompt.displayDatePrompt();

		LocalTime time = Prompt.displayTimePrompt();

		Prompt.displayDoctorPrompt();
	
		choice = this.rescheduleAppointmentView.displayDoctorsAll(doctorRepository);

		Doctor newDoctor = doctorRepository.getAll().get(choice - 1);
		Appointment newAppointment = new Appointment(this.patient.getId(), newDoctor.getId(), date, time);
		Doctor oldDoctor = doctorRepository.getById(oldAppointment.getDoctorId());

		if (patient.rescheduleAppointment(oldDoctor, newDoctor, oldAppointment, newAppointment)) {
			this.rescheduleAppointmentView.displayRescheduleSuccess();
		} else {
			this.rescheduleAppointmentView.displayRescheduleFailure();
		}
	}

}
