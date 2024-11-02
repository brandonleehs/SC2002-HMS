package hms.entity.medicine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Define a singleton class with early instantiation
public class MedicineInventory {
	private static final MedicineInventory medicineInventory = new MedicineInventory();
	private final Map<String, Integer> medicineStock = new HashMap<String, Integer>();
	private final Map<String, Integer> medicineLowStockLevelAlertValue = new HashMap<String, Integer>();
	private final List<ReplenishRequest> replenishmentRequestList = new ArrayList<ReplenishRequest>();

	private MedicineInventory() {
	}

	public static MedicineInventory getInstance() {
		return medicineInventory;
	}

	public Map<String, Integer> getMedicineStock() {
		return this.medicineStock;
	}

	public Map<String, Integer> getMedicineLowStockLevelAlertValue() {
		return this.medicineLowStockLevelAlertValue;
	}

	public List<ReplenishRequest> getReplenishmentRequestList() {
		return this.replenishmentRequestList;
	}

	public void setMedicineStock(String medicineName, int stock) {
		this.medicineStock.put(medicineName, stock);
	}

	public void setLowStockLevelAlertValue(String medicineName, int LowStockLevelAlertValue) {
		this.medicineLowStockLevelAlertValue.put(medicineName, LowStockLevelAlertValue);
	}

	public boolean dispenseMedicine(String medicineName) {
		if (this.medicineStock.get(medicineName) <= 0) {
			return false;
		}
		this.medicineStock.put(medicineName, this.medicineStock.get(medicineName) - 1);
		return true;
	}

	public void addMedicineStock(String medicineName, int amountToAdd) {
		this.medicineStock.put(medicineName, this.medicineStock.get(medicineName) + amountToAdd);
	}

//TODO: Consider checking if amount to remove is too large
	public void removeMedicineStock(String medicineName, int amountToRemove) {
		this.medicineStock.put(medicineName, this.medicineStock.get(medicineName) - amountToRemove);
	}

	public void addReplenishmentRequest(ReplenishRequest replenishRequest) {
		this.replenishmentRequestList.add(replenishRequest);
	}

	public boolean removeReplenishmentRequest(ReplenishRequest replenishRequest) {
		return this.replenishmentRequestList.remove(replenishRequest);
	}

	// TODO: This should be in boundary/view
//	public void printAvailableMedicines() {
//		System.out.println("Available Medicines:");
//		for (String medicineName : medicineStock.keySet()) {
//			System.out.println(medicineName);
//		}
//	}

//	public void viewInventoryStock() {
//		System.out.println("Medicine Inventory:");
//		for (Map.Entry<String, Integer> entry : medicineStock.entrySet()) {
//			String medicineName = entry.getKey();
//			Integer stockLevel = entry.getValue();
//			System.out.println(medicineName + ": " + stockLevel);
//		}
//	}

//	public void viewInventoryWarning() {
//		System.out.println("Available Medicines:");
//		for (String medicineName : medicineStock.keySet()) {
//			int stockLevel = medicineStock.get(medicineName);
//			int lowStockAlertValue = medicineLowStockLevelAlertValue.getOrDefault(medicineName, Integer.MAX_VALUE);
//
//			System.out.print(medicineName + " (Stock: " + stockLevel + ")");
//
//			// Check if stock level is below the alert value
//			if (stockLevel < lowStockAlertValue) {
//				System.out.println(" - Warning: Low stock!");
//			} else {
//				System.out.println();
//			}
//		}
//	}
}
