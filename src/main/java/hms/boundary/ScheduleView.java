package hms.boundary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import hms.entity.user.Doctor;
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
		for (Doctor doctor : doctorRepository.getAll()) {
			System.out.println(String.format("Doctor: Dr. %s", doctor.getName()));
			System.out.println("|" + "-".repeat(WIDTH - 2) + "|");
			String format = "| %-" + 10 + "s | %-" + 5 + "s | %-" + (WIDTH - 25) + "s |\n";
			System.out.printf(format, "Date", "Time", "Availability");
			System.out.println("|" + "-".repeat(WIDTH - 2) + "|");
			for (LocalTime time : doctor.getAvailability(date)) {
				System.out.printf(format, date, time, "Available");
			}
			System.out.println();
		}
	}
}
