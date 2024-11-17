package hms.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hms.entity.medicine.ReplenishRequest;
import hms.serializer.MedicineInventorySerializer;

// Define a singleton class with early instantiation
public class MedicineInventory {
	private Map<String, List<Integer>> medicineStock = new HashMap<>();
	private final List<ReplenishRequest> replenishmentRequestList = new ArrayList<ReplenishRequest>();
	private static final String FILEPATH = "./src/main/resources/Medicine_List.csv";

	public MedicineInventory() {
		MedicineInventorySerializer medicineInventorySerializer = new MedicineInventorySerializer(FILEPATH);
		medicineInventorySerializer.serialize();
		this.medicineStock = medicineInventorySerializer.getMedicineStock();
		this.replenishmentRequestList.addAll(medicineInventorySerializer.getReplenishmentRequestList());
	}

	public Map<String, List<Integer>> getFullMedicine() {
		return medicineStock;
	}

	public List<String> getMedicineNames() {
		List<String> temp_r = new ArrayList<>(medicineStock.keySet());
		return temp_r;
	}

	public Integer getMedicineStock(String medicineName) {
		return medicineStock.get(medicineName).get(0);
	}

	public void addMedicineStock(String name, int amount) {
		List<Integer> tempList = medicineStock.get(name);
		int oldStock = tempList.get(0);
		tempList.set(0, oldStock + amount);
	}

	public void removeMedicineStock(String name, int amount) {
		List<Integer> tempList = medicineStock.get(name);
		int oldStock = tempList.get(0);
		tempList.set(0, oldStock - amount);
	}

	public void setMedicineStock(String name, int amount) {
		List<Integer> tempList = medicineStock.get(name);
		tempList.set(0, amount);
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

	public void setStockWarningLevel(String medicineName, int amount) {
		List<Integer> tempList = medicineStock.get(medicineName);
		tempList.set(1, amount);
	}

	public boolean dispenseMedicine(String medicineName, Integer quantityToDispense) {
		int medicineStockNumber = this.medicineStock.get(medicineName).get(0);
		if (medicineStockNumber <= 0 || medicineStockNumber - quantityToDispense < 0) {
			return false;
		} else {
			List<Integer> temp = new ArrayList<>();
			temp.add(medicineStockNumber - quantityToDispense);
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

	public void approveReplenishmentRequest(ReplenishRequest replenishRequest) {
		String medicineName = replenishRequest.getMedicineName();
		List<Integer> tempList = medicineStock.get(medicineName);
		tempList.set(0, replenishRequest.getStockToAdd() + tempList.get(0));
		medicineStock.put(medicineName, tempList);
		removeReplenishmentRequest(replenishRequest);
	}

	public void removeReplenishmentRequest(ReplenishRequest replenishRequest) {
		this.replenishmentRequestList.remove(replenishRequest);
	}

	public void deserialize() {
		File file = new File(FILEPATH);
		try {
			PrintWriter printWriter = new PrintWriter(file);
			String header = String.join(",", "Medicine Name", "Initial Stock", "Low Stock Level Alert",
					"Replenish Request");
			printWriter.println(header);
			for (Map.Entry<String, List<Integer>> entry : medicineStock.entrySet()) {
				String data = String.join(",", entry.getKey(), String.valueOf(entry.getValue().get(0)),
						String.valueOf(entry.getValue().get(1)));
				ReplenishRequest replenishRequest = null;
				if (!this.replenishmentRequestList.isEmpty()
						&& (replenishRequest = this.replenishmentRequestList.remove(0)) != null) {
					data = data + "," + replenishRequest.getMedicineName() + ","
							+ String.valueOf(replenishRequest.getStockToAdd());
				}
				printWriter.println(data);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
