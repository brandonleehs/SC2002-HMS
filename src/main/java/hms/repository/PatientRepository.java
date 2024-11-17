package hms.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import hms.entity.user.Patient;
import hms.serializer.PatientSerializer;

public class PatientRepository implements IUserRepository<Patient> {
	private final Map<String, Patient> patientMap;
	private final static String FILEPATH = "./src/main/resources/Patient_List.csv";

	public PatientRepository() {
		PatientSerializer patientSerializer = new PatientSerializer(FILEPATH);
		this.patientMap = patientSerializer.getMap();
	}

	@Override
	public Patient getById(String id) {
		return this.patientMap.get(id);
	}

	@Override
	public void removeById(String id) {
		this.patientMap.remove(id);
	}

	@Override
	public List<Patient> getAll() {
		return this.patientMap.values().stream().toList();
	}

	@Override
	public Map<String, Patient> getMap() {
		return patientMap;
	}

	public void addPatient(String id, Patient patient) {
		patientMap.put(id, patient);
	}

	@Override
	public void deserialize() {
		File file = new File(FILEPATH);
		try {
			PrintWriter printWriter = new PrintWriter(file);
			String header = String.join(",", "Patient ID", "Name", "Date of Birth", "Gender", "Blood Type",
					"Contact Information", "Password Hash");
			printWriter.println(header);
			for (Patient patient : getAll()) {
				String data = String.join(",", patient.getId(), patient.getName(), patient.getDateOfBirth().toString(),
						patient.getGender().toString(), patient.getBloodType().toString(), patient.getEmailAddress(),
						patient.getPasswordHash());
				printWriter.println(data);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
