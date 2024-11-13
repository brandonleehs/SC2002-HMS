package hms.boundary.administrator;

import hms.boundary.View;
import hms.entity.user.Administrator;
import java.util.Scanner;

public class AdministratorMenuView extends View {
	private final Administrator administrator;

	public AdministratorMenuView(Administrator administrator) {
		this.administrator = administrator;
	}

	public void displayOptions() {
		String options = """
                Please select an option:
                1. Manage Hospital Staff
                2. View Appointment Details
                3. Manage Medication Inventory
                4. Approve Replenishment Requests
                5. Logout
                """;

		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-5):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, String.format("Welcome, Administrator %s.", administrator.getName()));
	}

	public int getUserChoice() {
		Scanner scanner = new Scanner(System.in);
		int choice = -1;
		try {
			choice = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
		}
		return choice;
	}
}
