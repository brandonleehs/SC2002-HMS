package hms.boundary.administrator.ReplenishRequest;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.medicine.ReplenishRequest;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * The ReplenishRequestView class manages the user interface for viewing and
 * handling replenishment requests. It displays pending requests, allows the
 * user to select and approve or deny requests, and handles user input.
 */
public class ReplenishRequestView extends View {

	/**
	 * Displays the header for the Replenish Request view. It provides a bordered
	 * text representation with the title "Replenish Request".
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Replenish Request");
	}

	/**
	 * Displays a list of replenishment requests. It shows the medicine name and the
	 * amount to be added for each request.
	 * 
	 * @param replenishRequests a list of replenish requests to be displayed.
	 */
	public void displayRequests(List<ReplenishRequest> replenishRequests) {

		String format = "| %-" + 5 + "s | %-" + (WIDTH - 21) + "s | %-" + 6 + "s | \n";

		displayHeader();
		if (replenishRequests.isEmpty()) {
			System.out.println("There are no requests.");
		} else {
			System.out.printf(format, "Index", "Medicine Name", "Amount");
			System.out.printf(format, "-".repeat(5), "-".repeat(WIDTH - 21), "-".repeat(6));
			for (int i = 0; i < replenishRequests.size(); i++) {
				System.out.printf(format, i + 1, replenishRequests.get(i).getMedicineName(),
						replenishRequests.get(i).getStockToAdd());
			}
		}
	}

	/**
	 * Prompts the user to select a replenishment request from the list.
	 * 
	 * @param sizeOfList the total number of replenish requests.
	 * @return the user's choice as an integer corresponding to the selected request
	 *         index.
	 */
	public int getRequestChoice(int sizeOfList) {
		int choice;
		try {
			choice = InputHandler.getChoice(1, sizeOfList);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Displays a message indicating there are no pending replenishment requests.
	 */
	public void emptyRequests() {
		System.out.println("No pending replenishment requests.");
	}

	/**
	 * Prompts the user to confirm whether they would like to approve, deny, or
	 * cancel the replenishment request.
	 * 
	 * @return the user's choice as an integer (1 for approve, 2 for deny, 3 for
	 *         cancel).
	 */
	public int getConfirmation() {
		int choice;
		System.out.println("Would you like to approve or deny this request?");
		System.out.println("1. Approve");
		System.out.println("2. Deny");
		System.out.println("3. Cancel");
		try {
			choice = InputHandler.getChoice(1, 3);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return 3;
		}
		return choice;
	}

}
