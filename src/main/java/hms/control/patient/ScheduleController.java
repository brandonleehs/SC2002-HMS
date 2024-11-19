package hms.control.patient;

import java.time.LocalDate;

import hms.boundary.Prompt;
import hms.boundary.patient.AvailabilityView;
import hms.control.Controller;
import hms.entity.user.Doctor;

/**
 * Controller for displaying the availability of doctors on a specific date.
 * This class allows the patient to view the schedule and availability of doctors for a given date.
 */
public class ScheduleController extends Controller {
	private final AvailabilityView scheduleView;

	/**
	 * Constructs an instance of ScheduleController, initializing the view for displaying doctor's availability.
	 */
	public ScheduleController() {
		this.scheduleView = new AvailabilityView();
	}

	/**
	 * Navigates through the process of displaying doctor availability for a specific date.
	 */
	@Override
	public void navigate() {
		LocalDate date = Prompt.displayDatePrompt();
		if (date == null) return;
		
		this.scheduleView.displayHeader();
		this.scheduleView.displayDate(date);

		for (Doctor doctor : doctorRepository.getAll()) {
			this.scheduleView.displayAvailabilityTable(doctor, date);
		}
	}

}
