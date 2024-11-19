package hms.control.patient;

import hms.boundary.patient.PatientMenuView;
import hms.boundary.patient.record.MedicalRecordView;
import hms.control.MenuController;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Patient;
import hms.entity.user.User;

/**
 * Controller class for managing the patient's menu and interactions with the system.
 * Facilitates navigation between different options available to a patient, such as viewing medical records, scheduling appointments, and changing personal information.
 */
public class PatientMenuController extends MenuController {
	private final PatientMenuView patientMenuView;
	private Patient patient;

	/**
	 * Constructs a new PatientMenuController with the specified patient.
	 *
	 * @param patient the patient for whom the menu options are being displayed
	 */
	public PatientMenuController(Patient patient) {
		this.patient = patient;
		this.patientMenuView = new PatientMenuView(patient);
	}

	/**
	 * Displays the menu options for the patient and handles the navigation between different functionalities.
	 */
	@Override
	public void navigate() {
		checkNewUser((User)patient);
		int choice = 0;
		do {
			this.patientMenuView.displayHeader();
			choice = this.patientMenuView.displayOptions();

			switch (choice) {
			case 1:
				MedicalRecordView medicalRecordView = new MedicalRecordView(patient);
				medicalRecordView.displayMedicalRecord();
				break;
			case 2:
				UpdatePersonalInfoController updatePersonalInfoController = new UpdatePersonalInfoController(patient);
				updatePersonalInfoController.navigate();
				break;
			case 3:
				ScheduleController scheduleController = new ScheduleController();
				scheduleController.navigate();
				break;
			case 4:
				ScheduleAppointmentController scheduleAppointmentController = new ScheduleAppointmentController(
						patient);
				scheduleAppointmentController.navigate();
				break;
			case 5:
				RescheduleAppointmentController rescheduleAppointmentController = new RescheduleAppointmentController(
						patient);
				rescheduleAppointmentController.navigate();
				break;
			case 6:
				CancelAppointmentController cancelAppointmentController = new CancelAppointmentController(patient);
				cancelAppointmentController.navigate();
				break;
			case 7:
				AppointmentController appointmentController = new AppointmentController(patient);
				appointmentController.navigate();
				break;
			case 8:
				AppointmentOutcomeRecordController appointmentOutcomeRecordController = new AppointmentOutcomeRecordController(
						patient);
				appointmentOutcomeRecordController.navigate();
				break;
			case 9:
				ChangePasswordController changePasswordController = new ChangePasswordController((User)patient);
				changePasswordController.navigate();
				break;
			case 10:
				System.out.println("Logging out.");
				break;
			default:
			}
		} while (choice < 10);
	}
}
