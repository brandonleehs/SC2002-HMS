package hms.boundary.user;

import hms.boundary.View;

/**
 * This class represents the start screen view of the application. It allows the user to either login
 * or quit the application. The screen includes a header with the application's logo and a list of options.
 */
public class StartView extends View {

	/**
     * Displays the available options on the start screen. This includes the options to log in or quit the application.
     * The options are printed with a separator line below them for visual clarity.
     */
	public void displayOptions() {
		System.out.println("Please select an option:");
		System.out.println("1. Login");
		System.out.println("2. Quit");
		System.out.println("-".repeat(WIDTH));
	}

	/**
     * Displays the header for the start screen. This includes the application's logo at the top of the screen.
     * The logo is displayed by calling the static `displayLogo()` method from the parent class `View`.
     */
	@Override
	public void displayHeader() {
		View.displayLogo();
	}
}
