package hms.model;

import hms.attributes.Role;
import hms.record.MedicalRecord;

public class Patient extends User {
	private final MedicalRecord medicalRecord;

	public Patient(MedicalRecord medicalRecord, String password) {
		super(medicalRecord.getId(), password, medicalRecord.getName(), medicalRecord.getGender(), Role.PATIENT);
		this.medicalRecord = medicalRecord;
	}

	// To implement rest of getters/setters, update functions, etc.
}