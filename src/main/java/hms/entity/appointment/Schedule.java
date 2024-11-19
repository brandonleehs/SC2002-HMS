package hms.entity.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Schedule(LocalTime startTime, LocalTime endTime) {
		this.scheduleMap = new HashMap<LocalDate, Appointment[]>();
		this.workingHours = new ArrayList<LocalTime[]>();
		this.workingHours.add(new LocalTime[] { startTime, endTime });
		this.availableMap = new HashMap<LocalDate, boolean[]>();
	}

	public Schedule(LocalTime startTime1, LocalTime endTime1, LocalTime startTime2, LocalTime endTime2) {
		this(startTime1, endTime1);
		this.workingHours.add(new LocalTime[] { startTime2, endTime2 });
	}

	// If no working hours are implemented, assume 0800-1200, 1300-1700 working
	// hours
	public Schedule() {
		this(LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(13, 0), LocalTime.of(17, 0));
	}

	// Timeslots are in intervals of 30 min
	// As a result, appointments should be scheduled every 30 min only with 0
	// secs
	public boolean addAppointment(Appointment appointment) {
		if (!isClashing(appointment) && isAvailable(appointment)) {
			updateSchedule(appointment);
			return true;
		}
		return false;
	}

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

	public void cancelAppointment(Appointment appointment) {
		Appointment[] timeslots = this.scheduleMap.get(appointment.getDate());
		if (timeslots == null) {
			return;
		}
		timeslots[getTimeslot(appointment.getTime())] = null;
	}

	public void setAvailability(LocalDate date, LocalTime startTime, LocalTime endTime) {
		int startTimeslot = getTimeslot(startTime);
		int endTimeslot = getTimeslot(endTime);
		boolean[] availableArr = new boolean[48];
		for (int i = startTimeslot; i < endTimeslot; i++) {
			availableArr[i] = true;
		}
		this.availableMap.put(date, availableArr);
	}

	public void setUnavailable(LocalDate date, LocalTime startTime, LocalTime endTime) {
		int startTimeslot = getTimeslot(startTime);
		int endTimeslot = getTimeslot(endTime);
		boolean[] availableArr = new boolean[48];
		for (int i = startTimeslot; i < endTimeslot; i++) {
			availableArr[i] = false;
		}
		this.availableMap.put(date, availableArr);
	}

	public boolean isAvailable(Appointment appointment) {
		return isAvailable(LocalDateTime.of(appointment.getDate(), appointment.getTime()));
	}

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

	public boolean isClashing(Appointment appointment) {
		return isClashing(LocalDateTime.of(appointment.getDate(), appointment.getTime()));
	}

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

	public static int getTimeslot(LocalTime time) {
		return time.getHour() * 2 + time.getMinute() / 30;
	}

	public Map<LocalDate, Appointment[]> getScheduleMap() {
		return scheduleMap;
	}

	public Map<LocalDate, boolean[]> getAvailableMap() {
		return availableMap;
	}
}
