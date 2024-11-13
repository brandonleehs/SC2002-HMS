package hms;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import hms.entity.user.Patient;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

class PatientControllerTest {
	private static PatientController patientController = PatientController.getInstance();

	@BeforeAll
	static void setUp() {
		patientController.loadPatientMap("Patient_List.xlsx");
	}

	@Test
	void testGetPatientTableIfNull() {
		assertTrue(patientController.getPatientMap() != null);
	}

	@Test
	void testGetPatientTableIfAttributesAreSame() {
		Patient alice = patientController.getPatientMap().get("P1001");
		assertTrue(alice.getName().equals("Alice Brown"));
		assertTrue(alice.getDateOfBirth().isEqual(LocalDate.of(1980, 5, 14)));
		assertTrue(alice.getGender() == Gender.FEMALE);
		assertTrue(alice.getBloodType() == BloodType.A_POS);
		assertTrue(alice.getEmailAddress().equals("alice.brown@example.com"));
	}
}
