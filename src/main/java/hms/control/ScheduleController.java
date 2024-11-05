package hms.control;

import java.time.LocalDate;

import hms.boundary.InputHandler;
import hms.boundary.Prompt;
import hms.boundary.patient.ScheduleView;
import hms.entity.user.Doctor;

public class ScheduleController extends Controller {
	private final ScheduleView scheduleView;

	public ScheduleController() {
		this.scheduleView = new ScheduleView();
	}

	@Override
	public void navigate() {
		Prompt.displayDatePrompt();
		LocalDate date = InputHandler.getDate();

		this.scheduleView.displayHeader();
		this.scheduleView.displayDate(date);

		for (Doctor doctor : doctorRepository.getAll()) {
			this.scheduleView.displayAvailabilityTable(doctor, date);
		}
	}

}
