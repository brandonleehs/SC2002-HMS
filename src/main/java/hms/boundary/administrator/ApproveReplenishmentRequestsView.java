package hms.boundary.administrator;

import java.util.Scanner;

import hms.boundary.View;
import hms.entity.medicine.ReplenishRequest;

/**
 * The ApproveReplenishmentRequestsView class provides functionality to display
 * and handle interactions related to approving or declining medicine
 * replenishment requests.
 */
public class ApproveReplenishmentRequestsView extends View {

	/**
	 * Displays the header for the "Approve Replenishment Requests" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Approve Replenishment Requests");
	}

	/**
	 * Displays the options available for a replenishment request, allowing the user
	 * to choose between approving or declining the request.
	 */
	public void displayOptions() {
		System.out.println("1. Approve Request");
		System.out.println("2. Decline Request");
	}

	/**
	 * Displays the details of a specific replenishment request, including the
	 * medicine name and the quantity to be replenished.
	 * 
	 * @param request the ReplenishRequest object containing the details of the
	 *                replenishment request.
	 */
	public void displayRequestDetails(ReplenishRequest request) {
		System.out.println("Replenishment Request Details:");
		System.out.println("Medicine: " + request.getMedicineName());
		System.out.println("Quantity to Add: " + request.getStockToAdd());
	}

	/**
	 * Retrieves user input and returns the user's choice as an integer. If the
	 * input is invalid, it informs the user and returns -1.
	 * 
	 * @return the user's input as an integer, or -1 if the input is invalid.
	 */
	public int getUserInput() {
		Scanner scanner = new Scanner(System.in);
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid choice.");
			return -1;
		}
	}
}
