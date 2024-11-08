package hms.control.patient;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.InputHandler;
import hms.boundary.Prompt;
import hms.boundary.patient.appointment.RescheduleAppointmentView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

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
			try {
				rescheduleAppointment();
			} catch (InvalidChoiceValueException | InvalidChoiceFormatException | InvalidDateException
					| InvalidTimeException e) {
				return;
			}
		}
	}

	private void rescheduleAppointment() throws InvalidChoiceValueException, InvalidChoiceFormatException,
			InvalidDateException, InvalidTimeException {
		this.rescheduleAppointmentView.displayAppointments(this.patient, doctorRepository);
		int choice = 0;
		choice = InputHandler.getChoice(1, this.patient.getScheduledAppointmentList().size());

		Appointment oldAppointment = this.patient.getScheduledAppointmentList().get(choice - 1);

		Prompt.displayDatePrompt();
		LocalDate date = InputHandler.getDate();

		Prompt.displayTimePrompt();
		LocalTime time = InputHandler.getTime();

		Prompt.displayDoctorPrompt();
		this.rescheduleAppointmentView.displayDoctorsAll(doctorRepository);

		choice = InputHandler.getChoice(1, doctorRepository.getAll().size());

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
