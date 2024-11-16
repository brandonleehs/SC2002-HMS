package hms.control.pharmacist;

import hms.boundary.patient.record.AppointmentOutcomeRecordView;
import hms.boundary.pharmacist.PharmacistMenuView;
import hms.boundary.pharmacist.ViewReplenishmentRequestView;
import hms.control.Controller;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;

public class PharmacistMenuController extends Controller {
	private final PharmacistMenuView pharmacistMenuView;
	private final Pharmacist pharmacist;

	public PharmacistMenuController(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
		this.pharmacistMenuView = new PharmacistMenuView(pharmacist);
	}

	@Override
	public void navigate() {
		int choice = 0;
		do {
			this.pharmacistMenuView.displayHeader();
			choice = this.pharmacistMenuView.displayOptions();

			Patient patient;
			switch (choice) {
			case 1: // View Appointment Outcome Record
				AppointmentOutcomeRecordView appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();
				patient = PharmacistMenuView.choosePatient(patientRepository);
				if (patient == null)
					continue;
				appointmentOutcomeRecordView.displayRecords(patient);
				break;
			case 2: // Update Prescription Status
				patient = PharmacistMenuView.choosePatient(patientRepository);
				if (patient == null)
					continue;
				UpdatePrescriptionStatusController updatePatientMedicalRecordController = new UpdatePrescriptionStatusController(
						patient);
				updatePatientMedicalRecordController.navigate();
				break;
			case 3: // View Medication Inventory
				ShowMedicationInventoryController showMedicationInventoryController = new ShowMedicationInventoryController();
				showMedicationInventoryController.navigate();
				break;
			case 4: // Submit Replenishment Request
				SubmitReplenishmentRequestController submitReplenishmentRequest = new SubmitReplenishmentRequestController();
				submitReplenishmentRequest.navigate();
				break;
			case 5: // View active amounts of Replenishment Requests waiting for approval
				ViewReplenishmentRequestView viewReplenishmentRequestView = new ViewReplenishmentRequestView();
				viewReplenishmentRequestView.displayHeader();
				viewReplenishmentRequestView.displayRequests(medicineInventory.getReplenishmentRequestList());
				break;
			case 6: // change password
				ChangePasswordController changePasswordController = new ChangePasswordController(pharmacist);
				changePasswordController.navigate();
				break;
			case 7: // logout
				System.out.println("Logging out.");
				break;
			default:
			}
		} while (choice < 7);
	}
}