package hms.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.entity.user.attributes.Gender;
import hms.repository.DoctorRepository;
import hms.repository.PatientRepository;

class DoctorTest {
	private static final PatientRepository patientRepository = new PatientRepository();

	@Test
	void testScheduleAppointmentIfRightTime() {
		Patient alice = patientRepository.getMap().get("P1001");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		Appointment appointment = new Appointment(alice.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		alice.scheduleAppointment(doctor, appointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[26] == appointment);
	}

	@Test
	void testScheduleAppointmentIfWrongTime() {
		Patient alice = patientRepository.getMap().get("P1001");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		Appointment appointment = new Appointment(alice.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(12, 0));
		assertFalse(alice.scheduleAppointment(doctor, appointment));
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments == null);
	}

	@Test
	void testRescheduleAppointmentIfRightTime() {
		Patient bob = patientRepository.getMap().get("P1002");
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
		Patient bob = patientRepository.getMap().get("P1002");
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
	void testRescheduleAppointmentIfDifferentDoctorRightTime() {
		Patient bob = patientRepository.getMap().get("P1002");
		Doctor oldDoctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Doctor newDoctor = new Doctor("D003", "password", "John Doe", Gender.MALE, 35);
		Appointment oldAppointment = new Appointment(bob.getId(), oldDoctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		Appointment newAppointment = new Appointment(bob.getId(), newDoctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 30));
		assertTrue(bob.scheduleAppointment(oldDoctor, oldAppointment));
		assertTrue(bob.rescheduleAppointment(oldDoctor, newDoctor, oldAppointment, newAppointment));
		Appointment[] appointments1 = oldDoctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		Appointment[] appointments2 = newDoctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments1[26] == null);
		assertTrue(appointments2[27] == newAppointment);
	}

	@Test
	void testRescheduleAppointmentIfDifferentDoctorWrongTime() {
		Patient bob = patientRepository.getMap().get("P1002");
		Doctor oldDoctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Doctor newDoctor = new Doctor("D003", "password", "John Doe", Gender.MALE, 35);
		Appointment oldAppointment = new Appointment(bob.getId(), oldDoctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		Appointment newAppointment = new Appointment(bob.getId(), newDoctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(12, 30));
		assertTrue(bob.scheduleAppointment(oldDoctor, oldAppointment));
		assertFalse(bob.rescheduleAppointment(oldDoctor, newDoctor, oldAppointment, newAppointment));
		Appointment[] appointments1 = oldDoctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		Appointment[] appointments2 = newDoctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments1[26] == oldAppointment);
		assertTrue(appointments2 == null);
	}

	@Test
	void testCancelSchedule() {
		Patient bob = patientRepository.getMap().get("P1002");
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
		Patient bob = patientRepository.getMap().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		bob.scheduleAppointment(doctor, appointment);
		doctor.acceptAppointment(appointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[26].getAppointmentStatus() == AppointmentStatus.CONFIRMED);
	}

	@Test
	void testCancelAppointment() {
		Patient bob = patientRepository.getMap().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		bob.scheduleAppointment(doctor, appointment);
		doctor.cancelAppointment(appointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointment.getAppointmentStatus() == AppointmentStatus.CANCELLED);
		assertTrue(appointments[26] == null);
	}

	@Test
	void testSetAvailabilityIfWrongTime() {
		Patient bob = patientRepository.getMap().get("P1002");
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
		Patient bob = patientRepository.getMap().get("P1002");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		doctor.setAvailability(LocalDate.of(2024, 10, 29), LocalTime.of(3, 0), LocalTime.of(8, 1, 23));
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(7, 30));
		assertTrue(bob.scheduleAppointment(doctor, appointment));
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[15] == appointment);
	}

	@Test
	void testGetAvailability() {
		DoctorRepository doctorRepository = new DoctorRepository();
		PatientRepository patientRepository = new PatientRepository();
		Patient patient = patientRepository.getById("P1001");
		Doctor doctor = doctorRepository.getById("D001");
		LocalDate date = LocalDate.of(2024, 11, 5);
		Appointment appt = new Appointment(patient.getId(), doctor.getId(), date, LocalTime.of(9, 30));
		patient.scheduleAppointment(doctor, appt);
		List<LocalTime> availability = doctor.getAvailability(date);
		assertFalse(availability.contains(LocalTime.of(9, 30)));

	}
}
