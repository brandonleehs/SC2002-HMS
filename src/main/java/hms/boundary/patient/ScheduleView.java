package hms.boundary.patient;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.View;
import hms.entity.user.Doctor;

public class ScheduleView extends View {

	public void displayDate(LocalDate date) {
		System.out.println("Date: " + date);
	}

	public void displayAvailabilityTable(Doctor doctor, LocalDate date) {
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

	public void displayBorder() {

	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Available Appointment Slots");
	}
}
