package hms.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MedicineInventorySerializerTest {
	private static final MedicineInventorySerializer medicineInventorySerializer = new MedicineInventorySerializer(
			"./src/test/resources/Medicine_List.csv");

	@BeforeAll
	static void setUp() {
		medicineInventorySerializer.serialize();
	}

	@Test
	void testMedicineInventoryIfNull() {
		assertTrue(medicineInventorySerializer.getMedicineStock() != null);
	}

	@Test
	void testMedicineInventoryAttributesIfSame() {
		assertTrue(medicineInventorySerializer.getMedicineStock().get("Paracetamol").get(0) == 100);
		assertTrue(medicineInventorySerializer.getMedicineStock().get("Paracetamol").get(1) == 20);
		assertTrue(medicineInventorySerializer.getMedicineStock().get("Ibuprofen").get(0) == 50);
		assertTrue(medicineInventorySerializer.getMedicineStock().get("Ibuprofen").get(1) == 10);
		assertTrue(medicineInventorySerializer.getMedicineStock().get("Amoxicillin").get(0) == 75);
		assertTrue(medicineInventorySerializer.getMedicineStock().get("Amoxicillin").get(1) == 15);
	}

}
