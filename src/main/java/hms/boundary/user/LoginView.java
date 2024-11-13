package hms.boundary.user;

import hms.boundary.View;

public class LoginView extends View {
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Login");
	}

	public void displayIdPrompt() {
		System.out.print("Enter id: ");
	}

	public void displayPasswordPrompt() {
		System.out.print("Enter password: ");
	}
}