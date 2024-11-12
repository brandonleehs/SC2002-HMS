package hms.control.pharmacist;

import java.util.Set;

import hms.boundary.pharmacist.ShowMedicationInventoryView;
import hms.control.Controller;

public class ShowMedicationInventoryController extends Controller {
    private Set<String> medicineList = medicineInventory.getMedicineNames();
    private ShowMedicationInventoryView showMedicationInventoryView = new ShowMedicationInventoryView();
    
    public void navigate(){
        showMedicationInventoryView.printAvailableMedicines(medicineList);
    }
}
