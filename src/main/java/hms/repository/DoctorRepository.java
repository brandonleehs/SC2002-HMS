package hms.repository;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import hms.entity.user.Doctor;
import hms.serializer.DoctorSerializer;

/**
 * This class represents a repository for managing {@link Doctor} objects. It is
 * responsible for serializing and deserializing doctor data to and from a CSV
 * file. It provides methods to retrieve, add, and remove doctors, as well as
 * access their data in a map.
 */
public class DoctorRepository implements IUserRepository<Doctor> {
	private final Map<String, Doctor> doctorMap;
	private static final String FILEPATH = "./src/main/resources/Staff_List.csv";

	/**
	 * Constructs a new {@code DoctorRepository}. This constructor initializes the
	 * repository by deserializing doctor data from a file using the
	 * {@link DoctorSerializer}.
	 */
	public DoctorRepository() {
		DoctorSerializer doctorSerializer = new DoctorSerializer(FILEPATH);
		this.doctorMap = doctorSerializer.getMap();
	}

	/**
	 * Retrieves a doctor by their unique identifier.
	 *
	 * @param id the unique identifier of the doctor
	 * @return the {@link Doctor} object with the given ID, or {@code null} if not
	 *         found
	 */
	@Override
	public Doctor getById(String id) {
		return this.doctorMap.get(id);
	}

	/**
	 * Removes a doctor by their unique identifier.
	 *
	 * @param id the unique identifier of the doctor to be removed
	 */
	@Override
	public void removeById(String id) {
		this.doctorMap.remove(id);
	}

	/**
	 * Retrieves all doctors in the repository.
	 *
	 * @return a list of all {@link Doctor} objects in the repository
	 */
	@Override
	public List<Doctor> getAll() {
		return this.doctorMap.values().stream().toList();
	}

	/**
	 * Retrieves the map of doctors by their unique identifiers.
	 *
	 * @return the map of doctors with their IDs as the keys
	 */
	@Override
	public Map<String, Doctor> getMap() {
		return this.doctorMap;
	}

	/**
	 * Adds a new doctor to the repository.
	 *
	 * @param id     the unique identifier of the doctor
	 * @param doctor the {@link Doctor} object to be added
	 */
	public void addDoctor(String id, Doctor doctor) {
		doctorMap.put(id, doctor);
	}

	/**
	 * Deserializes the doctor data and writes it to a CSV file. The file includes
	 * the doctor's ID, name, role, gender, age, password hash, and availability
	 * schedule.
	 * 
	 */
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
