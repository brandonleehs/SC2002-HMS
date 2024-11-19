package hms.entity.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.appointment.Schedule;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.attributes.Gender;

public class Doctor extends User {
	private final int age;
	private final Schedule schedule;
	private final List<Appointment> pendingAppointmentList;
	private final List<Appointment> confirmedAppointmentList;

	public Doctor(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
		this.schedule = new Schedule();
		this.pendingAppointmentList = new ArrayList<Appointment>();
		this.confirmedAppointmentList = new ArrayList<Appointment>();
	}

	// Returns a list of available times
	public List<LocalTime> getAvailability(LocalDate date) {
		List<LocalTime> availability = new ArrayList<LocalTime>();
		for (int hour = 0; hour < 24; hour++) {
			LocalTime time1 = LocalTime.of(hour, 0);
			LocalTime time2 = LocalTime.of(hour, 30);
			if (!this.schedule.isClashing(LocalDateTime.of(date, time1))
					&& this.schedule.isAvailable(LocalDateTime.of(date, time1))) {
				availability.add(time1);
			}
			if (!this.schedule.isClashing(LocalDateTime.of(date, time2))
					&& this.schedule.isAvailable(LocalDateTime.of(date, time2))) {
				availability.add(time2);
			}
		}
		return availability;
	}

	public void setAvailability(LocalDate date, LocalTime startTime, LocalTime endTime) {
		this.schedule.setAvailability(date, startTime, endTime);
	}

	public void acceptAppointment(Appointment appointment) {
		appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);

		this.confirmedAppointmentList.add(appointment);
		removeAppointmentFromPendingList(appointment);
	}

	public void cancelAppointment(Appointment appointment) {
		this.schedule.cancelAppointment(appointment);
		appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);

		removeAppointmentFromPendingList(appointment);
		removeAppointmentFromConfirmedList(appointment);
	}

	public void completeAppointment(Patient patient, Appointment appointment, String serviceType,
			String consultationNotes, Map<Medicine, Integer> medicineList) {

		AppointmentOutcomeRecord appointmentOutcomeRecord = new AppointmentOutcomeRecord(appointment.getDate(),
				serviceType, consultationNotes, appointment.getUUID());

		appointmentOutcomeRecord.addPrescribedMedicine(medicineList);

		appointment.setAppointmentOutcomeRecord(appointmentOutcomeRecord);
		// Remove appointment from schedule
		cancelAppointment(appointment);
		// Remember to set status back to completed!
		appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
		// Add to patient's outcome record
		patient.addAppointmentOutcomeRecord(appointmentOutcomeRecord);
		patient.getScheduledAppointmentList().remove(appointment);

		removeAppointmentFromConfirmedList(appointment);
		removeAppointmentFromPendingList(appointment);
	}

	public boolean scheduleAppointment(Appointment appointment) {
		if (this.schedule.addAppointment(appointment)) {
			this.pendingAppointmentList.add(appointment);
			return true;
		}
		return false;
	}

	public void prescribeMedicine(HashMap<Medicine, Integer> medicineList,
			AppointmentOutcomeRecord appointmentOutcomeRecord) {
		appointmentOutcomeRecord.addPrescribedMedicine(medicineList);
	}

	public void removeMedicine(Medicine medicine, AppointmentOutcomeRecord appointmentOutcomeRecord) {
		appointmentOutcomeRecord.removePrescribedMedicine(medicine);
	}

	public int getAge() {
		return this.age;
	}

	public Schedule getSchedule() {
		return this.schedule;
	}

	public List<Appointment> getPendingAppointmentList() {
		return this.pendingAppointmentList;
	}

	public List<Appointment> getConfirmedAppointmentList() {
		return this.confirmedAppointmentList;
	}

	private void removeAppointmentFromPendingList(Appointment appointment) {
		if (this.pendingAppointmentList.contains(appointment)) {
			this.pendingAppointmentList.remove(appointment);
		}
	}

	private void removeAppointmentFromConfirmedList(Appointment appointment) {
		if (this.confirmedAppointmentList.contains(appointment)) {
			this.confirmedAppointmentList.remove(appointment);
		}
	}
}
