package hms.model;

import hms.record.MedicalRecord;

public class Patient {
	private final MedicalRecord medicalRecord;

	public Patient(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	// To implement update functions, etc.
}