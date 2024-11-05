package hms.boundary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
	private static final Scanner scanner = new Scanner(System.in);

	public static Integer getChoice() {

		try {
			int choice = scanner.nextInt();
			scanner.nextLine();
			return choice;
		} catch (InputMismatchException e) {
			ErrorMessage.displayInvalidChoiceFormat();
			scanner.nextLine(); // Clear buffer!
		}
		return null;
	}

	public static String getString() {
		return scanner.nextLine();
	}

	public static LocalDate getDate() {
		String dateString = scanner.nextLine();
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate date = LocalDate.parse(dateString, dateformatter);
			return date;
		} catch (DateTimeParseException e) {
			ErrorMessage.displayInvalidDateError();
			return null;
		}
	}

	public static LocalTime getTime() {
		String timeString = scanner.nextLine();
		try {
			LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_TIME);
			return time;
		} catch (DateTimeParseException e) {
			ErrorMessage.displayInvalidTimeError();
			return null;
		}
	}
}
