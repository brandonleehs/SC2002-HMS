package hms.entity.medicine;

import hms.serializer.MedicineInventorySerializer;

import java.util.*;

// Define a singleton class with early instantiation
public class MedicineInventory {
	private Map<String, Integer> medicineStock = new HashMap<String, Integer>();
	private Map<String, Integer> medicineLowStockLevelAlertValue = new HashMap<String, Integer>();
	private final List<ReplenishRequest> replenishmentRequestList = new ArrayList<ReplenishRequest>();

	public MedicineInventory() {
		MedicineInventorySerializer medicineInventorySerializer = new MedicineInventorySerializer();
		List<HashMap<String, Integer>> maps = medicineInventorySerializer.getMedicineInventory("Medicine_List.xlsx");
		medicineStock = maps.get(0);
		medicineLowStockLevelAlertValue = maps.get(1);
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

	public Set<String> getMedicineNames() {
		return this.medicineStock.keySet();
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
