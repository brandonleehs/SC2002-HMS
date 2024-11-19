package hms.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hms.entity.medicine.ReplenishRequest;

/**
 * The MedicineInventorySerializer class is responsible for deserializing medicine inventory data
 * from a CSV file and storing the data in memory. It parses each row of the file, extracting details
 * about the medicine stock and replenishment requests.
 */
public class MedicineInventorySerializer extends Serializer {
	private final Map<String, List<Integer>> medicineStock = new HashMap<String, List<Integer>>();
	private final List<ReplenishRequest> replenishmentRequestList = new ArrayList<ReplenishRequest>();

	/**
     * Constructs a MedicineInventorySerializer to read data from the specified file.
     * This constructor initializes the serializer with the provided file path.
     *
     * @param filepath the path to the CSV file containing the medicine inventory data
     */
	public MedicineInventorySerializer(String filepath) {
		super(filepath);
	}

	/**
     * Reads the CSV file and processes each line, extracting medicine stock data and
     * replenishment requests. Each row is passed to the setData method for further processing.
     * The file is expected to have columns representing the medicine name, stock, low stock alert value,
     * and optionally, a replenishment request with quantity.
     */
	public void serialize() {
		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				setData(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Processes a CSV row and sets the medicine stock and replenishment request data.
     * It extracts the medicine name, stock level, low stock alert value, and if available,
     * a replenishment request with the supplier and quantity. The extracted data is stored
     * in the medicineStock map and replenishmentRequestList.
     *
     * @param row the CSV row containing medicine data (name, stock, alert level, and optional replenishment request)
     */
	private void setData(String[] row) {
		List<Integer> temp = new ArrayList<Integer>(2);
		String name = row[0];
		int stock = Integer.parseInt(row[1]);
		int lowStockLeveLAlertValue = Integer.parseInt(row[2]);
		if (row.length > 3) {
			ReplenishRequest replenishRequest = new ReplenishRequest(row[3], Integer.valueOf(row[4]));
			this.replenishmentRequestList.add(replenishRequest);
		}
		temp.add(stock);
		temp.add(lowStockLeveLAlertValue);
		this.medicineStock.put(name, temp);
	}

	/**
     * Returns a map of medicine names to a list containing their stock level and low stock alert value.
     * The map is populated during the deserialization process from the CSV file.
     *
     * @return a map of medicine names to a list containing stock and alert level
     */
	public Map<String, List<Integer>> getMedicineStock() {
		return this.medicineStock;
	}

	/**
     * Returns a list of replenishment requests, each containing a supplier name and requested quantity.
     * The list is populated during the deserialization process if replenishment request data is available in the CSV file.
     *
     * @return a list of replenishment requests
     */
	public List<ReplenishRequest> getReplenishmentRequestList() {
		return this.replenishmentRequestList;
	}
}
