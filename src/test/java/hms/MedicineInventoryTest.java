package hms;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MedicineInventoryTest {
	private static final MedicineInventory medicineInventory = MedicineInventory.getMedicineInventory();

	@BeforeEach
	public void setUp() {
		medicineInventory.setStock("Paracetamol", 100);
		medicineInventory.setStock("Ibuprofen", 50);
		medicineInventory.setStock("Amoxicillin", 75);
		medicineInventory.setLowStockLevelAlertValue("Paracetamol", 20);
		medicineInventory.setLowStockLevelAlertValue("Ibuprofen", 10);
		medicineInventory.setLowStockLevelAlertValue("Amoxicillin", 15);
	}

	@Test
	public void testDecrementMedicineStockInvalid() {
		medicineInventory.decrementMedicineStock("Paracetamol");
		assertFalse(medicineInventory.getMedicineStock().get("Paracetamol") == 100);
	}

	@Test
	public void testDecrementMedicineStockValid() {
		medicineInventory.decrementMedicineStock("Paracetamol");
		assertTrue(medicineInventory.getMedicineStock().get("Paracetamol") == 99);
	}

	@Test
	public void testAddMedicineStockInvalid() {
		medicineInventory.addMedicineStock("Ibuprofen", 10);
		assertFalse(medicineInventory.getMedicineStock().get("Ibuprofen") == 10);
	}

	@Test
	public void testAddMedicineStockValid() {
		medicineInventory.addMedicineStock("Ibuprofen", 10);
		assertTrue(medicineInventory.getMedicineStock().get("Ibuprofen") == 60);
	}

}
