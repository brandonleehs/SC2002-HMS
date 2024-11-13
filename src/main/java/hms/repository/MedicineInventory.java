package hms.repository;

import hms.entity.medicine.ReplenishRequest;
import hms.serializer.MedicineInventorySerializer;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public List<String> getMedicineNames() {
		List<String> temp_r = new ArrayList<>(medicineStock.keySet());
		return temp_r;
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

	public boolean dispenseMedicine(String medicineName, Integer quantityToDispense) {
		int medicineStockNumber = this.medicineStock.get(medicineName).get(0);
		if (medicineStockNumber <= 0 || medicineStockNumber-quantityToDispense<0) {
			return false;
		}
		else{
			List<Integer> temp = new ArrayList<>();
			temp.add(medicineStockNumber-quantityToDispense);
			temp.add(this.medicineStock.get(medicineName).get(1));
			this.medicineStock.put(medicineName, temp);
			return true;
		}
	}

	public List<ReplenishRequest> getReplenishmentRequestList() {
		return this.replenishmentRequestList;
	}

	public void addReplenishmentRequest(ReplenishRequest replenishRequest) {
		this.replenishmentRequestList.add(replenishRequest);
	}

	public boolean removeReplenishmentRequest(ReplenishRequest replenishRequest) {
		return this.replenishmentRequestList.remove(replenishRequest);
	}
}
