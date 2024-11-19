package hms.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Doctor;
import hms.entity.user.attributes.Gender;

public class DoctorSerializer extends UserSerializer<Doctor> {

	public DoctorSerializer(String filepath) {
		super(filepath);
	}

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
