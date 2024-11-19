package hms.control.pharmacist;

import java.util.List;
import java.util.Map;

import hms.boundary.pharmacist.ShowMedicationInventoryView;
import hms.control.Controller;

/**
 * Controller class for displaying the medication inventory managed by the pharmacist.
 * Retrieves and shows the list of available medicines from the inventory.
 */
public class ShowMedicationInventoryController extends Controller {
    private Map<String, List<Integer>> medicineList;
    private final ShowMedicationInventoryView showMedicationInventoryView = new ShowMedicationInventoryView();

    /**
     * Constructs a new ShowMedicationInventoryController.
     */
    public ShowMedicationInventoryController(){
    }

    /**
     * Retrieves the list of available medicines from the inventory and displays them.
     * If no medicines are available, a message indicating no records is shown.
     */
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
