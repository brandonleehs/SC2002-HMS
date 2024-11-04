package hms.boundary;

import hms.entity.user.Administrator;

public class AdministratorMenuView extends UserMenuView<Administrator> {

	protected AdministratorMenuView(Administrator administrator) {
		super(administrator);
	}

	@Override
	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. View and Manage Hospital Staff\r\n"
				+ "2. View Appointments Details\r\n" + "3. View and Manage Medication Inventory\r\n"
				+ "4. Approve Replenishment Requests\r\n" + "5. Logout";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-5):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}

	@Override
	public void show() {
		displayBorderedText(WIDTH, String.format("Welcome, %s.", user.getName()));
		displayOptions();
	}

}
