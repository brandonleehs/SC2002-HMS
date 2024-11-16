package hms.control.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.administrator.InventoryManagement.AddMedicineView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;

public class AddMedicineController extends Controller{
    private final AddMedicineView addMedicineView;
    private final ShowMedicationInventoryController showMedicationInventoryController;
    private int choice, amount;

    public AddMedicineController(){
        addMedicineView = new AddMedicineView();
        showMedicationInventoryController = new ShowMedicationInventoryController();
    }
    

    @Override
    public void navigate(){
        choice=0;
        amount=0;
        List<String> medicineNames = medicineInventory.getMedicineNames();
        addMedicineView.displayHeader();
        showMedicationInventoryController.navigate();
        // Prompt for choosing which medicine
        choice = this.addMedicineView.MedicineIndexPrompt(medicineNames);
        if(choice==-1) return;
        // Prompt for amount to be replenished
        amount = this.addMedicineView.MedicineAmountPrompt();
        if(amount==-1) return; 
        medicineInventory.addMedicineStock(medicineNames.get(choice-1), amount);
        this.addMedicineView.SuccessfulRequestPrompt();
    }
}
