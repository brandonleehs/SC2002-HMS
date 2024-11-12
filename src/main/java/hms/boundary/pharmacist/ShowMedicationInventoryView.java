package hms.boundary.pharmacist;

import hms.boundary.View;
import hms.entity.medicine.MedicineInventory;

public class ShowMedicationInventoryView extends View{
    public void printAvailableMedicines(medicineList){
		System.out.println("Available Medicines:");
		for (String medicineName : medicineList) {
			System.out.println(medicineName);
		}
	}

    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Medication Inventory");
    }
}
