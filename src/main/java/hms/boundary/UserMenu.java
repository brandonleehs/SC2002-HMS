package hms.boundary;

import hms.entity.user.User;

public abstract class UserMenu extends View {
	protected final User user;

	protected UserMenu(User user) {
		this.user = user;
	}

	abstract public void displayOptions();
}
