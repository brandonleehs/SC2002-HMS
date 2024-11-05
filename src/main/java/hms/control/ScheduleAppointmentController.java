package hms.control;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.InputHandler;
import hms.boundary.Prompt;
import hms.boundary.patient.ScheduleAppointmentView;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

public class ScheduleAppointmentController extends Controller {
	private ScheduleAppointmentView scheduleAppointmentView;
	private Patient patient;

	public ScheduleAppointmentController(Patient patient) {
		this.scheduleAppointmentView = new ScheduleAppointmentView();
		this.patient = patient;
	}

	@Override
	public void navigate() {
		try {
			scheduleAppointment();
		} catch (InvalidDateException | InvalidTimeException | InvalidChoiceFormatException
				| InvalidChoiceValueException e) {
			return;
		}
	}

	private void scheduleAppointment() throws InvalidDateException, InvalidTimeException, InvalidChoiceFormatException,
			InvalidChoiceValueException {
		this.scheduleAppointmentView.displayHeader();
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
		this.scheduleAppointmentView.displayDoctorsAll(doctorRepository);

		int choice = InputHandler.getChoice(1, doctorRepository.getAll().size());

		Doctor doctor = doctorRepository.getAll().get(choice - 1);
		Appointment appointment = new Appointment(this.patient.getId(), doctor.getId(), date, time);

		if (patient.scheduleAppointment(doctor, appointment)) {
			this.scheduleAppointmentView.displayScheduleSuccess();
		} else {
			this.scheduleAppointmentView.displayScheduleFailure();
		}
	}
}
