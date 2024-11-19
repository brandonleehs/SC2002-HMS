package hms.boundary.administrator.ManageStaff;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The UpdateStaffView class handles the user interface for updating hospital
 * staff information. It prompts the administrator to select a staff role, enter
 * a user ID, update the staff details, and display appropriate success or error
 * messages for staff updates.
 */
public class UpdateStaffView extends View {
	int choice;

	public UpdateStaffView() {
		choice = 0;
	}

	/**
	 * Displays the header for the Edit Staff view. It provides a bordered text
	 * representation with the title "Edit Staff".
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Edit Staff");
	}

	/**
	 * Prompts the user to choose a role for the staff to be updated. The available
	 * choices are Doctor, Pharmacist, Receptionist, or Cancel.
	 * 
	 * @return the user's choice as an integer (1 for Doctor, 2 for Pharmacist, 3
	 *         for Receptionist, 4 for Cancel).
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
	 * Prompts the user to enter the ID of the staff member to be updated.
	 * 
	 * @return the user ID entered by the administrator as a String.
	 */
	public String getID() {
		System.out.println("Enter User ID of Staff:");
		return InputHandler.getString();
	}

	/**
	 * Displays an error message indicating that the staff member does not exist.
	 */
	public void staffDoesNotExist() {
		System.out.println("Staff does not exist");
	}

	/**
	 * Prompts the user to enter the updated details of the staff member, including
	 * the name and gender. It loops until valid data is entered.
	 * 
	 * @return a list of updated staff details (name and gender) as Strings.
	 */
	public List<String> getDetails() {
		List<String> returnDetails = new ArrayList<>();
		List<String> dupe = new ArrayList<>();
		int loop;
		do {
			try {
				choice = -1;
				System.out.println("Enter Name: ");
				returnDetails.add(InputHandler.getString());
				System.out.println("Enter Gender: ");
				System.out.println("1: Male");
				System.out.println("2: Female");
				choice = (InputHandler.getChoice(1, 2));
				if (choice == 1) {
					returnDetails.add("M");
				} else {
					returnDetails.add("F");
				}
				loop = 1;
			} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return dupe;
			}
		} while (loop != 1);
		return returnDetails;
	}

	/**
	 * Prompts the user to enter the updated age of the staff member.
	 * 
	 * @return the updated age as an integer.
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
	 * Displays the updated details of the staff member and prompts the user to
	 * confirm or cancel the changes.
	 * 
	 * @param details the updated details of the staff member.
	 * @param age     the updated age of the staff member.
	 * @return the user's choice (1 to confirm, 2 to cancel).
	 */
	public int printDetails(List<String> details, int age) {
		try {
			System.out.println("Check that entered details are correct:");
			System.out.println("Name: " + details.get(0));
			System.out.println("Gender: " + details.get(1));
			System.out.println("Age: " + age);
			System.out.println("Enter 1 to confirm, 2 to cancel");
			choice = InputHandler.getChoice(1, 2);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return 2;
		}
		return choice;

	}

	/**
	 * Displays a success message indicating that the doctor update was successful.
	 */
	public void updateDoctorSuccessful() {
		System.out.println("Doctor updated successfully.");
	}

	/**
	 * Displays a success message indicating that the pharmacist update was
	 * successful.
	 */
	public void updatePharmacistSuccessful() {
		System.out.println("Pharmacist updated successfully.");
	}

	/**
	 * Displays a success message indicating that the receptionist update was
	 * successful.
	 */
	public void updateReceptionistSuccessful() {
		System.out.println("Receptionist updated successfully.");
	}
}
