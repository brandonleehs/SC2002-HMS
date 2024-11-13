package hms.control.pharmacist;

import hms.boundary.pharmacist.ViewReplenishmentRequestView;
import hms.control.Controller;

public class ViewReplenishmentRequestController extends Controller{
    private final ViewReplenishmentRequestView viewReplenishmentRequestView;

    public ViewReplenishmentRequestController(){
        this.viewReplenishmentRequestView = new ViewReplenishmentRequestView();
    }

    @Override
    public void navigate(){
        this.viewReplenishmentRequestView.displayHeader();
        viewReplenishmentRequestView.displayRequests(medicineInventory.getReplenishmentRequestList());
    }

}
