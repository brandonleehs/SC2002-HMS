package hms.boundary.administrator.InventoryManagement;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class InventoryManagementView extends View {

    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Inventory Management");
    }

    public void displayOptions() {
        System.out.println("Inventory Management:");
        System.out.println("1. Add more Medicine");
        System.out.println("2. Update Medicine Stock");
        System.out.println("3. Remove Medicine");
        System.out.println("4. View Inventory");
        System.out.println("5. Change Stock Warning");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }

    public int getChoice() {
        int choice;
        try {
                choice = InputHandler.getChoice(1, 6);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
                return -1;
            }
        return choice;
    }

    public void printExit(){
        System.out.println("Exiting inventory management...");
    }
}