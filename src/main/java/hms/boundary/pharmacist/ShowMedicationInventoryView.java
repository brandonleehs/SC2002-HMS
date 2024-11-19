package hms.boundary.pharmacist;

import java.util.List;
import java.util.Map;

import hms.boundary.View;
import hms.entity.medicine.InventoryWarning;

/**
 * Displays the medication inventory information for the pharmacist in the Hospital Management System (HMS).
 * This class provides methods to display the available medicines, warnings for low stock, and other related views.
 */
public class ShowMedicationInventoryView extends View {
	/**
     * Displays the list of available medicines along with their quantities and warnings if stock is low.
     * 
     * The method iterates through the given medicine list and displays details such as:
     * - Medicine index
     * - Medicine name
     * - Quantity in stock
     * - A warning indicator if the stock is below or equal to the predefined warning level
     * 
     * @param medicineList A map where the key is the medicine name, and the value is a list containing:
     *                     - The current stock (index 0)
     *                     - The warning threshold (index 1)
     */
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

	/**
     * Displays a message indicating that there are no medication records available.
     */
	public void noRecords() {
		System.out.println("There are no records");
	}

	/**
     * Displays the header for the medication inventory view.
     * Includes a bordered title for better visual clarity.
     */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Medication Inventory");
	}
}
