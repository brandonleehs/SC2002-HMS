package hms.entity.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.appointment.Schedule;
import hms.entity.attributes.Gender;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;

public class Doctor extends User {
	private final int age;
	private final Schedule schedule;

	// TODO: add custom working hours after testing
	public Doctor(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
		this.schedule = new Schedule();
	}

	public void viewSchedule() {
	}

	// TODO: input validation for date/timings
	public void setAvailability(LocalDate date, LocalTime startTime, LocalTime endTime) {
		this.schedule.setAvailability(date, startTime, endTime);
	}

	public void acceptAppointment(Appointment appointment) {
		appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
	}

	public void declineAppointment(Appointment appointment) {
		appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
	}

	public void completeAppointment(Patient patient, Appointment appointment, String serviceType,
			String consultationNotes) {
		appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
		AppointmentOutcomeRecord appointmentOutcomeRecord = new AppointmentOutcomeRecord(appointment.getDate(),
				serviceType, consultationNotes);
		appointment.setAppointmentOutcomeRecord(appointmentOutcomeRecord);
		// Remove appointment from schedule
		cancelAppointment(appointment);
		// Add to patient's outcome record
		patient.addAppointmentOutcomeRecord(appointmentOutcomeRecord);
	}

	public boolean scheduleAppointment(Appointment appointment) {
		return this.schedule.addAppointment(appointment);
	}

	public boolean changeAppointment(Appointment appointment, LocalDateTime datetime) {
		return this.schedule.changeAppointment(appointment, datetime);
	}

	public void cancelAppointment(Appointment appointment) {
		this.schedule.cancelAppointment(appointment);
	}

	public void prescribeMedicine(Medicine medicine, AppointmentOutcomeRecord appointmentOutcomeRecord) {
		appointmentOutcomeRecord.addPrescribedMedicine(medicine);
	}

	public int getAge() {
		return this.age;
	}

	public Schedule getSchedule() {
		return this.schedule;
	}
}
