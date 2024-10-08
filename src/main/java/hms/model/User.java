package hms.model;

abstract class User {

	public enum Gender {
		MALE, FEMALE;
	}

	// To rename to passwordHash if we are using BCrypt to salt
	private final String id;
	private final String name;
	private String password;
	private final Gender gender;

	protected User(String id, String password, String name, Gender gender) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
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

	public void setPassword(String password) {
		this.password = password;
	}
}