package hms.boundary.administrator;

import hms.boundary.View;
import hms.entity.medicine.ReplenishRequest;
import java.util.Scanner;

public class ApproveReplenishmentRequestsView extends View {

    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Approve Replenishment Requests");
    }

    public void displayOptions() {
        System.out.println("1. Approve Request");
        System.out.println("2. Decline Request");
    }

    public void displayRequestDetails(ReplenishRequest request) {
        System.out.println("Replenishment Request Details:");
        System.out.println("Medicine: " + request.getMedicineName());
        System.out.println("Quantity to Add: " + request.getStockToAdd());
    }

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
