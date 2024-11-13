package hms.control.doctor;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.InputHandler;
import hms.boundary.Prompt;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

public class SetDoctorAvailabilityController extends Controller {
	private Doctor doctor;

	public SetDoctorAvailabilityController(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public void navigate() {
		Prompt.displayDatePrompt();
		LocalDate date;
		try {
			date = InputHandler.getDate();
		} catch (InvalidDateException e) {
			return;
		}

		Prompt.displayStartTimePrompt();
		LocalTime startTime, endTime;
		try {
			startTime = InputHandler.getTime();
		} catch (InvalidTimeException e) {
			return;
		}
		Prompt.displayEndTimePrompt();
		try {
			endTime = InputHandler.getTime();
		} catch (InvalidTimeException e) {
			return;
		}

		doctor.setAvailability(date, startTime, endTime);
	}
}