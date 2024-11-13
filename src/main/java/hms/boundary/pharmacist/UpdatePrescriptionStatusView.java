package hms.boundary.pharmacist;

import hms.boundary.View;

public class UpdatePrescriptionStatusView extends View{
    public void AppointmentPrompt(){
        System.out.println("Enter Appointment index: ");
    }

    public void MedicinePrompt(){
        System.out.println("Enter Medicine Index: ");
    }

    public void DecisionPrompt(){
        System.out.println("Enter 1 to Dispense, 2 to Undispense: ");
    }

    public void SuccessfulDispense(){
        System.out.println("Successfully dispensed medicine!");
    }

    public void UnsuccessfulDispense(){
        System.out.println("Dispense unsuccessful, insufficient medicine.");
    }

    public void emptyRecords(){
        System.out.println("There are no records.");
    }

    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Update Prescription Status");
    }
}
