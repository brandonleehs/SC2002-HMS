package hms.serializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import hms.entity.attributes.BloodType;
import hms.entity.attributes.Gender;
import hms.entity.record.MedicalRecord;
import hms.entity.user.Patient;

public class PatientSerializer extends UserSerializer<Patient> {

	@Override
	protected Map<String, Patient> readWorkbook(Workbook wb) {
		Map<String, Patient> patientMap = new HashMap<String, Patient>();
		for (Sheet sheet : wb) {
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Patient patient = getPatientFromRow(sheet.getRow(rowNum));
				patientMap.put(patient.getId(), patient);
			}
		}
		return patientMap;
	}

	private Patient getPatientFromRow(Row row) {
		DataFormatter formatter = new DataFormatter();
		String id = formatter.formatCellValue(row.getCell(0));
		String name = formatter.formatCellValue(row.getCell(1));
		// Get String representation
		String dateOfBirthString = formatter.formatCellValue(row.getCell(2));
		// Parse as LocalDate
		LocalDate dateOfBirth = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateOfBirthString));
		Gender gender = formatter.formatCellValue(row.getCell(3)).equals("Male") ? Gender.MALE : Gender.FEMALE;
		BloodType bloodType = BloodType.getBloodType(formatter.formatCellValue(row.getCell(4)));
		String emailAddress = formatter.formatCellValue(row.getCell(5));
		MedicalRecord medicalRecord = new MedicalRecord(id, name, dateOfBirth, gender, bloodType, "12345678",
				emailAddress);
		return new Patient(medicalRecord, "password");
	}

}
