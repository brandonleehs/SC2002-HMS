package hms.boundary.receptionist;

import hms.boundary.InputHandler;
import hms.boundary.StaffView;
import hms.entity.user.Receptionist;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

/**
 * This class represents the view for the receptionist menu, which provides options for the receptionist
 * to perform actions such as registering a new user, changing the password, or logging out.
 */
public class ReceptionistMenuView extends StaffView {
    private Receptionist receptionist;
    
    /**
     * Constructs a ReceptionistMenuView object with the specified receptionist.
     * This constructor is used to initialize the view with the receptionist's information.
     * 
     * @param receptionist the Receptionist object associated with the current view
     */
    public ReceptionistMenuView(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    /**
     * Displays the available options for the receptionist to select from. The options include
     * Register New User
     * Change Password
     * Logout
     * The method waits for the user input and returns the selected option.
     * 
     * @return the option selected by the receptionist. Returns -1 if there is an input error.
     */
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

    /**
     * Displays the header of the receptionist menu, including a welcome message that incorporates
     * the receptionist's name. This message is displayed within a bordered text format.
     */
    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, String.format("Welcome, %s.", receptionist.getName()));
    }
}
