package hms.boundary.user;

import hms.boundary.InputHandler;
import hms.boundary.View;

/**
 * This class represents the view for the change password functionality. It allows the user to
 * input a new password, displays relevant success or failure messages, and manages the user interface
 * for password changes.
 */
public class ChangePasswordView extends View {

	/**
     * Displays the header for the change password view, which includes a bordered title "Change Password".
     * This is called when the view is rendered to show the user the context for changing their password.
     */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Change Password");
	}

	/**
     * Displays a prompt asking the user to enter a new password. This method collects the user's input
     * for the new password by calling the appropriate input handler.
     * 
     * @return the new password entered by the user.
     */
	public String displayPasswordPrompt() {
		System.out.print("Enter new password: ");
		return InputHandler.getString();
	}

	/**
     * Displays a message indicating that the password change was successful.
     * This message is shown after the user has successfully changed their password.
     */
	public void displayPasswordChangeSuccess() {
		System.out.println("Password changed successfully.");
	}

	/**
     * Displays a message indicating that the password change failed.
     * This message is shown when the password change process encounters an issue.
     */
	public void displayPasswordChangeFailure() {
		System.out.println("Password not changed.");
	}
}
