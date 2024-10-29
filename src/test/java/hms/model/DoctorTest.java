package hms.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import hms.PatientController;
import hms.appointment.Appointment;
import hms.appointment.AppointmentStatus;
import hms.attributes.Gender;

class DoctorTest {
	private static PatientController patientController = new PatientController("/Patient_List.xlsx");

	@Test
	void testScheduleAppointmentIfRightTime() {
		Patient alice = patientController.getPatientTable().get("P1001");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		Appointment appointment = new Appointment(alice.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		alice.scheduleAppointment(doctor, appointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[26] == appointment);
	}

	@Test
	void testScheduleAppointmentIfWrongTime() {
		Patient alice = patientController.getPatientTable().get("P1001");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		Appointment appointment = new Appointment(alice.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(12, 0));
		assertFalse(alice.scheduleAppointment(doctor, appointment));
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments == null);
	}

	@Test
	void testRescheduleAppointmentIfRightTime() {
		Patient bob = patientController.getPatientTable().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment oldAppointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		Appointment newAppointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(14, 0));
		bob.scheduleAppointment(doctor, oldAppointment);
		bob.rescheduleAppointment(doctor, doctor, oldAppointment, newAppointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[26] == null);
		assertTrue(appointments[28] == newAppointment);
	}

	@Test
	void testRescheduleAppointmentIfWrongTime() {
		Patient bob = patientController.getPatientTable().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment oldAppointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		Appointment newAppointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(12, 30));
		bob.scheduleAppointment(doctor, oldAppointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertFalse(bob.rescheduleAppointment(doctor, doctor, oldAppointment, newAppointment));
		assertTrue(appointments[26] == oldAppointment);
		assertTrue(appointments[28] == null);
	}

	@Test
	void testCancelSchedule() {
		Patient bob = patientController.getPatientTable().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		bob.scheduleAppointment(doctor, appointment);
		bob.cancelAppointment(doctor, appointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[26] == null);
	}

	@Test
	void testAcceptAppointment() {
		Patient bob = patientController.getPatientTable().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		bob.scheduleAppointment(doctor, appointment);
		doctor.acceptAppointment(appointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[26].getAppointmentStatus() == AppointmentStatus.CONFIRMED);
	}

	@Test
	void testDeclineAppointment() {
		Patient bob = patientController.getPatientTable().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		bob.scheduleAppointment(doctor, appointment);
		doctor.declineAppointment(appointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[26].getAppointmentStatus() == AppointmentStatus.CANCELLED);
	}

	@Test
	void testSetAvailabilityIfWrongTime() {
		Patient bob = patientController.getPatientTable().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		doctor.setAvailability(LocalDate.of(2024, 10, 29), LocalTime.of(3, 0), LocalTime.of(8, 1, 23));
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(8, 0));
		assertFalse(bob.scheduleAppointment(doctor, appointment));
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments == null);
	}

	@Test
	void testSetAvailabilityIfRightTime() {
		Patient bob = patientController.getPatientTable().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		doctor.setAvailability(LocalDate.of(2024, 10, 29), LocalTime.of(3, 0), LocalTime.of(8, 1, 23));
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(7, 30));
		assertTrue(bob.scheduleAppointment(doctor, appointment));
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[15] == appointment);
	}
}
