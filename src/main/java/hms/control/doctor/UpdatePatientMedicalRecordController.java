package hms.control.doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hms.boundary.doctor.UpdatePatientMedicalRecordView;
import hms.boundary.patient.record.MedicalRecordView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;
import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineStatus;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;

public class UpdatePatientMedicalRecordController extends Controller {
	private Patient patient;
	private Doctor doctor;
	private UpdatePatientMedicalRecordView updatePatientMedicalRecordView;
	private ShowMedicationInventoryController showMedicationInventoryController;
	private Map<String, List<Integer>> medicines = new HashMap<String, List<Integer>>();

	public UpdatePatientMedicalRecordController(Doctor doctor, Patient patient) {
		this.patient = patient;
		this.doctor = doctor;
		this.updatePatientMedicalRecordView = new UpdatePatientMedicalRecordView();
		this.showMedicationInventoryController = new ShowMedicationInventoryController();
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

		int choice = this.updatePatientMedicalRecordView
				.displayApptOptionPrompt(patient.getAppointmentOutcomeRecordList().size());
		if (choice == -1)
			return;

		AppointmentOutcomeRecord appointmentOutcomeRecord = patient.getAppointmentOutcomeRecordList().get(choice);

		choice = this.updatePatientMedicalRecordView.displayOptions();
		if (choice == -1)
			return;

		String notes = null;
		switch (choice) {
		case 1:
			medicines = medicineInventory.getFullMedicine();
			List<String> medicineNames = medicineInventory.getMedicineNames();
			HashMap<Medicine, Integer> prescribed_medicines = new HashMap<>();

			showMedicationInventoryController.navigate();

			int medicineChoice = updatePatientMedicalRecordView
					.displayAddPrescriptionNamePrompt(medicines.keySet().size());
			if (medicineChoice == -1)
				return;

			int medicineAmount = updatePatientMedicalRecordView.displayAddPrescriptionQtyPrompt();

			Medicine prescribed_medicine = new Medicine(medicineNames.get(medicineChoice));
			prescribed_medicine.setMedicineStatus(MedicineStatus.PENDING);
			prescribed_medicines.put(prescribed_medicine, medicineAmount);
			doctor.prescribeMedicine(prescribed_medicines, appointmentOutcomeRecord);
			break;
		case 2:
			notes = this.updatePatientMedicalRecordView.displaySetConsultationNotesPrompt();
			appointmentOutcomeRecord.addConsultationNotes(notes);
			break;
		case 3:
			notes = this.updatePatientMedicalRecordView.displaySetConsultationNotesPrompt();
			appointmentOutcomeRecord.setConsultationNotes(notes);
			break;
		case 4:
			this.updatePatientMedicalRecordView.displayReturnMenu();
			break;
		default:
		}
	}
}