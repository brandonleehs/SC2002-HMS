package hms.entity.user;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.Validation;
import hms.entity.attributes.Gender;

public abstract class User {

	private final String id;
	private final String name;
	private String passwordHash;
	private final Gender gender;

	protected User(String id, String password, String name, Gender gender) {
		this.id = id;
		this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.name = name;
		this.gender = gender;
	}

	public String getId() {
		return this.id;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public String getName() {
		return this.name;
	}

	public Gender getGender() {
		return this.gender;
	}

	public boolean setPassword(String password) {
		if (Validation.validatePassword(password)) {
			this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
			return true;
		}
		return false;
	}
}