package hms.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

import hms.record.AppointmentOutcomeRecord;

public class Appointment {
	private final String patientId;
	private String doctorId;
	private AppointmentStatus appointmentStatus;
	private LocalDate date;
	private LocalTime time;
	private AppointmentOutcomeRecord appointmentOutcomeRecord;

	public Appointment(String patientId, String doctorId, LocalDate date, LocalTime time) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.time = time;
		this.appointmentStatus = AppointmentStatus.PENDING;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public AppointmentStatus getAppointmentStatus() {
		return this.appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return this.time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public AppointmentOutcomeRecord getAppointmentOutcomeRecord() {
		return appointmentOutcomeRecord;
	}

	public void setAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		this.appointmentOutcomeRecord = appointmentOutcomeRecord;
	}

}
