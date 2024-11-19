package hms.boundary.receptionist;

import hms.boundary.InputHandler;
import hms.boundary.StaffView;
import hms.entity.user.Receptionist;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class ReceptionistMenuView extends StaffView {
    private Receptionist receptionist;
    
    public ReceptionistMenuView(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public int displayOptions() {
        String options = "Please select an option:\r\n" + "1. Register New User\r\n" 
        + "2. Change Password\r\n" + "3. Logout";
		String border = "=".repeat(WIDTH);
		String prompt = "Enter choice (1-3):";

		System.out.println(options);
		System.out.println(border);
		System.out.println(prompt);

		int choice;
        try{
            choice = InputHandler.getChoice(1, 3);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return choice;
    }

    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, String.format("Welcome, %s.", receptionist.getName()));
    }
}
