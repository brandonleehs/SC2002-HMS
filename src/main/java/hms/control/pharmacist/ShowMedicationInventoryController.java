package hms.control.pharmacist;

import java.util.List;
import java.util.Map;

import hms.boundary.pharmacist.ShowMedicationInventoryView;
import hms.control.Controller;

public class ShowMedicationInventoryController extends Controller {
    private Map<String, List<Integer>> medicineList = medicineInventory.getFullMedicine();
    private ShowMedicationInventoryView showMedicationInventoryView = new ShowMedicationInventoryView();
    
    public void navigate(){
        showMedicationInventoryView.displayHeader();
        if(!medicineList.isEmpty()){
            showMedicationInventoryView.printAvailableMedicines(medicineList);
        } 
        else{
            showMedicationInventoryView.noRecords();
        }
    }
}
