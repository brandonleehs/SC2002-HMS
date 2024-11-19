package hms.entity.record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

public class MedicalRecord {
	private final String id;
	private final String name;
	private final LocalDate dateOfBirth;
	private final Gender gender;
	private final BloodType bloodType;
	private String phoneNumber;
	private String emailAddress;
	private final List<AppointmentOutcomeRecord> appointmentOutcomeRecordList;

	public MedicalRecord(String id, String name, LocalDate dateOfBirth, Gender gender, BloodType bloodType,
			String phoneNumber, String emailAddress) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.bloodType = bloodType;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.appointmentOutcomeRecordList = new ArrayList<AppointmentOutcomeRecord>();
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<AppointmentOutcomeRecord> getAppointmentOutcomeRecordList() {
		return this.appointmentOutcomeRecordList;
	}

	public void addAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		this.appointmentOutcomeRecordList.add(appointmentOutcomeRecord);
	}
}