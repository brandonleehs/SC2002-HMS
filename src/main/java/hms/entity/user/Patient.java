package hms.entity.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hms.Validation;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.record.MedicalRecord;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

public class Patient extends User {
	private final MedicalRecord medicalRecord;
	private final List<Appointment> scheduledAppointmentList;
	private final List<Appointment> allAppointmentList;

	public Patient(MedicalRecord medicalRecord, String password) {
		super(medicalRecord.getId(), password, medicalRecord.getName(), medicalRecord.getGender());
		this.medicalRecord = medicalRecord;
		this.scheduledAppointmentList = new ArrayList<Appointment>();
		this.allAppointmentList = new ArrayList<Appointment>();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.medicalRecord.setPhoneNumber(phoneNumber);
	}

	public boolean setEmailAddress(String emailAddress) {
		if (Validation.validateEmailAddress(emailAddress)) {
			this.medicalRecord.setEmailAddress(emailAddress);
			return true;
		}
		return false;
	}

	public String getId() {
		return this.medicalRecord.getId();
	}

	public String getName() {
		return this.medicalRecord.getName();
	}

	public LocalDate getDateOfBirth() {
		return this.medicalRecord.getDateOfBirth();
	}

	public Gender getGender() {
		return this.medicalRecord.getGender();
	}

	public BloodType getBloodType() {
		return this.medicalRecord.getBloodType();
	}

	public String getPhoneNumber() {
		return this.medicalRecord.getPhoneNumber();
	}

	public String getEmailAddress() {
		return this.medicalRecord.getEmailAddress();
	}

	public List<AppointmentOutcomeRecord> getAppointmentOutcomeRecordList() {
		return this.medicalRecord.getAppointmentOutcomeRecordList();
	}

	public boolean scheduleAppointment(Doctor doctor, Appointment appointment) {
		if (doctor.scheduleAppointment(appointment)) {
			this.scheduledAppointmentList.add(appointment);
			this.allAppointmentList.add(appointment);
			return true;
		}
		return false;
	}

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

	public void cancelAppointment(Doctor doctor, Appointment appointment) {
		this.scheduledAppointmentList.remove(appointment);
		appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
		doctor.cancelAppointment(appointment);
	}

	public void addAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		this.medicalRecord.addAppointmentOutcomeRecord(appointmentOutcomeRecord);
	}

	public List<Appointment> getScheduledAppointmentList() {
		return this.scheduledAppointmentList;
	}

	public List<Appointment> getAllAppointmentList() {
		return this.allAppointmentList;
	}
}