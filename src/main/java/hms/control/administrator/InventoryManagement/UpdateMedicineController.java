package hms.control.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.administrator.InventoryManagement.UpdateMedicineView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;

public class UpdateMedicineController extends Controller {
    private final UpdateMedicineView updateMedicineView;
    private final ShowMedicationInventoryController showMedicationInventoryController;
    private int choice, amount;

    public UpdateMedicineController(){
        updateMedicineView = new UpdateMedicineView();
        showMedicationInventoryController = new ShowMedicationInventoryController();
    }
    

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
