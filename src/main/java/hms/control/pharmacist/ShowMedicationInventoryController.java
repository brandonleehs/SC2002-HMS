package hms.control.pharmacist;

import hms.control.Controller;

import java.util.Set;

public class ShowMedicationInventoryController extends Controller {
    private Set<String> medicineList = medicineInventory.getMedicineNames();

    public void navigate(){

    }
}
