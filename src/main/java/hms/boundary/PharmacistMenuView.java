package hms.boundary;

import hms.entity.user.Pharmacist;

public class PharmacistMenuView extends View {
	private Pharmacist pharmacist;

	protected PharmacistMenuView(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. View Appointment Outcome Record\r\n"
				+ "2. Update Prescription Status\r\n" + "3. View Medication Inventory\r\n"
				+ "4. Submit Replenishment Request\r\n" + "5. Logout";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-5):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, String.format("Welcome, %s.", pharmacist.getName()));
	}

//	@Override
//	public void show() {
//		displayBorderedText(WIDTH, String.format("Welcome, %s.", user.getName()));
//		displayOptions();
//	}

}
