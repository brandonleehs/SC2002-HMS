package hms.model;

import hms.attributes.Gender;
import hms.attributes.Role;

abstract class User {

	// To rename to passwordHash if we are using BCrypt to salt
	private final String id;
	private final String name;
	private String password;
	private final Gender gender;
	private final Role role;

	protected User(String id, String password, String name, Gender gender, Role role) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.role = role;
	}

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public String getName() {
		return this.name;
	}

	public Gender getGender() {
		return this.gender;
	}

	public Role getRole() {
		return this.role;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}