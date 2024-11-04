package hms.boundary;

import hms.entity.user.User;

public abstract class UserMenuView<E extends User> extends View {
	protected final E user;

	protected UserMenuView(E user) {
		this.user = user;
	}

	abstract public void displayOptions();
}
