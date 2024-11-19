package hms.boundary.administrator;

import java.util.Map;
import java.util.Scanner;

import hms.boundary.View;

/**
 * The ManageInventoryView class provides an interface for administrators to
 * manage the inventory of medicines. It includes functionalities for adding,
 * updating, removing medicines, viewing inventory, and setting low stock
 * alerts.
 */
public class ManageInventoryView extends View {
	/**
	 * Displays the header for the Inventory Management view.
	 */
	public void displayHeader() {
		displayBorderedText(WIDTH, "Inventory Management");
	}

	/**
	 * Displays the options menu for inventory management, allowing administrators
	 * to perform various inventory-related actions.
	 */
	public void displayOptions() {
		System.out.println("Inventory Management:");
		System.out.println("1. Add New Medicine");
		System.out.println("2. Update Medicine Stock");
		System.out.println("3. Remove Medicine");
		System.out.println("4. View Inventory");
		System.out.println("5. Exit");
		System.out.print("Enter choice: ");
	}

	/**
	 * Retrieves user input and returns their choice as an integer. If the input is
	 * invalid, it displays an error message and returns -1.
	 *
	 * @return the user's choice as an integer, or -1 if the input is invalid.
	 */
	public int getUserInput() {
		Scanner scanner = new Scanner(System.in);
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
			return -1;
		}
	}

	/**
	 * Prompts the user to input the name of a medicine.
	 *
	 * @return the name of the medicine as a string.
	 */
	public String getMedicineName() {
		System.out.print("Enter medicine name: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	/**
	 * Prompts the user to input the stock quantity to add. If the input is invalid,
	 * it displays an error message and returns 0.
	 *
	 * @return the stock quantity as an integer, or 0 if the input is invalid.
	 */
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

	/**
	 * Prompts the user to input the low stock alert value. If the input is invalid,
	 * it displays an error message and returns 0.
	 *
	 * @return the low stock alert value as an integer, or 0 if the input is
	 *         invalid.
	 */
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

	/**
	 * Displays the current inventory, showing each medicine and its stock quantity.
	 *
	 * @param inventory a map containing the medicine names as keys and their stock
	 *                  quantities as values.
	 */
	public void displayInventory(Map<String, Integer> inventory) {
		System.out.println("Current Inventory:");
		for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
			System.out.println("Medicine: " + entry.getKey() + ", Stock: " + entry.getValue());
		}
	}

	/**
	 * Displays a list of medicines with low stock levels, including the alert
	 * levels.
	 *
	 * @param lowStockLevels a map containing medicine names as keys and their low
	 *                       stock alert levels as values.
	 */
	public void displayLowStockAlert(Map<String, Integer> lowStockLevels) {
		System.out.println("Low Stock Alerts:");
		for (Map.Entry<String, Integer> entry : lowStockLevels.entrySet()) {
			System.out.println("Medicine: " + entry.getKey() + ", Alert Level: " + entry.getValue());
		}
	}
}
