package hms.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hms.attributes.BloodType;
import hms.attributes.Gender;
import hms.model.Patient;
import hms.record.MedicalRecord;

public class PatientSerializer {

	public PatientSerializer() {
	}

	public static Map<String, Patient> getPatientMap(String filepath) throws IOException, InvalidFormatException {
		InputStream in = PatientSerializer.class.getClassLoader().getResourceAsStream(filepath);
		OPCPackage pkg = OPCPackage.open(in);
		Workbook wb = new XSSFWorkbook(pkg);
		pkg.close();
		in.close();
		return readWorkbook(wb);
	}

	private static Map<String, Patient> readWorkbook(Workbook wb) {
		Map<String, Patient> patientMap = new HashMap<String, Patient>();
		for (Sheet sheet : wb) {
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Patient patient = getPatientFromRow(sheet.getRow(rowNum));
				patientMap.put(patient.getId(), patient);
			}
		}
		return patientMap;
	}

	private static Patient getPatientFromRow(Row row) {
		DataFormatter formatter = new DataFormatter();
		String id = formatter.formatCellValue(row.getCell(0));
		String name = formatter.formatCellValue(row.getCell(1));
		// Get String representation
		String dateOfBirthString = formatter.formatCellValue(row.getCell(2));
		// Parse as LocalDate
		LocalDate dateOfBirth = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateOfBirthString));
		Gender gender = formatter.formatCellValue(row.getCell(3)).equals("Male") ? Gender.MALE : Gender.FEMALE;
		BloodType bloodType = getBloodType(formatter.formatCellValue(row.getCell(4)));
		String emailAddress = formatter.formatCellValue(row.getCell(5));
		MedicalRecord medicalRecord = new MedicalRecord(id, name, dateOfBirth, gender, bloodType, "12345678",
				emailAddress);
		return new Patient(medicalRecord, "password");
	}

	private static BloodType getBloodType(String bloodType) {
		switch (bloodType) {
		case "O+":
			return BloodType.O_POS;
		case "O-":
			return BloodType.O_NEG;
		case "A+":
			return BloodType.A_POS;
		case "A-":
			return BloodType.A_NEG;
		case "B+":
			return BloodType.B_POS;
		case "B-":
			return BloodType.B_NEG;
		case "AB+":
			return BloodType.AB_POS;
		default:
			return BloodType.AB_NEG;
		}
	}
}
