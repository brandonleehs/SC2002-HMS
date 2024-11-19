package hms.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Doctor;
import hms.entity.user.attributes.Gender;

/**
 * The DoctorSerializer class is responsible for deserializing doctor data from a CSV file and
 * creating Doctor objects. It reads data from the file and maps the details to a Doctor object,
 * including availability information, which is decoded from an integer into a boolean array.
 */
public class DoctorSerializer extends UserSerializer<Doctor> {

	/**
     * Constructs a DoctorSerializer to read data from the specified file.
     * This constructor initializes the serializer with the provided file path.
     *
     * @param filepath the path to the CSV file containing doctor data
     */
	public DoctorSerializer(String filepath) {
		super(filepath);
	}

	/**
     * Deserializes doctor data from the CSV file into a map of Doctor objects.
     * It reads each line of the file, splits it into fields, and creates a Doctor object if the
     * role is "Doctor". The Doctor object is then added to the doctor map.
     *
     * @return a map of doctor IDs to Doctor objects
     */
	@Override
	public Map<String, Doctor> getMap() {
		Map<String, Doctor> doctorMap = new HashMap<String, Doctor>();

		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				String role = row[2];
				if (role.equals("Doctor")) {
					Doctor doctor = getDoctorFromRow(row);
					doctorMap.put(doctor.getId(), doctor);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doctorMap;
	}

	/**
     * Converts a CSV row into a Doctor object. The row contains details such as the doctor's ID, 
     * name, gender, age, and password hash. Additionally, it decodes the doctor's availability 
     * schedule from the row and sets it on the Doctor object.
     * 
     * @param row the CSV row containing the doctor data
     * @return a Doctor object created from the CSV row
     */
	private Doctor getDoctorFromRow(String[] row) {
		String id = row[0];
		String name = row[1];
		Gender gender = row[3].equals("Male") ? Gender.MALE : Gender.FEMALE;
		int age = Integer.parseInt(row[4]);
		if (row.length < 6) {
			return new Doctor(id, "password", name, gender, age);
		}
		String passwordHash = row[5];
		Doctor doctor = new Doctor(id, "password", name, gender, age);
		doctor.setPasswordHash(passwordHash);
		decodeAvailableMap(doctor, row);

		return doctor;
	}

	/**
     * Decodes the doctor's availability schedule from the CSV row and sets it on the Doctor object.
     * The availability is stored as a boolean array for each date. The method uses an integer to
     * represent the availability for each 30-minute interval throughout a day.
     * 
     * @param doctor the Doctor object to set the availability on
     * @param row the CSV row containing the availability data
     */
	// Decode integer into boolean array
	private void decodeAvailableMap(Doctor doctor, String[] row) {
		for (int i = 6; i < row.length; i += 2) {
			LocalDate date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(row[i]));
			int n = Integer.valueOf(row[i + 1]);
			boolean[] availability = new boolean[48];
			for (int j = 0; j < 48; j++) {
				if ((n & 1 << j) != 0) {
					availability[j] = true;
				}
			}
			doctor.getSchedule().getAvailableMap().put(date, availability);
		}
	}
}
