package hms.boundary.pharmacist;

import java.util.List;

import hms.boundary.View;
import hms.entity.medicine.ReplenishRequest;

/**
 * View class responsible for displaying pending replenishment requests in the inventory.
 * It formats and presents the list of requests, including their index, medicine name, and requested amount.
 */
public class ViewReplenishmentRequestView extends View {

	/**
     * Displays the list of active replenishment requests in a tabular format.
     * Each request includes an index, the name of the medicine, and the amount requested.
     * If no requests exist, it informs the user.
     *
     * @param replenishRequests A list of {@code ReplenishRequest} objects representing pending requests.
     */
	public void displayRequests(List<ReplenishRequest> replenishRequests) {

		String format = "| %-" + 5 + "s | %-" + (WIDTH - 21) + "s | %-" + 6 + "s | \n";

		System.out.println("Active Pending Requests:");
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
     * Displays the header for the replenishment request view screen.
     * The header includes a bordered title indicating the purpose of the view.
     */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "View Replenishment Requests");
	}
}
