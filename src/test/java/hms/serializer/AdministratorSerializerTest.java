package hms.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import hms.entity.attributes.Gender;
import hms.entity.user.Administrator;

class AdministratorSerializerTest {

	@Test
	void testGetAdministratorMapIfNull() {
		AdministratorSerializer administratorSerializer = new AdministratorSerializer();
		assertTrue(administratorSerializer.getMap("Staff_List.xlsx") != null);
	}

	@Test
	void testGetAdministratorMapIfAttributesAreSame() {
		AdministratorSerializer administratorSerializer = new AdministratorSerializer();
		Map<String, Administrator> administratorMap;

		administratorMap = administratorSerializer.getMap("Staff_List.xlsx");
		Administrator sarah = administratorMap.get("A001");
		assertTrue(sarah.getName().equals("Sarah Lee"));
		assertTrue(sarah.getAge() == 40);
		assertTrue(sarah.getGender() == Gender.FEMALE);
	}

}
