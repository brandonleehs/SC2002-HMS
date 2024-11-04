package hms.boundary;

import hms.boundary.patient.MedicalRecordView;
import hms.boundary.patient.ScheduleAppointmentView;
import hms.boundary.patient.UpdatePersonalInfoView;
import hms.entity.user.Patient;

public class PatientMenuView extends UserMenuView<Patient> {
	public PatientMenuView(Patient patient) {
		super(patient);
	}

	@Override
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
	public void show() {
		int choice = 0;
		do {
			displayBorderedText(WIDTH, String.format("Welcome, %s.", user.getName()));
			displayOptions();
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				MedicalRecordView medicalRecordView = new MedicalRecordView(user);
				medicalRecordView.show();
				break;
			case 2:
				UpdatePersonalInfoView updatePersonalInfoView = new UpdatePersonalInfoView(user);
				updatePersonalInfoView.show();
				break;
			case 3:
				ScheduleView scheduleView = new ScheduleView();
				scheduleView.show();
				break;
			case 4:
				ScheduleAppointmentView scheduleAppointmentView = new ScheduleAppointmentView(user);
				scheduleAppointmentView.show();
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				System.out.println("Logging out.");
				break;
			}
		} while (choice < 10);
	}
}
