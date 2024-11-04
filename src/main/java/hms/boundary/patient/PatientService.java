package hms.boundary.patient;

import hms.entity.user.Patient;

public class PatientService {
	private Patient patient;

	public PatientService(Patient patient) {
		this.patient = patient;
	}

	public void renderService(int choice) {
		switch (choice) {
		case 1:
			viewMedicalRecord();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
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
		}
	}

	private void viewMedicalRecord() {
		MedicalRecordView medicalRecordView = new MedicalRecordView(patient);
		medicalRecordView.show();
	}
}
