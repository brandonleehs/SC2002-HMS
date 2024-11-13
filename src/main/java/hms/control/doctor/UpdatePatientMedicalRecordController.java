package hms.control.doctor;

import hms.boundary.InputHandler;
import hms.boundary.doctor.UpdatePatientMedicalRecordView;
import hms.boundary.patient.record.MedicalRecordView;
import hms.control.Controller;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class UpdatePatientMedicalRecordController extends Controller {
	private Patient patient;
	private Doctor doctor;
	private UpdatePatientMedicalRecordView updatePatientMedicalRecordView;

	public UpdatePatientMedicalRecordController(Doctor doctor, Patient patient) {
		this.patient = patient;
		this.doctor = doctor;
		this.updatePatientMedicalRecordView = new UpdatePatientMedicalRecordView();
	}

	@Override
	public void navigate() {
		if (patient.getAppointmentOutcomeRecordList().isEmpty()) {
			this.updatePatientMedicalRecordView.displayNoRecords();
			this.updatePatientMedicalRecordView.displayReturnMenu();
			return;
		}

		MedicalRecordView medicalRecordView = new MedicalRecordView(this.patient);
		medicalRecordView.displayMedicalRecord();

		this.updatePatientMedicalRecordView.displayApptOptionPrompt();

		int choice = 0;
		try {
			choice = InputHandler.getChoice(1, patient.getAppointmentOutcomeRecordList().size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return;
		}

		AppointmentOutcomeRecord appointmentOutcomeRecord = patient.getAppointmentOutcomeRecordList().get(choice - 1);

		this.updatePatientMedicalRecordView.displayOptions();
		try {
			choice = InputHandler.getChoice(1, 5);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			choice = -1;
			return;
		}
		String notes = null;
		switch (choice) {
		case 1:
			this.updatePatientMedicalRecordView.displayAddPrescriptionNamePrompt();
			String medName = InputHandler.getString();
			this.doctor.prescribeMedicine(new Medicine(medName), appointmentOutcomeRecord);
			break;
		case 2:
			this.updatePatientMedicalRecordView.displaySetConsultationNotesPrompt();
			notes = InputHandler.getString();
			appointmentOutcomeRecord.addConsultationNotes(notes);
			break;
		case 3:
			this.updatePatientMedicalRecordView.displaySetConsultationNotesPrompt();
			notes = InputHandler.getString();
			appointmentOutcomeRecord.setConsultationNotes(notes);
			break;
		case 4:
			this.updatePatientMedicalRecordView.displayReturnMenu();
			break;
		default:
		}
	}
//
//	public AppointmentOutcomeRecord chooseAppointmentOutcomeRecord() {
//		updatePatientMedicalRecordView.displayApptOptionPrompt();
//		int i = -1;
//		while (i == -1) {
//			try {
//				i = InputHandler.getChoice(1, patient.getAppointmentOutcomeRecordList().size());
//			} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
//				continue;
//			}
//		}
//
//		return (patient.getAppointmentOutcomeRecordList()).get(i - 1);
//	}
}