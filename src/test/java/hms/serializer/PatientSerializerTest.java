package hms.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;

import hms.entity.user.Patient;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

class PatientSerializerTest {

	@Test
	void testGetPatientMapIfNull() {
		PatientSerializer patientSerializer = new PatientSerializer("./src/test/resources/Patient_List.csv");
		assertTrue(patientSerializer.getMap() != null);

	}

	@Test
	void testGetPatientMapIfAttributesAreSame() {
		PatientSerializer patientSerializer = new PatientSerializer("./src/test/resources/Patient_List.csv");
		Map<String, Patient> patientMap;

		patientMap = patientSerializer.getMap();
		Patient alice = patientMap.get("P1001");
		assertTrue(alice.getName().equals("Alice Brown"));
		assertTrue(alice.getDateOfBirth().isEqual(LocalDate.of(1980, 5, 14)));
		assertTrue(alice.getGender() == Gender.FEMALE);
		assertTrue(alice.getBloodType() == BloodType.A_POS);
		assertTrue(alice.getEmailAddress().equals("alice.brown@example.com"));
	}
}
