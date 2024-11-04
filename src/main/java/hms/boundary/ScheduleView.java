package hms.boundary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import hms.entity.user.Doctor;

public class ScheduleView extends View {

	@Override
	public void show() {
		System.out.print("Please enter a date (YYYY-MM-DD): ");
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
