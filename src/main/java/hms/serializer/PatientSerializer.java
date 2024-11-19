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

/**
 * The PatientSerializer class is responsible for deserializing patient data from a CSV file and
 * creating Patient objects. It reads patient information, including personal and medical details,
 * and stores the data in a map for further use.
 */
public class PatientSerializer extends UserSerializer<Patient> {

	/**
     * Constructs a PatientSerializer to read data from the specified file.
     * This constructor initializes the serializer with the provided file path.
     *
     * @param filepath the path to the CSV file containing patient data
     */
	public PatientSerializer(String filepath) {
		super(filepath);
	}

	/**
     * Reads the CSV file and processes each line, creating a Patient object from the data in each row.
     * Each row is passed to the getPatientFromRow method for processing. The method returns a map
     * of patient IDs to Patient objects.
     *
     * @return a map of patient IDs to Patient objects
     */
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
			e.printStackTrace();
		}
		return patientMap;
	}

	/**
     * Creates a Patient object from the data in a CSV row. The method parses the patient's personal
     * and medical details, including ID, name, date of birth, gender, blood type, email, phone number,
     * and password hash, and returns a fully initialized Patient object.
     *
     * @param row an array of strings representing a single row from the CSV file
     * @return a Patient object created from the row data
     */
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
}
