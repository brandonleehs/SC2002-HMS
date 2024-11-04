package hms;

import hms.boundary.LoginView;
import hms.boundary.View;

public class App {

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.show();
		View.close();
	}
}
