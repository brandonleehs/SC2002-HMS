package hms.boundary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputHandler {
	private static final Scanner scanner = new Scanner(System.in);

	public static int getChoice() {
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}

	public static String getString() {
		return scanner.nextLine();
	}

	public static LocalDate getDate() {
		String dateString = scanner.nextLine();
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dateString, dateformatter);
		return date;
	}

	public static LocalTime getTime() {
		String timeString = scanner.nextLine();
		LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_TIME);
		return time;
	}
}
