package hms;

import java.util.HashMap;
import java.util.Map;

// Define a singleton class with early instantiation
public class MedicineInventory {
	private static final MedicineInventory medicineInventory = new MedicineInventory();
	private final Map<String, Integer> medicineStock = new HashMap<String, Integer>();
	private final Map<String, Integer> medicineLowStockLevelAlertValue = new HashMap<String, Integer>();

	private MedicineInventory() {
	}

	public static MedicineInventory getMedicineInventory() {
		return medicineInventory;
	}

	public Map<String, Integer> getMedicineStock() {
		return this.medicineStock;
	}

	public Map<String, Integer> getMedicineLowStockLevelAlertValue() {
		return this.medicineLowStockLevelAlertValue;
	}

	public void setStock(String medicineName, int stock) {
		this.medicineStock.put(medicineName, stock);
	}

	public void setLowStockLevelAlertValue(String medicineName, int LowStockLevelAlertValue) {
		this.medicineLowStockLevelAlertValue.put(medicineName, LowStockLevelAlertValue);
	}

	public void decrementMedicineStock(String medicineName) {
		this.medicineStock.put(medicineName, this.medicineStock.get(medicineName) - 1);
	}

	public void addMedicineStock(String medicineName, int stock) {
		this.medicineStock.put(medicineName, this.medicineStock.get(medicineName) + stock);
	}

}
