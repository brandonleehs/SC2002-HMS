package hms.control;

import hms.boundary.InputHandler;
import hms.boundary.patient.MedicalRecordView;
import hms.boundary.patient.PatientMenuView;
import hms.entity.user.Patient;

public class PatientMenuController extends Controller {
	private final PatientMenuView patientMenuView;
	private Patient patient;

	public PatientMenuController(Patient patient) {
		this.patient = patient;
		this.patientMenuView = new PatientMenuView(patient);
	}

	@Override
	public void navigate() {
		int choice = 0;
		do {
			this.patientMenuView.displayHeader();
			this.patientMenuView.displayOptions();

			choice = InputHandler.getChoice();
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
