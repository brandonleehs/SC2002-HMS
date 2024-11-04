package hms.repository;

import java.util.List;
import java.util.Map;

import hms.entity.user.Patient;
import hms.serializer.PatientSerializer;

public class PatientRepository implements IUserRepository<Patient> {
	private final Map<String, Patient> patientMap;

	public PatientRepository() {
		PatientSerializer patientSerializer = new PatientSerializer();
		this.patientMap = patientSerializer.getMap("Patient_List.xlsx");
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
}
