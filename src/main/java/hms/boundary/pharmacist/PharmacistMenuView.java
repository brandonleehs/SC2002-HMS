package hms.boundary.pharmacist;

import hms.boundary.InputHandler;
import hms.boundary.StaffView;
import hms.entity.user.Pharmacist;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class PharmacistMenuView extends StaffView {
	private Pharmacist pharmacist;

	public PharmacistMenuView(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public int displayOptions() {
		String options = "Please select an option:\r\n" + "1. View Appointment Outcome Record\r\n"
				+ "2. Update Prescription Status\r\n" + "3. View Medication Inventory\r\n"
				+ "4. Submit Replenishment Request\r\n" + "5. View Replenishment Requests\r\n" 
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
		displayBorderedText(WIDTH, String.format("Welcome, %s.", pharmacist.getName()));
	}

	public void displayPatientIdPrompt() {
		System.out.print("Enter patient ID: ");
	}
}
