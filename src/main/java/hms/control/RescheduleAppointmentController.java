package hms.control;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.InputHandler;
import hms.boundary.Prompt;
import hms.boundary.patient.RescheduleAppointmentView;
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
			this.rescheduleAppointmentView.displayAppointments(this.patient, doctorRepository);
			int choice = InputHandler.getChoice();
			Appointment oldAppointment = this.patient.getScheduledAppointmentList().get(choice - 1);

			Prompt.displayDatePrompt();
			LocalDate date = InputHandler.getDate();

			if (date == null) {
				return;
			}

			Prompt.displayTimePrompt();
			LocalTime time = InputHandler.getTime();

			if (time == null) {
				return;
			}

			Prompt.displayDoctorPrompt();
			this.rescheduleAppointmentView.displayDoctorsAll(doctorRepository);

			choice = InputHandler.getChoice();
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

}
