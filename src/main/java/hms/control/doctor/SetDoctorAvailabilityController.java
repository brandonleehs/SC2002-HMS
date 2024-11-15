package hms.control.doctor;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.Prompt;
import hms.control.Controller;
import hms.entity.user.Doctor;

public class SetDoctorAvailabilityController extends Controller {
	private Doctor doctor;

	public SetDoctorAvailabilityController(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public void navigate() {
		LocalDate date = Prompt.displayDatePrompt();
		if (date == null) return;

		LocalTime startTime = Prompt.displayStartTimePrompt();
		if (startTime == null) return;

		LocalTime endTime = Prompt.displayEndTimePrompt();
		if (endTime == null) return;

		doctor.setAvailability(date, startTime, endTime);
	}
}