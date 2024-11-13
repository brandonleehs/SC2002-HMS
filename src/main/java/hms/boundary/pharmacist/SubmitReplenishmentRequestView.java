package hms.boundary.pharmacist;

import hms.boundary.View;

public class SubmitReplenishmentRequestView extends View{
    
    public void MedicineIndexPrompt(){
        System.out.println("Enter Index of Medicine to be replenished:");
    }

    public void MedicineAmountPrompt(){
        System.out.println("Enter Amount of Medicine to be replenished:");
    }

    public void SuccessfulRequestPrompt(){
        System.out.println("Request successfully made");
    }
        
    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Submit Replenishment Request");
    }
}
