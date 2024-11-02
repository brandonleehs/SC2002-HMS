package hms.serializer;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import hms.entity.attributes.Gender;
import hms.entity.user.Doctor;

public class DoctorSerializer extends UserSerializer<Doctor> {

	@Override
	protected Map<String, Doctor> readWorkbook(Workbook wb) {
		Map<String, Doctor> doctorMap = new HashMap<String, Doctor>();
		for (Sheet sheet : wb) {
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Doctor doctor = getDoctorFromRow(sheet.getRow(rowNum));
				doctorMap.put(doctor.getId(), doctor);
			}
		}
		return doctorMap;
	}

	private Doctor getDoctorFromRow(Row row) {
		DataFormatter formatter = new DataFormatter();
		String id = formatter.formatCellValue(row.getCell(0));
		String name = formatter.formatCellValue(row.getCell(1));
		Gender gender = formatter.formatCellValue(row.getCell(3)).equals("Male") ? Gender.MALE : Gender.FEMALE;
		int age = Integer.parseInt(formatter.formatCellValue(row.getCell(4)));
		return new Doctor(id, "password", name, gender, age);
	}
}
