package hms.control;

import hms.boundary.ChangePasswordView;
import hms.boundary.InputHandler;
import hms.entity.user.User;

public class ChangePasswordController extends Controller {
	private User user;
	private ChangePasswordView changePasswordView;

	public ChangePasswordController(User user) {
		this.user = user;
		this.changePasswordView = new ChangePasswordView();
	}

	@Override
	public void navigate() {
		changePasswordView.displayHeader();
		changePasswordView.displayPasswordPrompt();
		String password = InputHandler.getString();
		if (this.user.setPassword(password)) {
			changePasswordView.displayPasswordChangeSuccess();
		} else {
			changePasswordView.displayPasswordChangeFailure();
		}
	}

}
