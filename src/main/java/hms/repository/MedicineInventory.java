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

/**
 * This class manages the inventory of medicines, including tracking stock levels, handling replenishment requests, 
 * and serializing data for persistence.
 * It provides methods for getting, updating, and managing medicine stock, as well as handling replenishment requests.
 * The inventory is stored in a map where the key is the medicine name and the value is a list containing the stock and low stock alert level.
 */
public class MedicineInventory {
	private Map<String, List<Integer>> medicineStock = new HashMap<>();
	private final List<ReplenishRequest> replenishmentRequestList = new ArrayList<ReplenishRequest>();
	private static final String FILEPATH = "./src/main/resources/Medicine_List.csv";

	/**
     * Constructor that initializes the medicine inventory by loading data from a serialized file.
     * 
     * @param medicineInventorySerializer the serializer to deserialize the inventory data
     */
	public MedicineInventory() {
		MedicineInventorySerializer medicineInventorySerializer = new MedicineInventorySerializer(FILEPATH);
		medicineInventorySerializer.serialize();
		this.medicineStock = medicineInventorySerializer.getMedicineStock();
		this.replenishmentRequestList.addAll(medicineInventorySerializer.getReplenishmentRequestList());
	}

	/**
     * Retrieves the full medicine stock.
     * 
     * @return a map of all medicines and their stock levels
     */
	public Map<String, List<Integer>> getFullMedicine() {
		return medicineStock;
	}

	/**
     * Retrieves a list of all medicine names in the inventory.
     * 
     * @return a list of all medicine names
     */
	public List<String> getMedicineNames() {
		List<String> temp_r = new ArrayList<>(medicineStock.keySet());
		return temp_r;
	}

	/**
     * Retrieves the current stock of a specified medicine.
     * 
     * @param medicineName the name of the medicine
     * @return the current stock level of the specified medicine
     */
	public Integer getMedicineStock(String medicineName) {
		return medicineStock.get(medicineName).get(0);
	}

	/**
     * Adds a specified amount of stock to a given medicine.
     * 
     * @param name   the name of the medicine
     * @param amount the amount of stock to add
     */
	public void addMedicineStock(String name, int amount) {
		List<Integer> tempList = medicineStock.get(name);
		int oldStock = tempList.get(0);
		tempList.set(0, oldStock + amount);
	}

	/**
     * Removes a specified amount of stock from a given medicine.
     * 
     * @param name   the name of the medicine
     * @param amount the amount of stock to remove
     */
	public void removeMedicineStock(String name, int amount) {
		List<Integer> tempList = medicineStock.get(name);
		int oldStock = tempList.get(0);
		tempList.set(0, oldStock - amount);
	}

	/**
     * Sets the stock level of a specified medicine to a given amount.
     * 
     * @param name   the name of the medicine
     * @param amount the new stock level to set
     */
	public void setMedicineStock(String name, int amount) {
		List<Integer> tempList = medicineStock.get(name);
		tempList.set(0, amount);
	}

	/**
     * Retrieves a map of medicines that are at or below their low stock level alert.
     * 
     * @return a map of medicine names and their low stock alert levels
     */
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

	/**
     * Sets the low stock alert level for a specified medicine.
     * 
     * @param medicineName the name of the medicine
     * @param amount       the low stock alert level to set
     */
	public void setStockWarningLevel(String medicineName, int amount) {
		List<Integer> tempList = medicineStock.get(medicineName);
		tempList.set(1, amount);
	}

	/**
     * Dispenses a specified quantity of a given medicine if there is sufficient stock.
     * 
     * @param medicineName      the name of the medicine to dispense
     * @param quantityToDispense the quantity to dispense
     * @return {@code true} if the medicine was successfully dispensed, {@code false} if there is insufficient stock
     */
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

	/**
     * Retrieves a list of all replenishment requests.
     * 
     * @return a list of replenishment requests
     */
	public List<ReplenishRequest> getReplenishmentRequestList() {
		return this.replenishmentRequestList;
	}

	/**
     * Adds a replenishment request to the list.
     * 
     * @param replenishRequest the replenishment request to add
     */
	public void addReplenishmentRequest(ReplenishRequest replenishRequest) {
		this.replenishmentRequestList.add(replenishRequest);
	}

	/**
     * Approves a replenishment request and adds the requested stock to the medicine inventory.
     * 
     * @param replenishRequest the replenishment request to approve
     */
	public void approveReplenishmentRequest(ReplenishRequest replenishRequest) {
		String medicineName = replenishRequest.getMedicineName();
		List<Integer> tempList = medicineStock.get(medicineName);
		tempList.set(0, replenishRequest.getStockToAdd() + tempList.get(0));
		medicineStock.put(medicineName, tempList);
		removeReplenishmentRequest(replenishRequest);
	}

	/**
     * Removes a replenishment request from the list.
     * 
     * @param replenishRequest the replenishment request to remove
     */
	public void removeReplenishmentRequest(ReplenishRequest replenishRequest) {
		this.replenishmentRequestList.remove(replenishRequest);
	}

	/**
     * Deserializes the medicine inventory data and writes it to a CSV file.
     * The data includes medicine names, stock levels, low stock levels, and replenishment requests.
     */
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
			e.printStackTrace();
		}
	}
}
