package hms.boundary.user;

import hms.boundary.InputHandler;
import hms.boundary.View;

/**
 * This class represents the view for the login functionality. It prompts the user for their login credentials,
 * including their user ID and password. The class displays the relevant prompts and manages the user interface 
 * during the login process.
 */
public class LoginView extends View {

	/**
     * Displays the header for the login view, which includes a bordered title "Login".
     * This method is called to show the user the context for the login screen.
     */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Login");
	}

	/**
     * Displays a prompt asking the user to enter their user ID. This method collects the user's input
     * for the user ID by calling the appropriate input handler.
     * 
     * @return the user ID entered by the user.
     */
	public String displayIdPrompt() {
		System.out.print("Enter id: ");
		return InputHandler.getString();
	}

	/**
     * Displays a prompt asking the user to enter their password. This method collects the user's input
     * for the password by calling the appropriate input handler.
     * 
     * @return the password entered by the user.
     */
	public String displayPasswordPrompt() {
		System.out.print("Enter password: ");
		return InputHandler.getString();
	}
}