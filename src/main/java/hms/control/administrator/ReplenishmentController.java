package hms.control.administrator;

import java.util.List;

import hms.boundary.administrator.ReplenishRequest.ReplenishRequestView;
import hms.control.Controller;
import hms.entity.medicine.ReplenishRequest;

public class ReplenishmentController extends Controller {
    private final ReplenishRequestView replenishRequestView = new ReplenishRequestView();

    public ReplenishmentController() {}

    @Override
    public void navigate() {
        List<ReplenishRequest> pendingRequests = medicineInventory.getReplenishmentRequestList();
        if (pendingRequests.isEmpty()) {
            replenishRequestView.emptyRequests();
            return;
        }
        replenishRequestView.displayRequests(pendingRequests);
        int choice = replenishRequestView.getRequestChoice(pendingRequests.size());
        if (choice==-1){
            return;
        }
        else{
            // Print chosen Request
            ReplenishRequest chosenRequest = pendingRequests.get(choice-1);
            choice = replenishRequestView.getConfirmation();
            if (choice==3) {
                return;
            }
            else if (choice==1){
                medicineInventory.approveReplenishmentRequest(chosenRequest);
            }
            else{
                medicineInventory.removeReplenishmentRequest(chosenRequest);
            }

        }
    }
}