package hms.boundary.patient;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.boundary.View;
import hms.entity.user.Doctor;

/**
 * The AvailabilityView class is responsible for displaying a doctor's available
 * appointment slots for a given date. It provides methods to display the
 * availability in a tabular format and relevant headers for the view.
 */
public class AvailabilityView extends View {

	/**
	 * Displays the given date in the console.
	 * 
	 * @param date the date to display.
	 */
	public void displayDate(LocalDate date) {
		System.out.println("Date: " + date);
	}

	/**
	 * Displays a table of a doctor's available appointment slots for a specific
	 * date. Each available time slot is listed along with the date and an
	 * "Available" status.
	 * 
	 * @param doctor the doctor whose availability is to be displayed.
	 * @param date   the date for which availability is shown.
	 */
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

	/**
	 * Displays the header for the "Available Appointment Slots" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Available Appointment Slots");
	}
}
