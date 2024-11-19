package hms.entity.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents a schedule for managing appointments with availability and timeslot tracking.
 * Timeslots are 30-minute intervals, and the schedule supports custom and recurring availability.
 */
public class Schedule {
	// Maps an array of (scheduled) appointments to a date
	// Appointment array has a length of 48, each slot representing a timeslot of 30
	// minutes in a day
	private final Map<LocalDate, Appointment[]> scheduleMap;
	// Represents a range of regular working hours - aka availability that is
	// repeated
	private final List<LocalTime[]> workingHours;
	// Maps custom availability for a day to a date
	// Each slot represents a timeslot, and the boolean value represents if the
	// timeslot is available
	private final Map<LocalDate, boolean[]> availableMap;

	/**
     * Constructs a schedule with a single range of working hours.
     * Appointment array has a length of 48, each slot representing a timeslot of 30 minutes in a day
     * @param startTime the start time of the working hours.
     * @param endTime the end time of the working hours.
     */
	public Schedule(LocalTime startTime, LocalTime endTime) {
		this.scheduleMap = new HashMap<LocalDate, Appointment[]>();
		this.workingHours = new ArrayList<LocalTime[]>();
		this.workingHours.add(new LocalTime[] { startTime, endTime });
		this.availableMap = new HashMap<LocalDate, boolean[]>();
	}

	/**
     * Constructs a schedule with two ranges of working hours.
     *
     * @param startTime1 the start time of the first range of working hours.
     * @param endTime1 the end time of the first range of working hours.
     * @param startTime2 the start time of the second range of working hours.
     * @param endTime2 the end time of the second range of working hours.
     */
	public Schedule(LocalTime startTime1, LocalTime endTime1, LocalTime startTime2, LocalTime endTime2) {
		this(startTime1, endTime1);
		this.workingHours.add(new LocalTime[] { startTime2, endTime2 });
	}

	/**
     * Constructs a schedule with default working hours:
     * 08:00-12:00 and 13:00-17:00.
     */
	public Schedule() {
		this(LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(13, 0), LocalTime.of(17, 0));
	}

	/**
     * Adds an appointment to the schedule if it does not clash with existing appointments
     * and the timeslot is available.
     *
     * @param appointment the appointment to add.
     * @return {@code true} if the appointment was successfully added; {@code false} otherwise.
     */
	public boolean addAppointment(Appointment appointment) {
		if (!isClashing(appointment) && isAvailable(appointment)) {
			updateSchedule(appointment);
			return true;
		}
		return false;
	}

	/**
     * Changes the time and date of an existing appointment.
     *
     * @param appointment the appointment to change.
     * @param datetime the new date and time for the appointment.
     * @return {@code true} if the change was successful; {@code false} otherwise.
     */
	public boolean changeAppointment(Appointment appointment, LocalDateTime datetime) {
		if (!isClashing(datetime) && isAvailable(datetime)) {
			cancelAppointment(appointment);
			appointment.setDate(datetime.toLocalDate());
			appointment.setTime(datetime.toLocalTime());
			addAppointment(appointment);
			return true;
		}
		return false;
	}

	/**
     * Cancels an existing appointment in the schedule.
     *
     * @param appointment the appointment to cancel.
     */
	public void cancelAppointment(Appointment appointment) {
		Appointment[] timeslots = this.scheduleMap.get(appointment.getDate());
		if (timeslots == null) {
			return;
		}
		timeslots[getTimeslot(appointment.getTime())] = null;
	}

	/**
     * Sets availability for a specific date and time range.
     *
     * @param date the date for which availability is being set.
     * @param startTime the start time of the available range.
     * @param endTime the end time of the available range.
     */
	public void setAvailability(LocalDate date, LocalTime startTime, LocalTime endTime) {
		int startTimeslot = getTimeslot(startTime);
		int endTimeslot = getTimeslot(endTime);
		boolean[] availableArr = new boolean[48];
		for (int i = startTimeslot; i < endTimeslot; i++) {
			availableArr[i] = true;
		}
		this.availableMap.put(date, availableArr);
	}

	/**
     * Marks specific time slots as unavailable for a specific date and time range.
     *
     * @param date the date for which timeslots are being marked unavailable.
     * @param startTime the start time of the unavailable range.
     * @param endTime the end time of the unavailable range.
     */
	public void setUnavailable(LocalDate date, LocalTime startTime, LocalTime endTime) {
		int startTimeslot = getTimeslot(startTime);
		int endTimeslot = getTimeslot(endTime);
		boolean[] availableArr = new boolean[48];
		for (int i = startTimeslot; i < endTimeslot; i++) {
			availableArr[i] = false;
		}
		this.availableMap.put(date, availableArr);
	}

	/**
     * Checks if the specified appointment is available in the schedule.
     *
     * @param appointment the appointment to check.
     * @return {@code true} if the timeslot is available; {@code false} otherwise.
     */
	public boolean isAvailable(Appointment appointment) {
		return isAvailable(LocalDateTime.of(appointment.getDate(), appointment.getTime()));
	}

	/**
     * Checks if a specific date and time is available in the schedule.
     *
     * @param datetime the date and time to check.
     * @return {@code true} if the timeslot is available; {@code false} otherwise.
     */
	public boolean isAvailable(LocalDateTime datetime) {
		LocalDate date = datetime.toLocalDate();
		LocalTime time = datetime.toLocalTime();
		int timeslot = getTimeslot(time);
		// Check if timeslot is available
		// If availability is explicitly set for that day, use it
		boolean[] availableArr = this.availableMap.get(date);
		if (availableArr != null) {
			if (availableArr[timeslot]) {
				return true;
			}
			return false;
		}
		// Otherwise, use default working hours to check availability
		for (LocalTime[] workingHourRange : this.workingHours) {
			if ((time.isAfter(workingHourRange[0]) || time.equals(workingHourRange[0]))
					&& time.isBefore(workingHourRange[1])) {
				return true;
			}
		}
		return false;
	}

	/**
     * Checks if the specified appointment clashes with existing appointments in the schedule.
     *
     * @param appointment the appointment to check.
     * @return {@code true} if the appointment clashes; {@code false} otherwise.
     */
	public boolean isClashing(Appointment appointment) {
		return isClashing(LocalDateTime.of(appointment.getDate(), appointment.getTime()));
	}

	/**
     * Checks if a specific date and time clashes with existing appointments in the schedule.
     *
     * @param datetime the date and time to check.
     * @return {@code true} if the datetime clashes; {@code false} otherwise.
     */
	public boolean isClashing(LocalDateTime datetime) {
		Appointment[] timeslots = this.scheduleMap.get(datetime.toLocalDate());

		return timeslots != null && timeslots[getTimeslot(datetime.toLocalTime())] != null;
	}

	private void updateSchedule(Appointment appointment) {
		int timeslot = getTimeslot(appointment.getTime());
		Appointment[] timeslots = this.scheduleMap.get(appointment.getDate());

		if (timeslots == null) {
			Appointment[] appointmentArr = new Appointment[48];
			appointmentArr[timeslot] = appointment;
			this.scheduleMap.put(appointment.getDate(), appointmentArr);
		} else {
			timeslots[timeslot] = appointment;
		}
	}

	/**
     * Converts a time to its corresponding 30-minute timeslot index.
     *
     * @param time the time to convert.
     * @return the index of the timeslot.
     */
	public static int getTimeslot(LocalTime time) {
		return time.getHour() * 2 + time.getMinute() / 30;
	}

	/**
     * Retrieves the schedule map, which maps dates to arrays of scheduled appointments.
     *
     * @return the schedule map.
     */
	public Map<LocalDate, Appointment[]> getScheduleMap() {
		return scheduleMap;
	}

	/**
     * Retrieves the availability map, which maps dates to boolean arrays representing availability.
     *
     * @return the availability map.
     */
	public Map<LocalDate, boolean[]> getAvailableMap() {
		return availableMap;
	}
}
