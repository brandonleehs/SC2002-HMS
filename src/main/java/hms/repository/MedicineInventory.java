package hms.repository;

import hms.entity.medicine.ReplenishRequest;
import hms.serializer.MedicineInventorySerializer;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Define a singleton class with early instantiation
public class MedicineInventory {
	private Map<String, List<Integer>> medicineStock = new HashMap<String, List<Integer>>();
	private final List<ReplenishRequest> replenishmentRequestList = new ArrayList<ReplenishRequest>();

	public MedicineInventory() {
		MedicineInventorySerializer medicineInventorySerializer = new MedicineInventorySerializer();
		medicineStock = medicineInventorySerializer.getMedicineInventory("Medicine_List.xlsx");
	}

	public Map<String, List<Integer>> getFullMedicine(){
		return medicineStock;
	}

	public Map<String, Integer> getMedicineStock() {
		Map<String, Integer> temp = new HashMap<String, Integer>();
		for (Map.Entry<String, List<Integer>> entry : medicineStock.entrySet()) {
            String key = entry.getKey();
            List<Integer> values = entry.getValue();

            // Check if the list has at least one element
            if (values != null && !values.isEmpty()) {
                temp.put(key, values.get(0)); // Put the first element in the new map
            }
        }
		return temp;
	}

	public Map<String, Integer> getMedicineLowStockLevelAlertValue() {
		Map<String, Integer> temp = new HashMap<String, Integer>();
		for (Map.Entry<String, List<Integer>> entry : medicineStock.entrySet()) {
            String key = entry.getKey();
            List<Integer> values = entry.getValue();

            // Check if the list has at least one element
            if (values != null && !values.isEmpty()) {
                temp.put(key, values.get(1)); // Put the first element in the new map
            }
        }
		return temp;
	}

// 	public List<ReplenishRequest> getReplenishmentRequestList() {
// 		return this.replenishmentRequestList;
// 	}

// 	public boolean dispenseMedicine(String medicineName) {
// 		if (this.medicineStock.get(medicineName) <= 0) {
// 			return false;
// 		}
// 		this.medicineStock.put(medicineName, this.medicineStock.get(medicineName) - 1);
// 		return true;
// 	}

// 	public void addMedicineStock(String medicineName, int amountToAdd) {
// 		this.medicineStock.put(medicineName, this.medicineStock.get(medicineName) + amountToAdd);
// 	}

	public List<String> getMedicineNames() {
		List<String> temp_r = new ArrayList<>(medicineStock.keySet());
		return temp_r;
	}

// //TODO: Consider checking if amount to remove is too large
// 	public void removeMedicineStock(String medicineName, int amountToRemove) {
// 		this.medicineStock.put(medicineName, this.medicineStock.get(medicineName) - amountToRemove);
// 	}

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
