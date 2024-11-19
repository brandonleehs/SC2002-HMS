package hms.entity.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hms.auth.Validation;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.record.MedicalRecord;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

/**
 * Represents a Patient, a type of User who has medical records and can schedule,
 * reschedule, and cancel appointments, as well as manage appointment outcomes.
 */
public class Patient extends User {
	private final MedicalRecord medicalRecord;
	private final List<Appointment> scheduledAppointmentList;
	private final List<Appointment> allAppointmentList;

	/**
     * Constructs a Patient instance with the specified medical record and password.
     *
     * @param medicalRecord the medical record of the patient.
     * @param password the password associated with the patient's account.
     */
	public Patient(MedicalRecord medicalRecord, String password) {
		super(medicalRecord.getId(), password, medicalRecord.getName(), medicalRecord.getGender());
		this.medicalRecord = medicalRecord;
		this.scheduledAppointmentList = new ArrayList<Appointment>();
		this.allAppointmentList = new ArrayList<Appointment>();
	}

	/**
     * Sets the phone number for the patient by updating it in the medical record.
     *
     * @param phoneNumber the phone number to be set for the patient.
     */
	public void setPhoneNumber(String phoneNumber) {
		this.medicalRecord.setPhoneNumber(phoneNumber);
	}

	/**
     * Sets the email address for the patient if it is valid.
     *
     * @param emailAddress the email address to be set for the patient.
     * @return true if the email address was valid and successfully set, false otherwise.
     */
	public boolean setEmailAddress(String emailAddress) {
		if (Validation.validateEmailAddress(emailAddress)) {
			this.medicalRecord.setEmailAddress(emailAddress);
			return true;
		}
		return false;
	}

	/**
     * Retrieves the unique ID of the patient.
     *
     * @return the ID of the patient.
     */
	public String getId() {
		return this.medicalRecord.getId();
	}

	/**
     * Retrieves the name of the patient.
     *
     * @return the name of the patient.
     */
	public String getName() {
		return this.medicalRecord.getName();
	}

	/**
     * Retrieves the date of birth of the patient.
     *
     * @return the date of birth of the patient.
     */
	public LocalDate getDateOfBirth() {
		return this.medicalRecord.getDateOfBirth();
	}

	/**
     * Retrieves the gender of the patient.
     *
     * @return the gender of the patient.
     */
	public Gender getGender() {
		return this.medicalRecord.getGender();
	}

	/**
     * Retrieves the blood type of the patient.
     *
     * @return the blood type of the patient.
     */
	public BloodType getBloodType() {
		return this.medicalRecord.getBloodType();
	}

	/**
     * Retrieves the phone number of the patient.
     *
     * @return the phone number of the patient.
     */
	public String getPhoneNumber() {
		return this.medicalRecord.getPhoneNumber();
	}

	/**
     * Retrieves the email address of the patient.
     *
     * @return the email address of the patient.
     */
	public String getEmailAddress() {
		return this.medicalRecord.getEmailAddress();
	}

	/**
     * Retrieves the list of appointment outcome records associated with the patient.
     *
     * @return a list of appointment outcome records.
     */
	public List<AppointmentOutcomeRecord> getAppointmentOutcomeRecordList() {
		return this.medicalRecord.getAppointmentOutcomeRecordList();
	}

	/**
     * Schedules an appointment for the patient with a specific doctor.
     *
     * @param doctor the doctor with whom the appointment is to be scheduled.
     * @param appointment the appointment to be scheduled.
     * @return true if the appointment was successfully scheduled, false otherwise.
     */
	public boolean scheduleAppointment(Doctor doctor, Appointment appointment) {
		if (doctor.scheduleAppointment(appointment)) {
			this.scheduledAppointmentList.add(appointment);
			this.allAppointmentList.add(appointment);
			return true;
		}
		return false;
	}

	/**
     * Reschedules an existing appointment with a new doctor and appointment details.
     *
     * @param oldDoctor the original doctor with whom the old appointment was scheduled.
     * @param newDoctor the new doctor with whom the new appointment is to be scheduled.
     * @param oldAppointment the existing appointment to be rescheduled.
     * @param newAppointment the new appointment details.
     * @return true if the appointment was successfully rescheduled, false otherwise.
     */
	public boolean rescheduleAppointment(Doctor oldDoctor, Doctor newDoctor, Appointment oldAppointment,
			Appointment newAppointment) {
		if (newDoctor.scheduleAppointment(newAppointment)) {
			cancelAppointment(oldDoctor, oldAppointment);
			this.scheduledAppointmentList.add(newAppointment);
			this.scheduledAppointmentList.remove(oldAppointment);
			this.allAppointmentList.add(newAppointment);
			return true;
		}
		return false;
	}

	/**
     * Cancels an existing appointment with the specified doctor.
     *
     * @param doctor the doctor associated with the appointment to be cancelled.
     * @param appointment the appointment to be cancelled.
     */
	public void cancelAppointment(Doctor doctor, Appointment appointment) {
		this.scheduledAppointmentList.remove(appointment);
		appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
		doctor.cancelAppointment(appointment);
	}

	/**
     * Adds an appointment outcome record to the patient's medical record.
     *
     * @param appointmentOutcomeRecord the outcome record to be added.
     */
	public void addAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		this.medicalRecord.addAppointmentOutcomeRecord(appointmentOutcomeRecord);
	}

	/**
     * Retrieves the list of scheduled appointments for the patient.
     *
     * @return a list of scheduled appointments.
     */
	public List<Appointment> getScheduledAppointmentList() {
		return this.scheduledAppointmentList;
	}

	/**
     * Retrieves the list of all appointments (both scheduled and completed) for the patient.
     *
     * @return a list of all appointments.
     */
	public List<Appointment> getAllAppointmentList() {
		return this.allAppointmentList;
	}
}