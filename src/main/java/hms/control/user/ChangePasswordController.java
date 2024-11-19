package hms.control.user;

import hms.boundary.user.ChangePasswordView;
import hms.control.Controller;
import hms.entity.user.User;

/**
 * Controller for handling the password change functionality for a user.
 * This class manages the process of prompting the user for a new password
 * and updating the user's password if the provided password is valid.
 */
public class ChangePasswordController extends Controller {
	private User user;
	private ChangePasswordView changePasswordView;

	/**
	 * Constructs an instance of ChangePasswordController.
	 * Initializes the user whose password is to be changed and the view for password change interaction.
	 *
	 * @param user The user whose password is being changed.
	 */
	public ChangePasswordController(User user) {
		this.user = user;
		this.changePasswordView = new ChangePasswordView();
	}

	/**
	 * Navigates the user through the password change process.
	 */
	@Override
	public void navigate() {
		changePasswordView.displayHeader();
		String password = changePasswordView.displayPasswordPrompt();
		if (this.user.setPassword(password)) {
			changePasswordView.displayPasswordChangeSuccess();
		} else {
			changePasswordView.displayPasswordChangeFailure();
		}
	}

}
