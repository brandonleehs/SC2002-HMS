package hms.repository;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import hms.entity.user.Doctor;
import hms.serializer.DoctorSerializer;

public class DoctorRepository implements IUserRepository<Doctor> {
	private final Map<String, Doctor> doctorMap;
	private static final String FILEPATH = "./src/main/resources/Staff_List.csv";

	public DoctorRepository() {
		DoctorSerializer doctorSerializer = new DoctorSerializer(FILEPATH);
		this.doctorMap = doctorSerializer.getMap();
	}

	@Override
	public Doctor getById(String id) {
		return this.doctorMap.get(id);
	}

	@Override
	public void removeById(String id) {
		this.doctorMap.remove(id);
	}

	@Override
	public List<Doctor> getAll() {
		return this.doctorMap.values().stream().toList();
	}

	@Override
	public Map<String, Doctor> getMap() {
		return this.doctorMap;
	}

	public void addDoctor(String id, Doctor doctor) {
		doctorMap.put(id, doctor);
	}

	@Override
	public void deserialize() {
		File file = new File(FILEPATH);
		try {
			PrintWriter printWriter = new PrintWriter(file);
			String header = String.join(",", "Staff ID", "Name", "Role", "Gender", "Age", "Password Hash",
					"Availability");
			printWriter.println(header);
			for (Doctor doctor : getAll()) {
				String data = String.join(",", doctor.getId(), doctor.getName(), "Doctor",
						doctor.getGender().toString(), String.valueOf(doctor.getAge()), doctor.getPasswordHash());
				data += encodeAvailableMap(doctor);
				printWriter.println(data);
			}
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Encode boolean array into an integer
	private String encodeAvailableMap(Doctor doctor) {
		String data = "";
		Map<LocalDate, boolean[]> availableMap = doctor.getSchedule().getAvailableMap();
		for (Map.Entry<LocalDate, boolean[]> entry : availableMap.entrySet()) {
			int n = 0;
			LocalDate date = entry.getKey();
			for (int i = 0; i < entry.getValue().length; i++) {
				if (entry.getValue()[i]) {
					n |= 1 << i;
				}
			}
			data += "," + date.toString() + "," + String.valueOf(n);
		}
		return data;
	}
}
