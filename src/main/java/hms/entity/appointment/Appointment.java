package hms.entity.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import hms.entity.record.AppointmentOutcomeRecord;
/**
 * Represents an appointment with details such as patient ID, doctor ID, 
 * date, time, and appointment status.
 */
public class Appointment {
	private UUID uuid;
	private final String patientId;
	private String doctorId;
	private AppointmentStatus appointmentStatus;
	private LocalDate date;
	private LocalTime time;
	private AppointmentOutcomeRecord appointmentOutcomeRecord;

	/**
     * Constructs an appointment with the specified patient ID, doctor ID, date, and time.
     * The appointment status is set to PENDING by default.
     *
     * @param patientId the ID of the patient.
     * @param doctorId the ID of the doctor.
     * @param date the date of the appointment.
     * @param time the time of the appointment.
     */
	public Appointment(String patientId, String doctorId, LocalDate date, LocalTime time) {
		this.uuid = UUID.randomUUID();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.time = time;
		this.appointmentStatus = AppointmentStatus.PENDING;
	}

	/**
     * Constructs an appointment with the specified UUID, patient ID, doctor ID, 
     * appointment status, date, and time.
     *
     * @param uuid the unique identifier for the appointment.
     * @param patientId the ID of the patient.
     * @param doctorId the ID of the doctor.
     * @param appointmentStatus the status of the appointment.
     * @param date the date of the appointment.
     * @param time the time of the appointment.
     */
	public Appointment(UUID uuid, String patientId, String doctorId, AppointmentStatus appointmentStatus,
			LocalDate date, LocalTime time) {
		this.uuid = uuid;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.time = time;
		this.appointmentStatus = appointmentStatus;
	}

	/**
     * Retrieves the patient ID associated with this appointment.
     *
     * @return the patient ID.
     */
	public String getPatientId() {
		return patientId;
	}

	/**
     * Retrieves the doctor ID associated with this appointment.
     *
     * @return the doctor ID.
     */
	public String getDoctorId() {
		return doctorId;
	}

	/**
     * Sets the doctor ID for this appointment.
     *
     * @param doctorId the new doctor ID.
     */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	/**
     * Retrieves the status of this appointment.
     *
     * @return the appointment status.
     */
	public AppointmentStatus getAppointmentStatus() {
		return this.appointmentStatus;
	}

	/**
     * Sets the status of this appointment.
     *
     * @param appointmentStatus the new appointment status.
     */
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	/**
     * Retrieves the date of this appointment.
     *
     * @return the appointment date.
     */
	public LocalDate getDate() {
		return this.date;
	}

	/**
     * Sets the date for this appointment.
     *
     * @param date the new date of the appointment.
     */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
     * Retrieves the time of this appointment.
     *
     * @return the appointment time.
     */
	public LocalTime getTime() {
		return this.time;
	}

	/**
     * Sets the time for this appointment.
     *
     * @param time the new time of the appointment.
     */
	public void setTime(LocalTime time) {
		this.time = time;
	}

	/**
     * Retrieves the outcome record of this appointment, if available.
     *
     * @return the appointment outcome record, or {@code null} if none exists.
     */
	public AppointmentOutcomeRecord getAppointmentOutcomeRecord() {
		return appointmentOutcomeRecord;
	}

	/**
     * Sets the outcome record for this appointment.
     *
     * @param appointmentOutcomeRecord the new outcome record.
     */
	public void setAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		this.appointmentOutcomeRecord = appointmentOutcomeRecord;
	}

	/**
     * Retrieves the unique identifier of this appointment.
     *
     * @return the UUID of the appointment.
     */
	public UUID getUUID() {
		return this.uuid;
	}
}
