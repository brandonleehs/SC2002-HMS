package hms.control.pharmacist;

import java.util.List;

import hms.boundary.pharmacist.SubmitReplenishmentRequestView;
import hms.control.Controller;
import hms.entity.medicine.ReplenishRequest;

/**
 * Controller for handling the submission of replenishment requests for medications.
 * This class facilitates the process of selecting a medicine, specifying the amount to be replenished,
 * and submitting the replenishment request to the inventory system.
 */
public class SubmitReplenishmentRequestController extends Controller {
	private final SubmitReplenishmentRequestView submitReplenishmentRequestView;
	private final ShowMedicationInventoryController showMedicationInventoryController;

	/**
	 * Constructs an instance of SubmitReplenishmentRequestController.
	 * Initializes the view for submitting replenishment requests and the controller for viewing the medication inventory.
	 */
	public SubmitReplenishmentRequestController() {
		this.submitReplenishmentRequestView = new SubmitReplenishmentRequestView();
		this.showMedicationInventoryController = new ShowMedicationInventoryController();
	}

	/**
	 * Navigates the pharmacist through the process of submitting a replenishment request.
	 */
	public void navigate() {
		submitReplenishmentRequestView.displayHeader();
		List<String> medicineNames = medicineInventory.getMedicineNames();
		showMedicationInventoryController.navigate();
		// Prompt for choosing which medicine
		int medicineChoice = this.submitReplenishmentRequestView.MedicineIndexPrompt(medicineNames.size());
		if (medicineChoice == -1)
			return;
		// Prompt for amount to be replenished
		int amount = this.submitReplenishmentRequestView.MedicineAmountPrompt();
		if (amount == -1)
			return;
		ReplenishRequest replenishRequest = new ReplenishRequest(medicineNames.get(medicineChoice - 1), amount);
		medicineInventory.addReplenishmentRequest(replenishRequest);
		this.submitReplenishmentRequestView.SuccessfulRequestPrompt();
	}

}