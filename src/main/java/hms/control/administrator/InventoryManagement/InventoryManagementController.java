package hms.control.administrator.InventoryManagement;

import hms.boundary.administrator.InventoryManagement.InventoryManagementView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;

public class InventoryManagementController extends Controller {
	private final InventoryManagementView inventoryManagementView;
	private final AddMedicineController addMedicineController;
	private final ShowMedicationInventoryController showMedicationInventoryController;
	private final UpdateMedicineController updateMedicineController;
	private final RemoveMedicineController removeMedicineController;
	private final SetStockWarningController setStockWarningController;

	public InventoryManagementController() {
		this.inventoryManagementView = new InventoryManagementView();
		this.addMedicineController = new AddMedicineController();
		this.showMedicationInventoryController = new ShowMedicationInventoryController();
		this.updateMedicineController = new UpdateMedicineController();
		this.removeMedicineController = new RemoveMedicineController();
		this.setStockWarningController = new SetStockWarningController();
	}

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