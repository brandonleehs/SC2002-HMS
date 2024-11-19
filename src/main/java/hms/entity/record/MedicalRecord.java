package hms.entity.record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;


/**
 * Represents a medical record for a patient, containing personal details, 
 * contact information, and a list of appointment outcome records.
 */
public class MedicalRecord {
	private final String id;
	private final String name;
	private final LocalDate dateOfBirth;
	private final Gender gender;
	private final BloodType bloodType;
	private String phoneNumber;
	private String emailAddress;
	private final List<AppointmentOutcomeRecord> appointmentOutcomeRecordList;

	/**
     * Constructs a new {@code MedicalRecord} instance with the specified details.
     *
     * @param id            the unique identifier for the patient.
     * @param name          the name of the patient.
     * @param dateOfBirth   the date of birth of the patient.
     * @param gender        the gender of the patient.
     * @param bloodType     the blood type of the patient.
     * @param phoneNumber   the contact phone number of the patient.
     * @param emailAddress  the contact email address of the patient.
     */
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

	/**
     * Retrieves the unique identifier for the patient.
     *
     * @return the patient ID.
     */
	public String getId() {
		return this.id;
	}

	/**
     * Retrieves the name of the patient.
     *
     * @return the patient's name.
     */
	public String getName() {
		return this.name;
	}

	/**
     * Retrieves the date of birth of the patient.
     *
     * @return the patient's date of birth.
     */
	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
     * Retrieves the gender of the patient.
     *
     * @return the patient's gender.
     */
	public Gender getGender() {
		return this.gender;
	}

	/**
     * Retrieves the blood type of the patient.
     *
     * @return the patient's blood type.
     */
	public BloodType getBloodType() {
		return this.bloodType;
	}

	/**
     * Retrieves the contact phone number of the patient.
     *
     * @return the patient's phone number.
     */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
     * Retrieves the contact email address of the patient.
     *
     * @return the patient's email address.
     */
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
     * Updates the contact email address of the patient.
     *
     * @param emailAddress the new email address.
     */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
     * Updates the contact phone number of the patient.
     *
     * @param phoneNumber the new phone number.
     */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
     * Retrieves the list of appointment outcome records associated with this medical record.
     *
     * @return a list of {@code AppointmentOutcomeRecord} instances.
     */
	public List<AppointmentOutcomeRecord> getAppointmentOutcomeRecordList() {
		return this.appointmentOutcomeRecordList;
	}

	/**
     * Adds an appointment outcome record to the patient's medical record.
     *
     * @param appointmentOutcomeRecord the appointment outcome record to add.
     */
	public void addAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		this.appointmentOutcomeRecordList.add(appointmentOutcomeRecord);
	}
}