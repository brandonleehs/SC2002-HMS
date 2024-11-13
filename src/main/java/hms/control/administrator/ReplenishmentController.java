package hms.control.administrator;

import hms.boundary.administrator.ApproveReplenishmentRequestsView;
import hms.entity.medicine.ReplenishRequest;
import hms.repository.MedicineInventory;
import java.util.List;

public class ReplenishmentController {
    private final ApproveReplenishmentRequestsView replenishmentView;
    private final MedicineInventory medicineInventory;

    public ReplenishmentController() {
        this.replenishmentView = new ApproveReplenishmentRequestsView();
        this.medicineInventory = new MedicineInventory();
    }

    public void approveReplenishmentRequests() {
        List<ReplenishRequest> pendingRequests = medicineInventory.getReplenishmentRequestList();

        if (pendingRequests.isEmpty()) {
            System.out.println("No pending replenishment requests.");
            return;
        }

        for (ReplenishRequest request : pendingRequests) {
            replenishmentView.displayHeader();
            replenishmentView.displayRequestDetails(request);
            replenishmentView.displayOptions();

            int choice = replenishmentView.getUserInput();
            if (choice == 1) {
                approveRequest(request);
                System.out.println("Request approved.");
            } else if (choice == 2) {
                declineRequest(request);
                System.out.println("Request declined.");
            } else {
                System.out.println("Invalid choice. Skipping request.");
            }
        }
    }

    private void approveRequest(ReplenishRequest request) {
        // Add stock to the inventory
        medicineInventory.addMedicineStock(request.getMedicineName(), request.getStockToAdd());
        // Remove the request from the list
        medicineInventory.removeReplenishmentRequest(request);
    }

    private void declineRequest(ReplenishRequest request) {
        // Simply remove the request from the pending list without adding stock
        medicineInventory.removeReplenishmentRequest(request);
    }
}
