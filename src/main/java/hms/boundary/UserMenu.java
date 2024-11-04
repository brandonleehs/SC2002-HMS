package hms.boundary;

import hms.entity.user.User;

public abstract class UserMenu<E extends User> extends View {
	protected final E user;

	protected UserMenu(E user) {
		this.user = user;
	}

	abstract public void displayOptions();
}
