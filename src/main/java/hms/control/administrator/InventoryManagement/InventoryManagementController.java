package hms.control.administrator.InventoryManagement;

import hms.boundary.administrator.InventoryManagement.InventoryManagementView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;


/**
 * The InventoryManagementController class manages the navigation and control flow
 * for inventory management functionalities in the HMS system.
 * It connects various controllers responsible for specific inventory tasks
 * and delegates the operations based on user input.
 */
public class InventoryManagementController extends Controller {
	private final InventoryManagementView inventoryManagementView;
	private final AddMedicineController addMedicineController;
	private final ShowMedicationInventoryController showMedicationInventoryController;
	private final UpdateMedicineController updateMedicineController;
	private final RemoveMedicineController removeMedicineController;
	private final SetStockWarningController setStockWarningController;


	/**
	 * Constructs a new InventoryManagementController and initializes the
	 * associated view and all sub-controllers for inventory management functionalities.
	 */
	public InventoryManagementController() {
		this.inventoryManagementView = new InventoryManagementView();
		this.addMedicineController = new AddMedicineController();
		this.showMedicationInventoryController = new ShowMedicationInventoryController();
		this.updateMedicineController = new UpdateMedicineController();
		this.removeMedicineController = new RemoveMedicineController();
		this.setStockWarningController = new SetStockWarningController();
	}

	/**
 	* Navigates through the inventory management options based on the user's choice.
 	* Continuously prompts the user until they choose to exit.
 	*/
 	@Override
	public void navigate() {
		int choice;
		do {
			inventoryManagementView.displayOptions();
			choice = inventoryManagementView.getChoice();

			switch (choice) {
			case 1:
				addMedicineController.navigate();
				break;
			case 2:
				updateMedicineController.navigate();
				break;
			case 3:
				removeMedicineController.navigate();
				break;
			case 4:
				showMedicationInventoryController.navigate();
				break;
			case 5:
				setStockWarningController.navigate();
				break;
			case 6:
				inventoryManagementView.printExit();
				break;
			default:
			}
		} while (choice < 6);
	}
}