package hms;

import hms.control.user.LoginController;

public class App {

	public static void main(String[] args) {
		LoginController loginController = new LoginController();
		loginController.navigate();
	}
}
