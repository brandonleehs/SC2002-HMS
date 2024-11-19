package hms.control.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.administrator.InventoryManagement.RemoveMedicineView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;


/**
 * The RemoveMedicineController class handles the process of removing medicine
 * stock from the inventory. It interacts with the RemoveMedicineView to display
 * prompts and gather user input for reducing medicine stock.
 */
public class RemoveMedicineController extends Controller {
	private final RemoveMedicineView removeMedicineView;
	private final ShowMedicationInventoryController showMedicationInventoryController;
	private int choice, amount;

	/**
	 * Constructs a RemoveMedicineController, initializing the RemoveMedicineView
	 * and the ShowMedicationInventoryController.
	 */
	public RemoveMedicineController() {
		removeMedicineView = new RemoveMedicineView();
		showMedicationInventoryController = new ShowMedicationInventoryController();
	}

	/**
	 * Executes the navigation logic for removing medicine from the inventory.
	 */
	@Override
	public void navigate() {
		choice = 0;
		amount = 0;
		List<String> medicineNames = medicineInventory.getMedicineNames();
		removeMedicineView.displayHeader();
		showMedicationInventoryController.navigate();
		// Prompt for choosing which medicine
		choice = this.removeMedicineView.MedicineIndexPrompt(medicineNames);
		if (choice == -1)
			return;
		// Prompt for amount to be replenished
		String medicineName = medicineNames.get(choice - 1);
		amount = this.removeMedicineView.MedicineAmountPrompt();
		if (amount == -1) {
			return;
		} else if (amount > medicineInventory.getMedicineStock(medicineName)) {
			removeMedicineView.InsufficientStockPrompt();
			return;
		}
		medicineInventory.removeMedicineStock(medicineNames.get(choice - 1), amount);
		this.removeMedicineView.SuccessfulRemovePrompt();
	}
}
