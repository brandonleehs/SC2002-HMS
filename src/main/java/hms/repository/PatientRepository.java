package hms.repository;

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
}
