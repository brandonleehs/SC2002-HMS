package hms;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import hms.entity.appointment.Appointment;
import hms.entity.appointment.Schedule;

class ScheduleTest {

	@Test
	void testGetTimeslot1() {
		assertTrue(Schedule.getTimeslot(LocalTime.of(3, 42, 34)) == 7);
	}

	@Test
	void testGetTimeslot2() {
		assertTrue(Schedule.getTimeslot(LocalTime.of(4, 0, 0)) == 8);
	}

	@Test
	void testGetTimeslot3() {
		assertTrue(Schedule.getTimeslot(LocalTime.of(5, 30, 0)) == 11);
	}

	@Test
	void testGetTimeslot4() {
		assertTrue(Schedule.getTimeslot(LocalTime.of(5, 29, 29)) == 10);
	}

	@Test
	void testGetTimeslot5() {
		assertTrue(Schedule.getTimeslot(LocalTime.of(23, 0, 0)) == 46);
	}

	@Test
	void testGetTimeslot6() {
		assertTrue(Schedule.getTimeslot(LocalTime.of(23, 59, 59)) == 47);
	}

	@Test
	void testAddAppointmentIfValidTime1() {
		Schedule schedule = new Schedule();
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(9, 0));

		assertTrue(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfValidTime2() {
		Schedule schedule = new Schedule();
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(9, 34));

		assertTrue(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfValidTime3() {
		Schedule schedule = new Schedule();
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(13, 00));

		assertTrue(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfInvalidTime1() {
		Schedule schedule = new Schedule();
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(3, 23));

		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfInvalidTime2() {
		Schedule schedule = new Schedule();
		// Test for boundary value, invalid time period from 1200-1300
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(12, 0));

		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfInvalidTime3() {
		Schedule schedule = new Schedule();
		// Test for boundary value, invalid time period from 1200-1300
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(12, 59));

		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfInvalidTime4() {
		Schedule schedule = new Schedule();
		// Test for boundary value, invalid time period after 1700
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(17, 0));

		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfSetWorkingHours1() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(17, 0));
		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfSetWorkingHours2() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		// Test for boundary value, invalid time period after 124356
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(12, 43, 56));
		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfSetWorkingHours3() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		// Test for boundary value, valid time period after 052301
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(5, 23, 1));
		assertTrue(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfSetWorkingHours4() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56), LocalTime.of(12, 44, 56),
				LocalTime.of(23, 59, 59));
		// Test for boundary value, invalid time of 124356
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(12, 43, 56));
		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfSetWorkingHours5() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56), LocalTime.of(12, 43, 56),
				LocalTime.of(23, 59, 59));
		// Test for boundary value, valid time of 124356
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(12, 43, 56));
		assertTrue(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfSetWorkingHours6() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56), LocalTime.of(12, 43, 56),
				LocalTime.of(23, 59, 59));
		// Test for boundary value, valid time of 124356
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(0, 0, 0));
		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testAddAppointmentIfSetWorkingHours7() {
		Schedule schedule = new Schedule(LocalTime.of(5, 31, 1), LocalTime.of(12, 43, 56), LocalTime.of(12, 29, 56),
				LocalTime.of(23, 59, 59));
		// Test for boundary value, invalid time of 053000, valid time of 12300
		Appointment appointment1 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(5, 30, 0));
		Appointment appointment2 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(12, 30, 0));

		assertFalse(schedule.addAppointment(appointment1));
		assertTrue(schedule.addAppointment(appointment2));
	}

	@Test
	void testAddAppointmentIfClash() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		// Test for boundary value, valid time period after 052301
		Appointment appointment1 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(5, 30, 1));

		// Test for appointment clash, invalid time period for appointment 2 since it is
		// within 30 min of appointment 1
		Appointment appointment2 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(5, 30, 1));
		assertTrue(schedule.addAppointment(appointment1));
		assertFalse(schedule.addAppointment(appointment2));
	}

	@Test
	void testAddAppointmentIfNoClash() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		// Test for boundary value, valid time period after 052301
		Appointment appointment1 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(5, 30, 1));

		// Test for appointment clash, invalid time period for appointment 2 since it
		// clashes with appointment 1
		Appointment appointment2 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(6, 0, 1));
		assertTrue(schedule.addAppointment(appointment1));
		assertTrue(schedule.addAppointment(appointment2));
	}

	@Test
	void testAddAppointmentIfNoClash2() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		// Test for boundary value, valid time period after 052301
		Appointment appointment1 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(5, 30, 1));

		// Test for appointment clash, invalid time period for appointment 2 since it
		// clashes with appointment 1
		Appointment appointment2 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(6, 0, 1));
		Appointment appointment3 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 26), LocalTime.of(6, 0, 1));
		Appointment appointment4 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 26), LocalTime.of(7, 0, 1));
		Appointment appointment5 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 27), LocalTime.of(7, 0, 1));
		Appointment appointment6 = new Appointment("1234", "5678", LocalDate.of(2024, 10, 26), LocalTime.of(6, 30, 1));

		assertTrue(schedule.addAppointment(appointment1));
		assertTrue(schedule.addAppointment(appointment2));
		assertTrue(schedule.addAppointment(appointment3));
		assertTrue(schedule.addAppointment(appointment4));
		assertTrue(schedule.addAppointment(appointment5));
		assertTrue(schedule.addAppointment(appointment6));
	}

	@Test
	void testSetAvailability1() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		schedule.setAvailability(LocalDate.of(2024, 10, 25), LocalTime.of(2, 0), LocalTime.of(2, 30));
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(2, 0, 1));
		assertTrue(schedule.addAppointment(appointment));
	}

	@Test
	void testSetAvailability2() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		schedule.setAvailability(LocalDate.of(2024, 10, 25), LocalTime.of(2, 0), LocalTime.of(2, 30));
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(10, 0, 1));
		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testSetAvailability3() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		schedule.setUnavailable(LocalDate.of(2024, 10, 25), LocalTime.of(0, 0), LocalTime.of(23, 59));
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(10, 0, 1));
		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testSetAvailability4() {
		Schedule schedule = new Schedule(LocalTime.of(5, 23, 1), LocalTime.of(12, 43, 56));
		schedule.setUnavailable(LocalDate.of(2024, 10, 25), LocalTime.of(0, 0), LocalTime.of(23, 59));
		Appointment appointment = new Appointment("1234", "5678", LocalDate.of(2024, 10, 25), LocalTime.of(23, 30, 0));
		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testCancelAppointment1() {
		Schedule schedule = new Schedule();
		LocalDate date = LocalDate.of(2024, 10, 25);
		LocalTime time = LocalTime.of(10, 0, 1);
		Appointment appointment = new Appointment("1234", "5678", date, time);
		assertTrue(schedule.addAppointment(appointment));
		schedule.cancelAppointment(appointment);
		assertTrue(schedule.getScheduleMap().get(date)[Schedule.getTimeslot(time)] == null);
	}

	@Test
	void testCancelAppointment2() {
		Schedule schedule = new Schedule();
		LocalDate date = LocalDate.of(2024, 10, 25);
		LocalTime time = LocalTime.of(13, 0, 1);
		Appointment appointment = new Appointment("1234", "5678", date, time);
		assertTrue(schedule.addAppointment(appointment));
		schedule.cancelAppointment(appointment);
		assertTrue(schedule.getScheduleMap().get(date)[Schedule.getTimeslot(time)] == null);
		assertTrue(schedule.addAppointment(appointment));
		assertFalse(schedule.addAppointment(appointment));
	}

	@Test
	void testChangeAppointment1() {
		Schedule schedule = new Schedule();
		LocalDate date = LocalDate.of(2024, 10, 25);
		LocalTime time = LocalTime.of(13, 0, 1);
		Appointment appointment = new Appointment("1234", "5678", date, time);
		assertTrue(schedule.addAppointment(appointment));
		assertTrue(schedule.changeAppointment(appointment, LocalDateTime.of(date, LocalTime.of(14, 0, 1))));
		assertTrue(schedule.getScheduleMap().get(date)[Schedule.getTimeslot(time)] == null);
		assertTrue(schedule.getScheduleMap().get(date)[Schedule.getTimeslot(LocalTime.of(14, 0, 1))] != null);
	}

	@Test
	void testChangeAppointment2() {
		Schedule schedule = new Schedule();
		LocalDate date = LocalDate.of(2024, 10, 25);
		LocalTime time = LocalTime.of(13, 0, 1);
		Appointment appointment1 = new Appointment("1234", "5678", date, time);
		Appointment appointment2 = new Appointment("1234", "5678", date, LocalTime.of(13, 0, 0));
		assertTrue(schedule.addAppointment(appointment1));
		assertTrue(schedule.changeAppointment(appointment1, LocalDateTime.of(date, LocalTime.of(14, 0, 1))));
		assertTrue(schedule.addAppointment(appointment2));
		assertTrue(schedule.getScheduleMap().get(date)[Schedule.getTimeslot(LocalTime.of(14, 0, 1))] != null);
	}

	@Test
	void testChangeAppointment3() {
		Schedule schedule = new Schedule();
		LocalDate date = LocalDate.of(2024, 10, 25);
		LocalTime time = LocalTime.of(13, 0, 0);
		Appointment appointment1 = new Appointment("1234", "5678", date, time);
		Appointment appointment2 = new Appointment("1234", "5678", date, LocalTime.of(13, 0, 0));
		assertTrue(schedule.addAppointment(appointment1));
		assertFalse(schedule.changeAppointment(appointment1, LocalDateTime.of(date, LocalTime.of(12, 0, 0))));
		assertFalse(schedule.addAppointment(appointment2));
	}

}
