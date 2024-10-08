package hms.record;

import java.time.LocalDate;

import hms.attributes.BloodType;
import hms.attributes.Gender;

// Auxiliary class for reducing arguments in MedicalRecord constructor
public class MedicalRecordDetails {
	private final String id;
	private final String name;
	private final LocalDate dateOfBirth;
	private final Gender gender;
	private final BloodType bloodType;

	public MedicalRecordDetails(String id, String name, LocalDate dateOfBirth, Gender gender, BloodType bloodType) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.bloodType = bloodType;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	public Gender getGender() {
		return this.gender;
	}

	public BloodType getBloodType() {
		return this.bloodType;
	}
}
