package hms.serializer;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import hms.repository.MedicineInventory;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class MedicineInventorySerializer extends Serializer {
	private Map<String, List<Integer>> medicineStock = new HashMap<String, List<Integer>>();

	public Map<String, List<Integer>>getMedicineInventory(String filepath) {
		Workbook wb = getWorkbook(filepath);
		for (Sheet sheet : wb) {
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				setMedicineStock(sheet.getRow(rowNum));
			}
		}
		List<Map<String, Integer>> maps = new ArrayList<>();
		return medicineStock;
	}

	private void setMedicineStock(Row row) {
		List<Integer> temp = new ArrayList<Integer>(2);
		DataFormatter formatter = new DataFormatter();
		String name = formatter.formatCellValue(row.getCell(0));
		int stock = Integer.parseInt(formatter.formatCellValue(row.getCell(1)));
		int lowStockLeveLAlertValue = Integer.parseInt(formatter.formatCellValue(row.getCell(2)));
		temp.add(stock);
		temp.add(lowStockLeveLAlertValue);
		medicineStock.put(name, temp);
	}
}
