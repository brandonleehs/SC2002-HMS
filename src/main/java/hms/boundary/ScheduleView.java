package hms.boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hms.entity.user.Patient;

public class ScheduleView extends UserMenuView<Patient> {

	public ScheduleView(Patient user) {
		super(user);
	}

	@Override
	public void displayOptions() {

	}

	@Override
	public void show() {
		String prompt = "Please enter a date (YYYY-MM-DD): ";
		System.out.print(prompt);
		String input = scanner.nextLine();
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(input, dateformatter);

		displayBorderedText(WIDTH, "Available Appointment Slots");
		System.out.println("Date: " + date);
	}
}
