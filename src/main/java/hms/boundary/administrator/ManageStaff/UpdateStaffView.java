package hms.boundary.administrator.ManageStaff;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class UpdateStaffView extends View {
	int choice;

	public UpdateStaffView() {
		choice = 0;
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Edit Staff");
	}

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

	public String getID() {
		System.out.println("Enter User ID of Staff:");
		return InputHandler.getString();
	}

	public void staffDoesNotExist() {
		System.out.println("Staff does not exist");
	}

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

	public int getAge() {
		try {
			System.out.println("Enter Age: ");
			this.choice = InputHandler.getChoice(10, 110);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

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

	public void updateDoctorSuccessful() {
		System.out.println("Doctor updated successfully.");
	}

	public void updatePharmacistSuccessful() {
		System.out.println("Pharmacist updated successfully.");
	}

	public void updateReceptionistSuccessful() {
		System.out.println("Receptionist updated successfully.");
	}
}
