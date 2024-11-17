package hms;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hms.repository.MedicineInventory;

class MedicineInventoryTest {
	private final MedicineInventory medicineInventory = new MedicineInventory();

	@BeforeEach
	public void setUp() {
		medicineInventory.setMedicineStock("Paracetamol", 100);
		medicineInventory.setMedicineStock("Ibuprofen", 50);
		medicineInventory.setMedicineStock("Amoxicillin", 75);
		medicineInventory.setStockWarningLevel("Paracetamol", 20);
		medicineInventory.setStockWarningLevel("Ibuprofen", 10);
		medicineInventory.setStockWarningLevel("Amoxicillin", 15);
	}

	@Test
	public void testDecrementMedicineStock1() {
		assertTrue(medicineInventory.dispenseMedicine("Paracetamol", 1));
		assertFalse(medicineInventory.getFullMedicine().get("Paracetamol").get(0) == 100);
	}

	@Test
	public void testDecrementMedicineStock2() {
		assertTrue(medicineInventory.dispenseMedicine("Paracetamol", 1));
		assertTrue(medicineInventory.getFullMedicine().get("Paracetamol").get(0) == 99);
	}

	@Test
	public void testAddMedicineStock3() {
		medicineInventory.addMedicineStock("Ibuprofen", 10);
		assertFalse(medicineInventory.getFullMedicine().get("Ibuprofen").get(0) == 10);
	}

	@Test
	public void testAddMedicineStock4() {
		medicineInventory.addMedicineStock("Ibuprofen", 10);
		assertTrue(medicineInventory.getFullMedicine().get("Ibuprofen").get(0) == 60);
	}

}
