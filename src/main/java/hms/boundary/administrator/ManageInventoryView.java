package hms.boundary.administrator;

import hms.boundary.View;
import java.util.Map;
import java.util.Scanner;

public class ManageInventoryView extends View {

    public void displayHeader() {
        displayBorderedText(WIDTH, "Inventory Management");
    }

    public void displayOptions() {
        System.out.println("Inventory Management:");
        System.out.println("1. Add New Medicine");
        System.out.println("2. Update Medicine Stock");
        System.out.println("3. Remove Medicine");
        System.out.println("4. View Inventory");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    public int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    public String getMedicineName() {
        System.out.print("Enter medicine name: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getStockToAdd() {
        System.out.print("Enter stock quantity: ");
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            return 0;
        }
    }

    public int getLowStockAlertValue() {
        System.out.print("Enter low stock alert value: ");
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid alert value.");
            return 0;
        }
    }

    public void displayInventory(Map<String, Integer> inventory) {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Medicine: " + entry.getKey() + ", Stock: " + entry.getValue());
        }
    }

    public void displayLowStockAlert(Map<String, Integer> lowStockLevels) {
        System.out.println("Low Stock Alerts:");
        for (Map.Entry<String, Integer> entry : lowStockLevels.entrySet()) {
            System.out.println("Medicine: " + entry.getKey() + ", Alert Level: " + entry.getValue());
        }
    }
}
