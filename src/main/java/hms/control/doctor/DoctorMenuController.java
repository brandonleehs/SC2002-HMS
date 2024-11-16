package hms.control.doctor;

import java.time.LocalDate;

import hms.boundary.Prompt;
import hms.boundary.doctor.DoctorMenuView;
import hms.boundary.doctor.ScheduleView;
import hms.boundary.patient.record.MedicalRecordView;
import hms.control.Controller;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;

public class DoctorMenuController extends Controller {
	private final DoctorMenuView doctorMenuView;
	private Doctor doctor;

	public DoctorMenuController(Doctor doctor) {
		this.doctor = doctor;
		this.doctorMenuView = new DoctorMenuView(doctor);
	}

	@Override
	public void navigate() {
		int choice = 0;
		do {
			this.doctorMenuView.displayHeader();
			choice = this.doctorMenuView.displayOptions();

			Patient patient;
			ScheduleView scheduleView;

			switch (choice) {
			case 1: // view patient medical records
				patient = DoctorMenuView.choosePatient(patientRepository);
                if (patient == null) continue;
				MedicalRecordView medicalRecordView = new MedicalRecordView(patient);
				medicalRecordView.displayMedicalRecord();
				break;
			case 2: // update patient medical records

				patient = DoctorMenuView.choosePatient(patientRepository);
                if (patient == null) continue;
				UpdatePatientMedicalRecordController updatePatientMedicalRecordController = new UpdatePatientMedicalRecordController(
						doctor, patient);
				updatePatientMedicalRecordController.navigate();
				break;
			case 3: // view personal schedule (all appointments in DB)
				LocalDate date = Prompt.displayDatePrompt();
				scheduleView = new ScheduleView();
				scheduleView.displayAppointments(doctor, date, patientRepository);
				break;
			case 4: // set availability for appointments
				SetDoctorAvailabilityController setDoctorAvailabilityController = new SetDoctorAvailabilityController(
						doctor);
				setDoctorAvailabilityController.navigate();
				break;
			case 5: // accept or decline appt requests
				PendingRequestController pendingRequestController = new PendingRequestController(doctor);
				pendingRequestController.navigate();
				break;
			case 6: // view all appts today & tmr
				scheduleView = new ScheduleView();
				scheduleView.displayUpcomingAppointments(doctor, patientRepository);
				break;
			case 7: // record appt outcome
				CompleteAppointmentController completeAppointmentController = new CompleteAppointmentController(doctor);
				completeAppointmentController.navigate();
				break;
			case 8: // change password
				ChangePasswordController changePasswordController = new ChangePasswordController(doctor);
				changePasswordController.navigate();
				break;
			case 9: // logout
				System.out.println("Logging out.");
				break;
			default:
			}
		} while (choice < 9);
	}
}
