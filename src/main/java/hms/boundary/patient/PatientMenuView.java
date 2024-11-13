package hms.boundary.patient;

import hms.boundary.View;
import hms.entity.user.Patient;

public class PatientMenuView extends View {
	private Patient patient;

	public PatientMenuView(Patient patient) {
		this.patient = patient;
	}

	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. View Medical Record\r\n"
				+ "2. Update Personal Information\r\n" + "3. View Available Appointment Slots\r\n"
				+ "4. Schedule an Appointment\r\n" + "5. Reschedule an Appointment\r\n" + "6. Cancel an Appointment\r\n"
				+ "7. View Scheduled Appointments\r\n" + "8. View Past Appointment Outcome Records\r\n"
				+ "9. Change Password\r\n" + "10. Logout";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-10):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, String.format("Welcome, %s.", patient.getName()));
	}
}
