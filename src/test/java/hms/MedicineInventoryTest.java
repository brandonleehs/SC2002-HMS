package hms;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hms.entity.medicine.MedicineInventory;

class MedicineInventoryTest {
	private static final MedicineInventory medicineInventory = MedicineInventory.getInstance();

	@BeforeEach
	public void setUp() {
		medicineInventory.setMedicineStock("Paracetamol", 100);
		medicineInventory.setMedicineStock("Ibuprofen", 50);
		medicineInventory.setMedicineStock("Amoxicillin", 75);
		medicineInventory.setLowStockLevelAlertValue("Paracetamol", 20);
		medicineInventory.setLowStockLevelAlertValue("Ibuprofen", 10);
		medicineInventory.setLowStockLevelAlertValue("Amoxicillin", 15);
	}

	@Test
	public void testDecrementMedicineStock1() {
		assertTrue(medicineInventory.dispenseMedicine("Paracetamol"));
		assertFalse(medicineInventory.getMedicineStock().get("Paracetamol") == 100);
	}

	@Test
	public void testDecrementMedicineStock2() {
		assertTrue(medicineInventory.dispenseMedicine("Paracetamol"));
		assertTrue(medicineInventory.getMedicineStock().get("Paracetamol") == 99);
	}

	@Test
	public void testAddMedicineStock3() {
		medicineInventory.addMedicineStock("Ibuprofen", 10);
		assertFalse(medicineInventory.getMedicineStock().get("Ibuprofen") == 10);
	}

	@Test
	public void testAddMedicineStock4() {
		medicineInventory.addMedicineStock("Ibuprofen", 10);
		assertTrue(medicineInventory.getMedicineStock().get("Ibuprofen") == 60);
	}

}
