package hms.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import hms.attributes.Gender;
import hms.model.Pharmacist;

class PharmacistSerializerTest {

	@Test
	void testGetPharmacistMapIfNull() {
		PharmacistSerializer pharmacistSerializer = new PharmacistSerializer();
		assertTrue(pharmacistSerializer.getUserMap("Staff_List.xlsx") != null);
	}

	@Test
	void testGetPharmacistMapIfAttributesAreSame() {
		PharmacistSerializer pharmacistSerializer = new PharmacistSerializer();
		Map<String, Pharmacist> pharmacistMap;

		pharmacistMap = pharmacistSerializer.getUserMap("Staff_List.xlsx");
		Pharmacist mark = pharmacistMap.get("P001");
		assertTrue(mark.getName().equals("Mark Lee"));
		assertTrue(mark.getAge() == 29);
		assertTrue(mark.getGender() == Gender.MALE);
	}
}
