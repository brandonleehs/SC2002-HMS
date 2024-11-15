package hms.control.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.administrator.InventoryManagement.SetStockWarningView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;

public class SetStockWarningController extends Controller{
    private final SetStockWarningView setStockWarningView;
    private final ShowMedicationInventoryController showMedicationInventoryController;
    private int choice, amount;

    public SetStockWarningController(){
        setStockWarningView = new SetStockWarningView();
        showMedicationInventoryController = new ShowMedicationInventoryController();
    }
    

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
