package hms;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import hms.attributes.BloodType;
import hms.attributes.Gender;
import hms.model.Patient;

class PatientControllerTest {

	@Test
	void testGetPatientTableIfNull() {
		PatientController patientController = new PatientController("/Patient_List.xlsx");
		assertTrue(patientController.getPatientTable() != null);
	}

	@Test
	void testGetPatientTableIfAttributesAreSame() {
		PatientController patientController = new PatientController("/Patient_List.xlsx");
		Patient alice = patientController.getPatientTable().get("P1001");
		assertTrue(alice.getName().equals("Alice Brown"));
		assertTrue(alice.getDateOfBirth().isEqual(LocalDate.of(1980, 5, 14)));
		assertTrue(alice.getGender() == Gender.FEMALE);
		assertTrue(alice.getBloodType() == BloodType.A_POS);
		assertTrue(alice.getEmailAddress().equals("alice.brown@example.com"));
	}
}
