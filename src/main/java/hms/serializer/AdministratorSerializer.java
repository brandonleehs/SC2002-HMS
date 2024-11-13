package hms.serializer;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import hms.entity.user.Administrator;
import hms.entity.user.attributes.Gender;

public class AdministratorSerializer extends UserSerializer<Administrator> {
	@Override
	protected Map<String, Administrator> readWorkbook(Workbook wb) {
		Map<String, Administrator> administratorMap = new HashMap<String, Administrator>();
		for (Sheet sheet : wb) {
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Administrator administrator = getAdministratorFromRow(sheet.getRow(rowNum));
				if (administrator != null) {
					administratorMap.put(administrator.getId(), administrator);
				}
			}
		}
		return administratorMap;
	}

	private Administrator getAdministratorFromRow(Row row) {
		DataFormatter formatter = new DataFormatter();
		String role = formatter.formatCellValue(row.getCell(2));
		if (role.equals("Administrator")) {
			String id = formatter.formatCellValue(row.getCell(0));
			String name = formatter.formatCellValue(row.getCell(1));
			Gender gender = formatter.formatCellValue(row.getCell(3)).equals("Male") ? Gender.MALE : Gender.FEMALE;
			int age = Integer.parseInt(formatter.formatCellValue(row.getCell(4)));
			return new Administrator(id, "password", name, gender, age);
		}
		return null;
	}
}
