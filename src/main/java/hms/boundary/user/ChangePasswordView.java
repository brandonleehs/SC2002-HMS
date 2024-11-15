package hms.boundary.user;

import hms.boundary.InputHandler;
import hms.boundary.View;

public class ChangePasswordView extends View {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Change Password");
	}

	public String displayPasswordPrompt() {
		System.out.print("Enter new password: ");
		return InputHandler.getString();
	}

	public void displayPasswordChangeSuccess() {
		System.out.println("Password changed successfully.");
	}

	public void displayPasswordChangeFailure() {
		System.out.println("Password not changed.");
	}
}
