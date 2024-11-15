package hms.boundary.pharmacist;

import java.util.List;
import java.util.Map;

import hms.boundary.View;
import hms.entity.medicine.InventoryWarning;

public class ShowMedicationInventoryView extends View{
    public void printAvailableMedicines(Map<String, List<Integer>> medicineList){
        String format = "| %-" + (WIDTH - 42) + "s | %-" + 10 + "s | %-" + 10 + "s | %-" + 10 + "s |\n";

		System.out.println("Available Medicines:");
        if (medicineList.isEmpty()) {
			System.out.println("No medicine prescribed.");
		} else {
			System.out.printf(format, "Index", "Medicine Name", "Amount", "Warning");
			System.out.println("|" + "-".repeat(WIDTH - 29) + "|" + "-".repeat(12) + "|"+"-".repeat(13) + "|");
			int u=0;
			for (Map.Entry<String, List<Integer>> entry : medicineList.entrySet()) {
				// If stock level is lower or equal to warning, display warning instead
				InventoryWarning WARNING = InventoryWarning.OK;
				if (entry.getValue().get(0)<=entry.getValue().get(1)) WARNING = InventoryWarning.WARNING;
				System.out.printf(format, u+1, entry.getKey(), entry.getValue().get(0),WARNING);
				u++;
			}
		}
	}

	public void noRecords(){
		System.out.println("There are no records");
	}

    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Medication Inventory");
    }
}
