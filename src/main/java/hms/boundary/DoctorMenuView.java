package hms.boundary;

import hms.entity.user.Doctor;

public class DoctorMenuView extends UserMenuView<Doctor> {

	protected DoctorMenuView(Doctor doctor) {
		super(doctor);
	}

	@Override
	public void displayOptions() {
		String options = "Please select an option:\r\n" + "1. View Patient Medical Records\r\n"
				+ "2. Update Patient Medical Records\r\n" + "3. View Personal Schedule\r\n"
				+ "4. Set Availability for Appointments\r\n" + "5. Accept or Decline Appointment Requests\r\n"
				+ "6. View Upcoming Appointments\r\n" + "7. Record Appointment Outcome\r\n" + "8. Logout";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-8):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);
	}

	@Override
	public void show() {
		displayBorderedText(WIDTH, String.format("Welcome, Dr. %s.", user.getName()));
		displayOptions();
	}

}
