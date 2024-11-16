package hms.control.pharmacist;

import java.util.List;
import java.util.Map;

import hms.boundary.pharmacist.ShowMedicationInventoryView;
import hms.control.Controller;

public class ShowMedicationInventoryController extends Controller {
    private Map<String, List<Integer>> medicineList;
    private final ShowMedicationInventoryView showMedicationInventoryView = new ShowMedicationInventoryView();
    
    public ShowMedicationInventoryController(){
    }

    @Override
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
