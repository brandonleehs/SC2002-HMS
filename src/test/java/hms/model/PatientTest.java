package hms.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

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

}
