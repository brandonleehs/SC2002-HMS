package hms.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import hms.entity.user.Doctor;
import hms.entity.user.attributes.Gender;

class DoctorSerializerTest {

	@Test
	void testGetDoctorMapIfNull() {
		DoctorSerializer doctorSerializer = new DoctorSerializer();
		assertTrue(doctorSerializer.getMap("Staff_List.xlsx") != null);
	}

	@Test
	void testGetDoctorMapIfAttributesAreSame() {
		DoctorSerializer doctorSerializer = new DoctorSerializer();
		Map<String, Doctor> doctorMap;

		doctorMap = doctorSerializer.getMap("Staff_List.xlsx");
		Doctor john = doctorMap.get("D001");
		assertTrue(john.getName().equals("John Smith"));
		assertTrue(john.getAge() == 45);
		assertTrue(john.getGender() == Gender.MALE);
	}

}
