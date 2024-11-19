package hms.boundary.pharmacist;

import hms.boundary.InputHandler;
import hms.boundary.StaffView;
import hms.entity.user.Pharmacist;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * View class responsible for rendering the pharmacist's menu and handling user input for menu options.
 * This class interacts with the pharmacist to provide options for viewing appointment records, 
 * updating prescription statuses, managing inventory, and other pharmacist-related functionalities.
 */
public class PharmacistMenuView extends StaffView {
	private Pharmacist pharmacist;

	/**
     * Constructs an instance of PharmacistMenuView.
     * Initializes the view with the pharmacist who is currently logged in.
     *
     * @param pharmacist The logged-in pharmacist.
     */
	public PharmacistMenuView(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	/**
     * Displays the pharmacist menu options and prompts the user to select one.
	 * @return The selected menu option (1-7), or -1 if an invalid input is provided.
	 */
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

	/**
     * Displays the header for the pharmacist menu.
     * Includes a welcome message with the pharmacist's name.
     */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, String.format("Welcome, %s.", pharmacist.getName()));
	}

	/**
     * Prompts the user to input a patient ID.
     * The input is not validated within this method.
     */
	public void displayPatientIdPrompt() {
		System.out.print("Enter patient ID: ");
	}
}
