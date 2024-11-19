package hms.boundary.administrator.ManageStaff;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The view class for adding new staff members (Doctor, Pharmacist,
 * Receptionist) in the system. This class provides prompts for the user to
 * input details for each role and displays success or error messages.
 */
public class AddStaffView extends View {
	private int choice;

	public AddStaffView() {
		this.choice = 0;
	}

	/**
	 * Prompts the administrator to choose the role of the staff member to be added.
	 * 
	 * @return The selected role choice as an integer (1 for Doctor, 2 for
	 *         Pharmacist, 3 for Receptionist, 4 for Cancel).
	 */
	public int getRoleChoice() {
		System.out.println("Choose role (Doctor/Pharmacist/Receptionist): ");
		System.out.println("1: Doctor\r\n2: Pharmacist\r\n3: Receptionist\r\n4: Cancel");
		choice = 0;
		try {
			choice = InputHandler.getChoice(1, 4);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return 4;
		}
		return choice;
	}

	/**
	 * Prompts the administrator to enter the details (ID, Name, Gender) for the new
	 * staff member.
	 * 
	 * @return A list containing the ID, Name, and Gender ("M" for Male, "F" for
	 *         Female) of the staff member.
	 * @throws InvalidChoiceFormatException If the input format is invalid.
	 * @throws InvalidChoiceValueException  If the input value is invalid.
	 */
	public List<String> getDetails() throws InvalidChoiceFormatException, InvalidChoiceValueException {
		List<String> returnDetails = new ArrayList<>();
		choice = -1;
		System.out.println("Enter ID: ");
		returnDetails.add(InputHandler.getString());
		System.out.println("Enter Name: ");
		returnDetails.add(InputHandler.getString());
		System.out.println("Enter Gender: ");
		System.out.println("1: Male");
		System.out.println("2: Female");
		choice = InputHandler.getChoice(1, 2);
		if (choice == 1) {
			returnDetails.add("M");
		} else {
			returnDetails.add("F");
		}
		return returnDetails;
	}

	/**
	 * Prompts the administrator to enter the age of the staff member.
	 * 
	 * @return The entered age if valid, or -1 if the input is invalid.
	 */
	public int getAge() {
		try {
			System.out.println("Enter Age: ");
			this.choice = InputHandler.getChoice(10, 110);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Displays a message confirming that a Doctor has been successfully added.
	 */
	public void addDoctorSuccessful() {
		System.out.println("Doctor added successfully.");
	}

	/**
	 * Displays a message confirming that a Pharmacist has been successfully added.
	 */
	public void addPharmacistSuccessful() {
		System.out.println("Pharmacist added successfully.");
	}

	/**
	 * Displays a message confirming that a Receptionist has been successfully
	 * added.
	 */
	public void addReceptionistSuccessful() {
		System.out.println("Receptionist added successfully.");
	}

	/**
	 * Displays a message indicating that the user already exists in the system.
	 */
	public void userExists() {
		System.out.println("User already exists!");
	}

	/**
	 * Displays the header for the "Add Staff" section with a bordered text.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Add Staff");
	}
}
