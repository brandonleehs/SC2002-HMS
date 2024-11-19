package hms.boundary.administrator;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.user.User;

/**
 * The view class for resetting a user's password. It handles displaying the
 * header for the reset password section, prompting the administrator to choose
 * a user, and displaying success messages after a password reset.
 */
public class ResetPasswordView extends View {

	/**
	 * Displays the header with the title "Reset Password".
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Reset Password");
	}

	/**
	 * Prompts the administrator to enter the ID of the user whose password needs to
	 * be reset.
	 * 
	 * @return The ID of the user to reset the password for.
	 */
	public String chooseUser() {
		System.out.print("Enter ID of user to reset: ");
		return InputHandler.getString();
	}

	/**
	 * Displays a success message indicating that the password for the specified
	 * user has been successfully reset.
	 * 
	 * @param user The user whose password was successfully reset.
	 */
	public void successMessage(User user) {
		System.out.println("Password for user " + user.getId() + " " + user.getName() + " successfully reset.");
	}
}
