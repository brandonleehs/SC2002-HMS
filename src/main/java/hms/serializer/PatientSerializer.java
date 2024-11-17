package hms.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import hms.entity.record.MedicalRecord;
import hms.entity.user.Patient;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

public class PatientSerializer extends UserSerializer<Patient> {

	public PatientSerializer(String filepath) {
		super(filepath);
	}

	@Override
	public Map<String, Patient> getMap() {
		Map<String, Patient> patientMap = new HashMap<String, Patient>();

		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				Patient patient = getPatientFromRow(row);
				patientMap.put(patient.getId(), patient);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patientMap;
	}

	private Patient getPatientFromRow(String[] row) {
		String id = row[0];
		String name = row[1];
		// Get String representation
		String dateOfBirthString = row[2];
		// Parse as LocalDate
		LocalDate dateOfBirth = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateOfBirthString));
		Gender gender = row[3].equals("Male") ? Gender.MALE : Gender.FEMALE;
		BloodType bloodType = BloodType.getBloodType(row[4]);
		String emailAddress = row[5];
		MedicalRecord medicalRecord = new MedicalRecord(id, name, dateOfBirth, gender, bloodType, "12345678",
				emailAddress);
		if (row.length <= 6) {
			return new Patient(medicalRecord, "password");
		}
		medicalRecord.setPhoneNumber(row[6]);
		String passwordHash = row[7];
		Patient patient = new Patient(medicalRecord, "password");
		patient.setPasswordHash(passwordHash);
		return patient;
	}

//	private Patient getPatientFromRow() {
//		String id = scanner.next();
//		String name = scanner.next();
//		// Get String representation
//		String dateOfBirthString = scanner.next();
//		// Parse as LocalDate
//		LocalDate dateOfBirth = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateOfBirthString));
//		Gender gender = scanner.next().equals("Male") ? Gender.MALE : Gender.FEMALE;
//		BloodType bloodType = BloodType.getBloodType(scanner.next());
//		this.scanner.skip(",");
//		String emailAddress = scanner.nextLine();
//		MedicalRecord medicalRecord = new MedicalRecord(id, name, dateOfBirth, gender, bloodType, "12345678",
//				emailAddress);
//		return new Patient(medicalRecord, "password");
//	}

//	@Override
//	protected Map<String, Patient> readWorkbook(Workbook wb) {
//		Map<String, Patient> patientMap = new HashMap<String, Patient>();
//		for (Sheet sheet : wb) {
//			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
//				Patient patient = getPatientFromRow(sheet.getRow(rowNum));
//				patientMap.put(patient.getId(), patient);
//			}
//		}
//		return patientMap;
//	}
//
//	private Patient getPatientFromRow(Row row) {
//		DataFormatter formatter = new DataFormatter();
//		String id = formatter.formatCellValue(row.getCell(0));
//		String name = formatter.formatCellValue(row.getCell(1));
//		// Get String representation
//		String dateOfBirthString = formatter.formatCellValue(row.getCell(2));
//		// Parse as LocalDate
//		LocalDate dateOfBirth = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateOfBirthString));
//		Gender gender = formatter.formatCellValue(row.getCell(3)).equals("Male") ? Gender.MALE : Gender.FEMALE;
//		BloodType bloodType = BloodType.getBloodType(formatter.formatCellValue(row.getCell(4)));
//		String emailAddress = formatter.formatCellValue(row.getCell(5));
//		MedicalRecord medicalRecord = new MedicalRecord(id, name, dateOfBirth, gender, bloodType, "12345678",
//				emailAddress);
//		return new Patient(medicalRecord, "password");
//	}

}
