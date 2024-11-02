package hms.serializer;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import hms.entity.attributes.Gender;
import hms.entity.user.Pharmacist;

public class PharmacistSerializer extends UserSerializer<Pharmacist> {
	@Override
	protected Map<String, Pharmacist> readWorkbook(Workbook wb) {
		Map<String, Pharmacist> pharmacistMap = new HashMap<String, Pharmacist>();
		for (Sheet sheet : wb) {
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Pharmacist pharmacist = getPharmacistFromRow(sheet.getRow(rowNum));
				pharmacistMap.put(pharmacist.getId(), pharmacist);
			}
		}
		return pharmacistMap;
	}

	private Pharmacist getPharmacistFromRow(Row row) {
		DataFormatter formatter = new DataFormatter();
		String id = formatter.formatCellValue(row.getCell(0));
		String name = formatter.formatCellValue(row.getCell(1));
		Gender gender = formatter.formatCellValue(row.getCell(3)).equals("Male") ? Gender.MALE : Gender.FEMALE;
		int age = Integer.parseInt(formatter.formatCellValue(row.getCell(4)));
		return new Pharmacist(id, "password", name, gender, age);
	}
}
