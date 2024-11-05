package hms.boundary.patient;

import hms.boundary.View;

public class UpdatePersonalInfoView extends View {

//	public String getPhoneNumber() {
//		return scanner.nextLine();
//	}

//	public String getEmailAddress() {
//		return scanner.nextLine();
//	}

	public void displayPhoneNumberUpdate() {
		System.out.println("Phone number updated.");
	}

	public void displayEmailAddressUpdate() {
		System.out.println("Email address updated.");
	}

	public void displayReturnMenu() {
		System.out.println("Returning to Menu.");
	}

//	@Override
//	public void show() {
//		displayBorderedText(WIDTH, "Update Personal Information");
//		displayOptions();
//
//		int choice = scanner.nextInt();
//		scanner.nextLine();
//		switch (choice) {
//		case 1:
//			System.out.print("Enter phone number: ");
//			String phoneNumber = scanner.nextLine();
//			user.setPhoneNumber(phoneNumber);
//			System.out.println("Phone number updated.");
//			break;
//		case 2:
//			System.out.print("Enter email address: ");
//			String emailAddress = scanner.nextLine();
//			user.setEmailAddress(emailAddress);
//			System.out.println("Email address updated.");
//			break;
//		default:
//			System.out.println("Returning to Patient Menu.");
//		}
//	}

	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. Update Phone Number\r\n" + "2. Update Email Address\r\n"
				+ "3. Return to main menu";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-3):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Update Personal Information");
	}

}
