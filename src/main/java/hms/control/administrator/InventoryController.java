package hms.control.administrator;

import hms.boundary.administrator.ManageInventoryView;
import hms.entity.medicine.ReplenishRequest;
import hms.repository.MedicineInventory;

public class InventoryController {
    private final ManageInventoryView inventoryView;
    private final MedicineInventory medicineInventory;

    public InventoryController() {
        this.inventoryView = new ManageInventoryView();
        this.medicineInventory = new MedicineInventory();
    }

    public void manageInventory() {
        int choice;
        do {
            inventoryView.displayOptions();
            choice = inventoryView.getUserInput();

            switch (choice) {
                case 1 -> addMedicine();
                case 2 -> updateMedicineStock();
                case 3 -> removeMedicine();
                case 4 -> displayInventory();
                case 5 -> System.out.println("Exiting inventory management...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void addMedicine() {
        String name = inventoryView.getMedicineName();
        int stock = inventoryView.getStockToAdd();
        int lowStockAlert = inventoryView.getLowStockAlertValue();
        medicineInventory.addNewMedicine(name, stock, lowStockAlert);
        System.out.println("Medicine added successfully.");
    }

    private void updateMedicineStock() {
        String name = inventoryView.getMedicineName();
        int stockToAdd = inventoryView.getStockToAdd();
        if (medicineInventory.updateMedicineStock(name, stockToAdd)) {
            System.out.println("Medicine stock updated successfully.");
        } else {
            System.out.println("Medicine not found in inventory.");
        }
    }

    private void removeMedicine() {
        String name = inventoryView.getMedicineName();
        if (medicineInventory.removeMedicine(name)) {
            System.out.println("Medicine removed successfully.");
        } else {
            System.out.println("Medicine not found in inventory.");
        }
    }

    private void displayInventory() {
        inventoryView.displayInventory(medicineInventory.getMedicineStock());
        inventoryView.displayLowStockAlert(medicineInventory.getMedicineLowStockLevelAlertValue());
    }
}
