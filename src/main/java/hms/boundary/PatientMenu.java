package hms.boundary;

public class PatientMenu extends Menu {
	// Defines the width of header display
	private static final int WIDTH = 50;

	@Override
	public void displayMenu(String name) {
		displayHeader(name);
		displayOptions();
	}

	@Override
	public void displayHeader(String name) {
		displayBorderedText(WIDTH, String.format("Welcome, %s.", name));
	}

	@Override
	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. View Patient Medical Records\r\n"
				+ "2. Update Patient Medical Records\r\n" + "3. View Personal Schedule\r\n"
				+ "4. Set Availability for Appointments\r\n" + "5. Accept or Decline Appointment Requests\r\n"
				+ "6. View Upcoming Appointments\r\n" + "7. Record Appointment Outcome\r\n" + "8. Logout";
		String prompt = "Enter choice (1-8):";
		String border = "=".repeat(WIDTH);
		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}
}
