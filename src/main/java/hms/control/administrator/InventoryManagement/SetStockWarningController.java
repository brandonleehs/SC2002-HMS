package hms.control.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.administrator.InventoryManagement.SetStockWarningView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;

/**
 * The SetStockWarningController class handles the process of setting stock
 * warning levels for medicines in the inventory. It interacts with the
 * SetStockWarningView to display prompts and gathers user input for setting
 * warning levels.
 */
public class SetStockWarningController extends Controller{
    private final SetStockWarningView setStockWarningView;
    private final ShowMedicationInventoryController showMedicationInventoryController;
    private int choice, amount;


    /**
     * Constructs a SetStockWarningController, initializing the SetStockWarningView
     * and ShowMedicationInventoryController.
     */
    public SetStockWarningController(){
        setStockWarningView = new SetStockWarningView();
        showMedicationInventoryController = new ShowMedicationInventoryController();
    }

    /**
     * Executes the navigation logic for setting stock warning levels in the inventory.
     */
    @Override
    public void navigate(){
        choice=0;
        amount=0;
        List<String> medicineNames = medicineInventory.getMedicineNames();
        setStockWarningView.displayHeader();
        showMedicationInventoryController.navigate();
        // Prompt for choosing which medicine
        choice = this.setStockWarningView.MedicineIndexPrompt(medicineNames);
        if(choice==-1) return;
        // Prompt for amount to be replenished
        amount = this.setStockWarningView.MedicineAmountPrompt();
        if(amount==-1) return; 
        medicineInventory.setStockWarningLevel(medicineNames.get(choice-1), amount);
        this.setStockWarningView.SuccessfulChangePrompt();
    }
}
