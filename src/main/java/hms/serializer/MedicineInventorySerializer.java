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
			// TODO Auto-generated catch block
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

//	private void setMedicineStock() {
//		List<Integer> temp = new ArrayList<Integer>(2);
//		String name = this.scanner.next();
//		int stock = Integer.parseInt(this.scanner.next());
//		scanner.skip(",");
//		int lowStockLeveLAlertValue = Integer.parseInt(this.scanner.nextLine());
//		temp.add(stock);
//		temp.add(lowStockLeveLAlertValue);
//		this.medicineStock.put(name, temp);
//	}

//	public Map<String, List<Integer>> getMedicineInventory(String filepath) {
//		Workbook wb = getWorkbook(filepath);
//		for (Sheet sheet : wb) {
//			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
//				setMedicineStock(sheet.getRow(rowNum));
//			}
//		}
//		return medicineStock;
//	}
//
//	private void setMedicineStock(Row row) {
//		List<Integer> temp = new ArrayList<Integer>(2);
//		DataFormatter formatter = new DataFormatter();
//		String name = formatter.formatCellValue(row.getCell(0));
//		int stock = Integer.parseInt(formatter.formatCellValue(row.getCell(1)));
//		int lowStockLeveLAlertValue = Integer.parseInt(formatter.formatCellValue(row.getCell(2)));
//		temp.add(stock);
//		temp.add(lowStockLeveLAlertValue);
//		medicineStock.put(name, temp);
//	}
}
