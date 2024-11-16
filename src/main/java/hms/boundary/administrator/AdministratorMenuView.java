package hms.boundary.administrator;

import hms.boundary.InputHandler;
import hms.boundary.StaffView;
import hms.entity.user.Administrator;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class AdministratorMenuView extends StaffView {
	private final Administrator administrator;

	public AdministratorMenuView(Administrator administrator) {
		this.administrator = administrator;
	}

	public int displayOptions() {
		String options = "Please select an option:\r\n" + "1. Manage Hospital Staff\r\n"
                + "2. View Appointment Details\r\n" + "3. Manage Medication Inventory\r\n"
                + "4. Approve Replenishment Requests\r\n" + "5. Reset User Password\r\n"
				+ "6. Change Password\r\n" + "7. Logout";

		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-7):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);

		int choice;
        try{
            choice = InputHandler.getChoice(1, 7);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return choice;
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, String.format("Welcome, Administrator %s.", administrator.getName()));
	}

	public void displayPatientIdPrompt() {
		System.out.print("Enter patient ID: ");
	}
}