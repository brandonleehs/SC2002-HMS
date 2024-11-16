package hms.boundary.administrator.ReplenishRequest;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.medicine.ReplenishRequest;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class ReplenishRequestView extends View {
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Replenish Request");
	}

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

	public int getRequestChoice(int sizeOfList) {
		int choice;
		try {
			choice = InputHandler.getChoice(1, sizeOfList);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	public void emptyRequests() {
		System.out.println("No pending replenishment requests.");
	}

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
