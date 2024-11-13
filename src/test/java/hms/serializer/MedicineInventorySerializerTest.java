// package hms.serializer;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;

// import hms.entity.medicine.MedicineInventory;

// class MedicineInventorySerializerTest {

// 	@Test
// 	void testMedicineInventoryIfNull() {
// 		MedicineInventorySerializer medicineInventorySerializer = new MedicineInventorySerializer();
// 		medicineInventorySerializer.getMedicineInventory("Medicine_List.xlsx");
// 		MedicineInventory medicineInventory = MedicineInventory.getInstance();
// 		assertTrue(medicineInventory.getMedicineStock() != null);
// 		assertTrue(medicineInventory.getMedicineLowStockLevelAlertValue() != null);
// 	}

// 	@Test
// 	void testMedicineInventoryAttributesIfSame() {
// 		MedicineInventorySerializer medicineInventorySerializer = new MedicineInventorySerializer();
// 		medicineInventorySerializer.getMedicineInventory("Medicine_List.xlsx");
// 		MedicineInventory medicineInventory = MedicineInventory.getInstance();
// 		assertTrue(medicineInventory.getMedicineStock().get("Paracetamol") == 100);
// 		assertTrue(medicineInventory.getMedicineLowStockLevelAlertValue().get("Paracetamol") == 20);
// 		assertTrue(medicineInventory.getMedicineStock().get("Ibuprofen") == 50);
// 		assertTrue(medicineInventory.getMedicineLowStockLevelAlertValue().get("Ibuprofen") == 10);
// 		assertTrue(medicineInventory.getMedicineStock().get("Amoxicillin") == 75);
// 		assertTrue(medicineInventory.getMedicineLowStockLevelAlertValue().get("Amoxicillin") == 15);
// 	}

// }
