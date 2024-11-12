package hms.serializer;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import hms.entity.medicine.MedicineInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicineInventorySerializer extends Serializer {
	private HashMap<String, Integer> medicineStock = new HashMap<>();
	private HashMap<String, Integer> medicineLowStockLevelAlertValue = new HashMap<>();

	public List<HashMap<String, Integer>>getMedicineInventory(String filepath) {
		Workbook wb = getWorkbook(filepath);
		for (Sheet sheet : wb) {
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				setMedicineStock(sheet.getRow(rowNum));
			}
		}
		List<HashMap<String, Integer>> maps = new ArrayList<>();
		maps.add(medicineStock);
		maps.add(medicineLowStockLevelAlertValue);
		return maps;
	}

	private void setMedicineStock(Row row) {
		DataFormatter formatter = new DataFormatter();
		String name = formatter.formatCellValue(row.getCell(0));
		int stock = Integer.parseInt(formatter.formatCellValue(row.getCell(1)));
		int lowStockLeveLAlertValue = Integer.parseInt(formatter.formatCellValue(row.getCell(2)));
		medicineStock.put(name, stock);
		medicineLowStockLevelAlertValue.put(name, lowStockLeveLAlertValue);
	}
}
