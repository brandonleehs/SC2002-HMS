package hms.boundary.user;

import hms.boundary.View;

public class StartView extends View {

	public void displayOptions() {
		System.out.println("Please select an option:");
		System.out.println("1. Login");
		System.out.println("2. Quit");
		System.out.println("-".repeat(WIDTH));
	}

	@Override
	public void displayHeader() {
		View.displayLogo();
	}
}
