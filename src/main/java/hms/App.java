package hms;

import hms.control.user.LoginController;

/**
 * The main entry point of the Hospital Management System (HMS) application.
 * <p>
 * This class initializes the application by creating an instance of the
 * {@code LoginController} and invoking its {@code navigate()} method to manage
 * the login flow.
 * </p>
 */
public class App {

	/**
	 * The main method to launch the HMS application.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		LoginController loginController = new LoginController();
		loginController.navigate();
	}
}
