package hms.boundary.administrator.ManageStaff;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class AddStaffView extends View {
	private int choice;

	public AddStaffView() {
		this.choice = 0;
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

	public int getAge() {
		try {
			System.out.println("Enter Age: ");
			this.choice = InputHandler.getChoice(10, 110);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	public void addDoctorSuccessful() {
		System.out.println("Doctor added successfully.");
	}

	public void addPharmacistSuccessful() {
		System.out.println("Pharmacist added successfully.");
	}

	public void addReceptionistSuccessful() {
		System.out.println("Receptionist added successfully.");
	}

	public void userExists() {
		System.out.println("User already exists!");
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Add Staff");
	}
}
