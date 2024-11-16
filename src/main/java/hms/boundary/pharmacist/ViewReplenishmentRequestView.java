package hms.boundary.pharmacist;

import java.util.List;

import hms.boundary.View;
import hms.entity.medicine.ReplenishRequest;

public class ViewReplenishmentRequestView extends View{
    
    public void displayRequests(List<ReplenishRequest> replenishRequests){
        
        String format = "| %-" + (WIDTH - 42) + "s | %-" + 10 + "s | %-" + 10 + "s | \n";

		System.out.println("Active Pending Requests:");
        if (replenishRequests.isEmpty()) {
			System.out.println("There are no requests.");
		} else {
			System.out.printf(format, "Index", "Medicine Name", "Amount");
			System.out.println("|" + "-".repeat(WIDTH - 29) + "|" + "-".repeat(12) + "|"+"-".repeat(13) + "|");
			for (int i=0; i<replenishRequests.size(); i++) {
				System.out.printf(format, i+1, replenishRequests.get(i).getMedicineName(), replenishRequests.get(i).getStockToAdd());
			}
		}
    }



    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "View Replenishment Requests");
    }
}
