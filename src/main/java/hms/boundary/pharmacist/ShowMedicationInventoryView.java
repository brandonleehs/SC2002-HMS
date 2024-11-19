package hms.boundary.pharmacist;

import java.util.List;
import java.util.Map;

import hms.boundary.View;
import hms.entity.medicine.InventoryWarning;

public class ShowMedicationInventoryView extends View {
	public void printAvailableMedicines(Map<String, List<Integer>> medicineList) {
		String format = "| %-" + 5 + "s | %-" + (WIDTH - 31) + "s | %-" + 6 + "s | %-" + 7 + "s |\n";
		System.out.println("Available Medicines:");
		if (medicineList.isEmpty()) {
			System.out.println("No medicine prescribed.");
		} else {
			System.out.printf(format, "Index", "Medicine Name", "Amount", "Warning");
			System.out.printf(format, "-".repeat(5), "-".repeat(WIDTH - 31), "-".repeat(6), "-".repeat(7));

			int u = 0;
			for (Map.Entry<String, List<Integer>> entry : medicineList.entrySet()) {
				// If stock level is lower or equal to warning, display warning instead
				InventoryWarning WARNING = InventoryWarning.OK;
				if (entry.getValue().get(0) <= entry.getValue().get(1))
					WARNING = InventoryWarning.WARNING;
				System.out.printf(format, u + 1, entry.getKey(), entry.getValue().get(0), WARNING);
				u++;
			}
		}
	}

	public void noRecords() {
		System.out.println("There are no records");
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Medication Inventory");
	}
}
