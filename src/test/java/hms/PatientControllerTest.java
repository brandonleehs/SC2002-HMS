package hms;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import hms.entity.user.Patient;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;
import hms.repository.PatientRepository;

class PatientControllerTest {
	private static final PatientRepository patientRepository = new PatientRepository();

	@Test
	void testGetPatientTableIfNull() {
		assertTrue(patientRepository.getMap() != null);
	}

	@Test
	void testGetPatientTableIfAttributesAreSame() {
		Patient alice = patientRepository.getMap().get("P1001");
		assertTrue(alice.getName().equals("Alice Brown"));
		assertTrue(alice.getDateOfBirth().isEqual(LocalDate.of(1980, 5, 14)));
		assertTrue(alice.getGender() == Gender.FEMALE);
		assertTrue(alice.getBloodType() == BloodType.A_POS);
		assertTrue(alice.getEmailAddress().equals("alice.brown@example.com"));
	}
}
