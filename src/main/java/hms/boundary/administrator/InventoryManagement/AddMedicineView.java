package hms.boundary.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class AddMedicineView extends View {
    
    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Add Medicine");
    }

    public int MedicineIndexPrompt(List<String> medicineNames){
        int choice;
        System.out.println("Enter Index of Medicine to be added:");
        try{
            choice = InputHandler.getChoice(1, medicineNames.size());
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
                return -1;
        }
        return choice;
    }

    public int MedicineAmountPrompt(){
        int amount;
        System.out.println("Enter Amount of Medicine to be added:");
        try{
            amount = InputHandler.getChoice(1, 9999);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
                amount=-1;
        }
        return amount;
    }

    public void SuccessfulRequestPrompt(){
        System.out.println("Medicine successfully added");
        System.out.println("|" + "-".repeat(WIDTH - 29) + "|" + "-".repeat(12) + "|"+"-".repeat(13) + "|");
    }
}
