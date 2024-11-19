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


/**
 * Represents a Doctor, a type of User with the ability to manage appointments,
 * set availability, prescribe medicine, and handle appointment outcomes.
 */
public class Doctor extends User {
	private final int age;
	private final Schedule schedule;
	private final List<Appointment> pendingAppointmentList;
	private final List<Appointment> confirmedAppointmentList;


	/**
     * Constructs a Doctor instance with the specified details.
     *
     * @param id the unique identifier of the doctor.
     * @param password the password associated with the doctor's account.
     * @param name the name of the doctor.
     * @param gender the gender of the doctor.
     * @param age the age of the doctor.
     */
	public Doctor(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
		this.schedule = new Schedule();
		this.pendingAppointmentList = new ArrayList<Appointment>();
		this.confirmedAppointmentList = new ArrayList<Appointment>();
	}

	/**
     * Retrieves a list of available times for a specific date.
     * Availability is determined based on the doctor's schedule.
     *
     * @param date the date for which the availability is to be checked.
     * @return a list of available times for the specified date.
     */
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

	/**
     * Sets the doctor's availability for a specific date and time range.
     *
     * @param date the date for which the availability is to be set.
     * @param startTime the start time of the availability.
     * @param endTime the end time of the availability.
     */
	public void setAvailability(LocalDate date, LocalTime startTime, LocalTime endTime) {
		this.schedule.setAvailability(date, startTime, endTime);
	}

	/**
     * Accepts an appointment, changing its status to confirmed and adding it to
     * the confirmed appointments list.
     *
     * @param appointment the appointment to be accepted.
     */
	public void acceptAppointment(Appointment appointment) {
		appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);

		this.confirmedAppointmentList.add(appointment);
		removeAppointmentFromPendingList(appointment);
	}

	/**
     * Cancels an appointment, changing its status to cancelled and removing it
     * from the schedule and the appointment lists.
     *
     * @param appointment the appointment to be cancelled.
     */
	public void cancelAppointment(Appointment appointment) {
		this.schedule.cancelAppointment(appointment);
		appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);

		removeAppointmentFromPendingList(appointment);
		removeAppointmentFromConfirmedList(appointment);
	}

	/**
     * Completes an appointment, records the outcome, and assigns prescribed medicines
     * to the appointment's outcome record. The appointment is also removed from the
     * schedule and the patient’s appointment list.
     *
     * @param patient the patient associated with the appointment.
     * @param appointment the appointment to be completed.
     * @param serviceType the type of service provided during the appointment.
     * @param consultationNotes the notes recorded during the consultation.
     * @param medicineList a map of medicines prescribed during the appointment.
     */
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

	/**
     * Schedules an appointment for the doctor, adding it to the pending appointment list
     * if the schedule allows it.
     *
     * @param appointment the appointment to be scheduled.
     * @return true if the appointment was successfully scheduled, false otherwise.
     */
	public boolean scheduleAppointment(Appointment appointment) {
		if (this.schedule.addAppointment(appointment)) {
			this.pendingAppointmentList.add(appointment);
			return true;
		}
		return false;
	}

	/**
     * Prescribes medicine during an appointment and adds it to the appointment's outcome record.
     *
     * @param medicineList a map of medicines to be prescribed.
     * @param appointmentOutcomeRecord the outcome record where the medicines will be added.
     */
	public void prescribeMedicine(HashMap<Medicine, Integer> medicineList,
			AppointmentOutcomeRecord appointmentOutcomeRecord) {
		appointmentOutcomeRecord.addPrescribedMedicine(medicineList);
	}

	/**
     * Removes a medicine from the prescribed list in the appointment's outcome record.
     *
     * @param medicine the medicine to be removed.
     * @param appointmentOutcomeRecord the outcome record from which the medicine will be removed.
     */
	public void removeMedicine(Medicine medicine, AppointmentOutcomeRecord appointmentOutcomeRecord) {
		appointmentOutcomeRecord.removePrescribedMedicine(medicine);
	}

	/**
     * Retrieves the doctor's age.
     *
     * @return the age of the doctor.
     */
	public int getAge() {
		return this.age;
	}

	/**
     * Retrieves the doctor's schedule.
     *
     * @return the schedule of the doctor.
     */
	public Schedule getSchedule() {
		return this.schedule;
	}

	/**
     * Retrieves the list of pending appointments for the doctor.
     *
     * @return a list of pending appointments.
     */
	public List<Appointment> getPendingAppointmentList() {
		return this.pendingAppointmentList;
	}

	/**
     * Retrieves the list of confirmed appointments for the doctor.
     *
     * @return a list of confirmed appointments.
     */
	public List<Appointment> getConfirmedAppointmentList() {
		return this.confirmedAppointmentList;
	}

	/**
     * Removes an appointment from the pending appointments list.
     *
     * @param appointment the appointment to be removed.
     */
	private void removeAppointmentFromPendingList(Appointment appointment) {
		if (this.pendingAppointmentList.contains(appointment)) {
			this.pendingAppointmentList.remove(appointment);
		}
	}

	/**
     * Removes an appointment from the confirmed appointments list.
     *
     * @param appointment the appointment to be removed.
     */
	private void removeAppointmentFromConfirmedList(Appointment appointment) {
		if (this.confirmedAppointmentList.contains(appointment)) {
			this.confirmedAppointmentList.remove(appointment);
		}
	}
}
