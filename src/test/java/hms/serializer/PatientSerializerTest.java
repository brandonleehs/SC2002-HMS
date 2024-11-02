package hms.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;

import hms.entity.attributes.BloodType;
import hms.entity.attributes.Gender;
import hms.entity.user.Patient;

class PatientSerializerTest {

	@Test
	void testGetPatientMapIfNull() {
		PatientSerializer patientSerializer = new PatientSerializer();
		assertTrue(patientSerializer.getMap("Patient_List.xlsx") != null);

	}

	@Test
	void testGetPatientMapIfAttributesAreSame() {
		PatientSerializer patientSerializer = new PatientSerializer();
		Map<String, Patient> patientMap;

		patientMap = patientSerializer.getMap("Patient_List.xlsx");
		Patient alice = patientMap.get("P1001");
		assertTrue(alice.getName().equals("Alice Brown"));
		assertTrue(alice.getDateOfBirth().isEqual(LocalDate.of(1980, 5, 14)));
		assertTrue(alice.getGender() == Gender.FEMALE);
		assertTrue(alice.getBloodType() == BloodType.A_POS);
		assertTrue(alice.getEmailAddress().equals("alice.brown@example.com"));
	}
}
