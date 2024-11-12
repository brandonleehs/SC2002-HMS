package hms.repository;

import hms.entity.medicine.ReplenishRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicineInventory {
	private final Map<String, Integer> medicineStock = new HashMap<>(); // Current stock of medicines
	private final Map<String, Integer> lowStockAlert = new HashMap<>(); // Low stock alert level for medicines
	private final List<ReplenishRequest> replenishmentRequestList = new ArrayList<>(); // List of pending replenishment requests

	// Method to add a new medicine with stock and low stock alert value
	public void addNewMedicine(String medicineName, int initialStock, int alertLevel) {
		medicineStock.put(medicineName, initialStock);
		lowStockAlert.put(medicineName, alertLevel);
	}

	// Method to update stock for an existing medicine
	public boolean updateMedicineStock(String medicineName, int additionalStock) {
		if (medicineStock.containsKey(medicineName)) {
			int currentStock = medicineStock.get(medicineName);
			medicineStock.put(medicineName, currentStock + additionalStock);
			return true;
		}
		return false;
	}

	// Method to remove a medicine from inventory
	public boolean removeMedicine(String medicineName) {
		if (medicineStock.containsKey(medicineName)) {
			medicineStock.remove(medicineName);
			lowStockAlert.remove(medicineName);
			return true;
		}
		return false;
	}

	// Method to get current stock levels of all medicines
	public Map<String, Integer> getMedicineStock() {
		return new HashMap<>(medicineStock); // Return a copy to prevent modification from outside
	}

	// Method to get low stock alert levels of all medicines
	public Map<String, Integer> getMedicineLowStockLevelAlertValue() {
		return new HashMap<>(lowStockAlert); // Return a copy to prevent modification from outside
	}

	// Method to retrieve the list of pending replenishment requests
	public List<ReplenishRequest> getReplenishmentRequestList() {
		return new ArrayList<>(replenishmentRequestList); // Return a copy to prevent external modifications
	}

	// Method to add stock based on a replenishment request
	public void addMedicineStock(String medicineName, int stockToAdd) {
		medicineStock.put(medicineName, medicineStock.getOrDefault(medicineName, 0) + stockToAdd);
	}

	// Method to remove a replenishment request from the list
	public boolean removeReplenishmentRequest(ReplenishRequest request) {
		return replenishmentRequestList.remove(request);
	}
}
