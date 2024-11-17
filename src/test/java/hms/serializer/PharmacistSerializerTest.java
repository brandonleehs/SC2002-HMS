package hms.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import hms.entity.user.Pharmacist;
import hms.entity.user.attributes.Gender;

class PharmacistSerializerTest {

	@Test
	void testGetPharmacistMapIfNull() {
		PharmacistSerializer pharmacistSerializer = new PharmacistSerializer("./src/test/resources/Staff_List.csv");
		assertTrue(pharmacistSerializer.getMap() != null);
	}

	@Test
	void testGetPharmacistMapIfAttributesAreSame() {
		PharmacistSerializer pharmacistSerializer = new PharmacistSerializer("./src/test/resources/Staff_List.csv");
		Map<String, Pharmacist> pharmacistMap;

		pharmacistMap = pharmacistSerializer.getMap();
		Pharmacist mark = pharmacistMap.get("P001");
		assertTrue(mark.getName().equals("Mark Lee"));
		assertTrue(mark.getAge() == 29);
		assertTrue(mark.getGender() == Gender.MALE);
	}
}
