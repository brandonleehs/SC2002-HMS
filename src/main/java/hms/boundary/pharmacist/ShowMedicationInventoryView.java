package hms.boundary.pharmacist;

import java.util.Set;

import hms.boundary.View;

public class ShowMedicationInventoryView extends View{
    public void printAvailableMedicines(Set<String> medicineList){
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
