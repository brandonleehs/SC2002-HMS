package hms.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import hms.entity.user.Patient;
import hms.serializer.PatientSerializer;

/**
 * The PatientRepository class manages the collection of patient records,
 * providing methods for accessing, modifying, and serializing patient data. It
 * uses a map to store patients, where the key is the patient ID and the value
 * is the patient object.
 */
public class PatientRepository implements IUserRepository<Patient> {
	private final Map<String, Patient> patientMap;
	private final static String FILEPATH = "./src/main/resources/Patient_List.csv";

	/**
	 * Constructor that initializes the PatientRepository by deserializing patient
	 * data from a CSV file.
	 * 
	 * @param patientSerializer the serializer to deserialize the patient data
	 */
	public PatientRepository() {
		PatientSerializer patientSerializer = new PatientSerializer(FILEPATH);
		this.patientMap = patientSerializer.getMap();
	}

	/**
	 * Retrieves a patient by their ID.
	 * 
	 * @param id the ID of the patient to retrieve
	 * @return the patient associated with the specified ID, or {@code null} if no
	 *         patient exists with that ID
	 */
	@Override
	public Patient getById(String id) {
		return this.patientMap.get(id);
	}

	/**
	 * Removes a patient from the repository by their ID.
	 * 
	 * @param id the ID of the patient to remove
	 */
	@Override
	public void removeById(String id) {
		this.patientMap.remove(id);
	}

	/**
	 * Retrieves all patients in the repository.
	 * 
	 * @return a list of all patients
	 */
	@Override
	public List<Patient> getAll() {
		return this.patientMap.values().stream().toList();
	}

	/**
	 * Retrieves the map containing all patients.
	 * 
	 * @return a map of patient IDs to patient objects
	 */
	@Override
	public Map<String, Patient> getMap() {
		return patientMap;
	}

	/**
	 * Adds a new patient to the repository.
	 * 
	 * @param id      the ID of the patient to add
	 * @param patient the Patient object to add
	 */
	public void addPatient(String id, Patient patient) {
		patientMap.put(id, patient);
	}

	/**
	 * Serializes the patient data and writes it to a CSV file. The data includes
	 * patient ID, name, date of birth, gender, blood type, contact information,
	 * phone number, and password hash.
	 * 
	 */
	@Override
	public void deserialize() {
		File file = new File(FILEPATH);
		try {
			PrintWriter printWriter = new PrintWriter(file);
			String header = String.join(",", "Patient ID", "Name", "Date of Birth", "Gender", "Blood Type",
					"Contact Information", "Phone Number", "Password Hash");
			printWriter.println(header);
			for (Patient patient : getAll()) {
				String data = String.join(",", patient.getId(), patient.getName(), patient.getDateOfBirth().toString(),
						patient.getGender().toString(), patient.getBloodType().toString(), patient.getEmailAddress(),
						patient.getPhoneNumber(), patient.getPasswordHash());
				printWriter.println(data);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
