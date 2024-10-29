package hms.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import hms.appointment.Appointment;
import hms.appointment.AppointmentStatus;
import hms.attributes.BloodType;
import hms.attributes.Gender;
import hms.record.MedicalRecord;

class PatientTest {
	@Test
	public void checkIfPasswordIsChanged() {
		MedicalRecord medicalRecord = new MedicalRecord("P1001", "Alice Brown", LocalDate.of(1980, 5, 1), Gender.FEMALE,
				BloodType.A_POS, "999", "alice.brown@example.com");
		Patient patient = new Patient(medicalRecord, "rockyou123");

		assertFalse(patient.getPasswordHash() == "rockyou123");
	}

	@Test
	public void checkHashPattern() {
		MedicalRecord medicalRecord = new MedicalRecord("P1001", "Alice Brown", LocalDate.of(1980, 5, 1), Gender.FEMALE,
				BloodType.A_POS, "999", "alice.brown@example.com");
		Patient patient = new Patient(medicalRecord, "admin1");

		String bcryptPattern = "\\$2[abxy]?\\$\\d{2}\\$[./A-Za-z0-9]{22}[./A-Za-z0-9]{31}";
		Pattern pattern = Pattern.compile(bcryptPattern);
		Matcher matcher = pattern.matcher(patient.getPasswordHash());

		assertTrue(matcher.matches());
	}

	@Test
	public void checkHashPatternAfterChange() {
		MedicalRecord medicalRecord = new MedicalRecord("P1001", "Alice Brown", LocalDate.of(1980, 5, 1), Gender.FEMALE,
				BloodType.A_POS, "999", "alice.brown@example.com");
		Patient patient = new Patient(medicalRecord, "admin1");
		patient.setPassword("qwerty");

		String bcryptPattern = "\\$2[abxy]?\\$\\d{2}\\$[./A-Za-z0-9]{22}[./A-Za-z0-9]{31}";
		Pattern pattern = Pattern.compile(bcryptPattern);
		Matcher matcher = pattern.matcher(patient.getPasswordHash());

		assertTrue(matcher.matches());
	}

	@Test
	void testScheduleAppointmentIfRightTime() {
		MedicalRecord medicalRecord = new MedicalRecord("P1001", "Alice Brown", LocalDate.of(1980, 5, 1), Gender.FEMALE,
				BloodType.A_POS, "999", "alice.brown@example.com");
		Patient alice = new Patient(medicalRecord, "password");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		Appointment appointment = new Appointment(alice.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		alice.scheduleAppointment(doctor, appointment);
		System.out.println(appointment.getAppointmentStatus());
		assertTrue(appointment.getAppointmentStatus() == AppointmentStatus.PENDING);
		doctor.completeAppointment(alice, appointment, "General", "Patient has a fever.");
		assertTrue(alice.getAppointmentOutcomeRecordList().get(0) == appointment.getAppointmentOutcomeRecord());
	}

	@Test
	void testScheduleAppointmentIfWrongTime() {
		MedicalRecord medicalRecord = new MedicalRecord("P1001", "Alice Brown", LocalDate.of(1980, 5, 1), Gender.FEMALE,
				BloodType.A_POS, "999", "alice.brown@example.com");
		Patient alice = new Patient(medicalRecord, "password");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		Appointment appointment = new Appointment(alice.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(12, 0));
		assertFalse(alice.scheduleAppointment(doctor, appointment));
		assertTrue(alice.getAppointmentOutcomeRecordList().isEmpty());
	}

	@Test
	void testRescheduleAppointmentIfRightTime() {
		MedicalRecord medicalRecord = new MedicalRecord("P1002", "Bob Stone", LocalDate.of(1975, 11, 2), Gender.MALE,
				BloodType.B_POS, "123", "bob.stone@example.com");
		Patient bob = new Patient(medicalRecord, "password");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment oldAppointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		Appointment newAppointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(14, 0));
		assertTrue(bob.scheduleAppointment(doctor, oldAppointment));
		assertTrue(bob.rescheduleAppointment(doctor, doctor, oldAppointment, newAppointment));
		doctor.completeAppointment(bob, newAppointment, "X-Ray", "Test results negative.");
		assertTrue(bob.getAppointmentOutcomeRecordList().get(0) == newAppointment.getAppointmentOutcomeRecord());
	}

	@Test
	void testRescheduleAppointmentIfWrongTime() {
		MedicalRecord medicalRecord = new MedicalRecord("P1002", "Bob Stone", LocalDate.of(1975, 11, 2), Gender.MALE,
				BloodType.B_POS, "123", "bob.stone@example.com");
		Patient bob = new Patient(medicalRecord, "password");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment oldAppointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		Appointment newAppointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(12, 30));
		assertTrue(bob.scheduleAppointment(doctor, oldAppointment));
		assertFalse(bob.rescheduleAppointment(doctor, doctor, oldAppointment, newAppointment));
	}

	@Test
	void testCancelSchedule() {
		MedicalRecord medicalRecord = new MedicalRecord("P1002", "Bob Stone", LocalDate.of(1975, 11, 2), Gender.MALE,
				BloodType.B_POS, "123", "bob.stone@example.com");
		Patient bob = new Patient(medicalRecord, "password");
		Doctor doctor = new Doctor("D002", "password", "Emily Clarke", Gender.FEMALE, 38);
		Appointment appointment = new Appointment(bob.getId(), doctor.getId(), LocalDate.of(2024, 10, 29),
				LocalTime.of(13, 0));
		bob.scheduleAppointment(doctor, appointment);
		bob.cancelAppointment(doctor, appointment);
		Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.of(2024, 10, 29));
		assertTrue(appointments[26] == null);
	}

}
