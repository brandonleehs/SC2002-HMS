package hms.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hms.entity.medicine.ReplenishRequest;

public class MedicineInventorySerializer extends Serializer {
	private final Map<String, List<Integer>> medicineStock = new HashMap<String, List<Integer>>();
	private final List<ReplenishRequest> replenishmentRequestList = new ArrayList<ReplenishRequest>();

	public MedicineInventorySerializer(String filepath) {
		super(filepath);
	}

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

	public Map<String, List<Integer>> getMedicineStock() {
		return this.medicineStock;
	}

	public List<ReplenishRequest> getReplenishmentRequestList() {
		return this.replenishmentRequestList;
	}
}
