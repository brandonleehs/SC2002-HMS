package hms.record;

import java.time.LocalDate;

import hms.attributes.BloodType;
import hms.attributes.Gender;

public class MedicalRecord {

	private final MedicalRecordDetails medicalRecordDetails;
	private String phoneNumber;
	private String emailAddress;
	// private AppointmentRecord appointmentRecord;

	public MedicalRecord(MedicalRecordDetails medicalRecordDetails, String phoneNumber, String emailAddress) {
		this.medicalRecordDetails = medicalRecordDetails;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	public String getId() {
		return this.medicalRecordDetails.getId();
	}

	public String getName() {
		return this.medicalRecordDetails.getName();
	}

	public LocalDate getDateOfBirth() {
		return this.medicalRecordDetails.getDateOfBirth();
	}

	public Gender getGender() {
		return this.medicalRecordDetails.getGender();
	}

	public BloodType getBloodType() {
		return this.medicalRecordDetails.getBloodType();
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

}