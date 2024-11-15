package hms.boundary.user;

import hms.boundary.InputHandler;
import hms.boundary.View;

public class LoginView extends View {
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Login");
	}

	public String displayIdPrompt() {
		System.out.print("Enter id: ");
		return InputHandler.getString();
	}

	public String displayPasswordPrompt() {
		System.out.print("Enter password: ");
		return InputHandler.getString();
	}
}