package hms.serializer;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import hms.entity.medicine.MedicineInventory;

public class MedicineInventorySerializer extends Serializer {
	public void getMedicineInventory(String filepath) {
		Workbook wb = getWorkbook(filepath);
		for (Sheet sheet : wb) {
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				setMedicineStock(sheet.getRow(rowNum));
			}
		}
	}

	private void setMedicineStock(Row row) {
		DataFormatter formatter = new DataFormatter();
		String name = formatter.formatCellValue(row.getCell(0));
		int stock = Integer.parseInt(formatter.formatCellValue(row.getCell(1)));
		int lowStockLeveLAlertValue = Integer.parseInt(formatter.formatCellValue(row.getCell(2)));
		MedicineInventory.getInstance().setMedicineStock(name, stock);
		MedicineInventory.getInstance().setLowStockLevelAlertValue(name, lowStockLeveLAlertValue);
	}
}
