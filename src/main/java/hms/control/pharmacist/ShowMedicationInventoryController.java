package hms.control.pharmacist;

import java.util.List;
import java.util.Map;

import hms.boundary.pharmacist.ShowMedicationInventoryView;
import hms.control.Controller;

public class ShowMedicationInventoryController extends Controller {
    private Map<String, List<Integer>> medicineList;
    private ShowMedicationInventoryView showMedicationInventoryView;
    
    public ShowMedicationInventoryController(){
        this.medicineList = medicineInventory.getFullMedicine();
        this.showMedicationInventoryView = new ShowMedicationInventoryView();
    }

    public void navigate(){
        showMedicationInventoryView.displayHeader();
        this.medicineList = medicineInventory.getFullMedicine();
        if(!medicineList.isEmpty()){
            showMedicationInventoryView.printAvailableMedicines(medicineList);
        } 
        else{
            showMedicationInventoryView.noRecords();
        }
    }
}
