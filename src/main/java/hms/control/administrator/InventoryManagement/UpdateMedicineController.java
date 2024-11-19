package hms.control.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.administrator.InventoryManagement.UpdateMedicineView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;

/**
 * Controller class for updating medicine inventory.
 * Handles the process of displaying current inventory, prompting the user to select a medicine,
 * and updating the stock quantity.
 */
public class UpdateMedicineController extends Controller {
    private final UpdateMedicineView updateMedicineView;
    private final ShowMedicationInventoryController showMedicationInventoryController;
    private int choice, amount;

    /**
     * Constructs a new UpdateMedicineController and initializes the associated view
     * and dependent controller.
     */
    public UpdateMedicineController(){
        updateMedicineView = new UpdateMedicineView();
        showMedicationInventoryController = new ShowMedicationInventoryController();
    }

    /**
     * Facilitates the workflow for updating the stock of a specific medicine.
     */
    @Override
    public void navigate(){
        choice=0;
        amount=0;
        List<String> medicineNames = medicineInventory.getMedicineNames();
        updateMedicineView.displayHeader();
        showMedicationInventoryController.navigate();
        // Prompt for choosing which medicine
        choice = this.updateMedicineView.MedicineIndexPrompt(medicineNames);
        if(choice==-1) return;
        // Prompt for amount to be replenished
        amount = this.updateMedicineView.MedicineAmountPrompt();
        if(amount==-1) return; 
        medicineInventory.setMedicineStock(medicineNames.get(choice-1), amount);
        this.updateMedicineView.SuccessfulUpdatePrompt();
    }
}
