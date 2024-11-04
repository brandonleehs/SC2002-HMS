package hms.boundary;

import hms.boundary.patient.PatientService;
import hms.entity.user.Patient;

public class PatientMenu extends UserMenu<Patient> {
	public PatientMenu(Patient patient) {
		super(patient);
	}

	@Override
	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. View Medical Record\r\n"
				+ "2. Update Personal Information\r\n" + "3. View Available Appointment Slots\r\n"
				+ "4. Schedule an Appointment\r\n" + "5. Reschedule an Appointment\r\n" + "6. Cancel an Appointment\r\n"
				+ "7. View Scheduled Appointments\r\n" + "8. View Past Appointment Outcome Records\r\n" + "9. Logout";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-9):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}

	@Override
	public void show() {
		displayBorderedText(WIDTH, String.format("Welcome, %s.", user.getName()));
		displayOptions();

		PatientService patientService = new PatientService(user);
		int choice = 0;
		do {
			choice = scanner.nextInt();
			scanner.nextLine();
			patientService.renderService(choice);
		} while (choice < 10);
	}
}
