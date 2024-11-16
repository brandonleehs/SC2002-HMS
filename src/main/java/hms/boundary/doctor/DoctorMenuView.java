package hms.boundary.doctor;

import hms.boundary.InputHandler;
import hms.boundary.StaffView;
import hms.entity.user.Doctor;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class DoctorMenuView extends StaffView {
	private final Doctor doctor;

	public DoctorMenuView(Doctor doctor) {
		this.doctor = doctor;
	}

	public int displayOptions() {
		String options = "Please select an option:\r\n" + "1. View Patient Medical Records\r\n"
				+ "2. Update Patient Medical Records\r\n" + "3. View Personal Schedule\r\n"
				+ "4. Set Availability for Appointments\r\n" + "5. Accept or Decline Appointment Requests\r\n"
				+ "6. View Upcoming Appointments\r\n" + "7. Record Appointment Outcome\r\n" + "8. Change Password\r\n"
				+ "9. Logout";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-9):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);

		int choice;
        try{
            choice = InputHandler.getChoice(1, 9);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return choice;
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, String.format("Welcome, Dr. %s.", doctor.getName()));
	}
}
