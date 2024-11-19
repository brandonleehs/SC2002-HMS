package hms.control.doctor;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.Prompt;
import hms.control.Controller;
import hms.entity.user.Doctor;

/**
 * Controller class for setting the availability of a doctor for appointments.
 * Facilitates the process of specifying the date and time range during which a doctor is available.
 */
public class SetDoctorAvailabilityController extends Controller {
	private Doctor doctor;

	/**
	 * Constructs a new SetDoctorAvailabilityController with the specified doctor.
	 *
	 * @param doctor the doctor whose availability is being set
	 */
	public SetDoctorAvailabilityController(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Facilitates the process of setting the doctor's availability for appointments.
	 */
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