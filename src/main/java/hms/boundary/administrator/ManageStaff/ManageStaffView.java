package hms.boundary.administrator.ManageStaff;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class ManageStaffView extends View {
    
    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Manage Hospital Staff");
    }

    public void displayOptions() {
        System.out.println("1. Add Doctor/Pharmacist");
        System.out.println("2. Update Doctor/Pharmacist");
        System.out.println("3. Remove Doctor/Pharmacist");
        System.out.println("4. Display All Doctors and Pharmacists");
    }

    public void displayRemoveSuccess() {
        System.out.println("User successfully removed.");
    }

    public void displayUserNotFound() {
        System.out.println("User not found.");
    }

    public int getChoice() {
        int choice;
        try {
                choice = InputHandler.getChoice(1, 5);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
                return -1;
            }
        return choice;
    }

    public void printExit(){
        System.out.println("Returning to main menu...");
    }
}