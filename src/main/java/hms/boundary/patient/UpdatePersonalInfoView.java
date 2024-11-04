package hms.boundary.patient;

import hms.boundary.UserMenuView;
import hms.entity.user.Patient;

public class UpdatePersonalInfoView extends UserMenuView<Patient> {

	public UpdatePersonalInfoView(Patient user) {
		super(user);
	}

	@Override
	public void show() {
		displayBorderedText(WIDTH, "Update Personal Information");
		displayOptions();

		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			System.out.print("Enter phone number: ");
			String phoneNumber = scanner.nextLine();
			user.setPhoneNumber(phoneNumber);
			System.out.println("Phone number updated.");
			break;
		case 2:
			System.out.print("Enter email address: ");
			String emailAddress = scanner.nextLine();
			user.setEmailAddress(emailAddress);
			System.out.println("Email address updated.");
			break;
		default:
			System.out.println("Returning to Patient Menu.");
		}
	}

	@Override
	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. Update Phone Number\r\n" + "2. Update Email Address\r\n"
				+ "3. Return to main menu";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-3):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}

}
