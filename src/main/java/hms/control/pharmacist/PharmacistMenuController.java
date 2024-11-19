package hms.control.pharmacist;

import hms.boundary.patient.record.AppointmentOutcomeRecordView;
import hms.boundary.pharmacist.PharmacistMenuView;
import hms.boundary.pharmacist.ViewReplenishmentRequestView;
import hms.control.MenuController;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;
import hms.entity.user.User;

/**
 * Controller for the pharmacist's menu and actions within the system.
 * This class handles the pharmacist's available operations such as viewing appointment records,
 * updating prescription statuses, managing medication inventory, and submitting replenishment requests.
 */
public class PharmacistMenuController extends MenuController {
	private final PharmacistMenuView pharmacistMenuView;
	private final Pharmacist pharmacist;

	/**
	 * Constructs an instance of PharmacistMenuController.
	 *
	 * @param pharmacist The pharmacist whose menu actions will be controlled.
	 */
	public PharmacistMenuController(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
		this.pharmacistMenuView = new PharmacistMenuView(pharmacist);
	}

	/**
	 * Navigates the pharmacist through the available menu options and executes the corresponding action.
	 */
	@Override
	public void navigate() {
		checkNewUser((User)pharmacist);
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
				ChangePasswordController changePasswordController = new ChangePasswordController((User)pharmacist);
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