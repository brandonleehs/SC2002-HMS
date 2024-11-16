package hms.entity.user;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.Validation;
import hms.entity.user.attributes.Gender;

public abstract class User {

	private String id;
	private String name;
	private String passwordHash;
	private Gender gender;

	protected User(String id, String password, String name, Gender gender) {
		this.id = id;
		this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.name = name;
		this.gender = gender;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String newID){
		this.id = newID;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName){
		this.name = newName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender newGender){
		this.gender = newGender;
	}

	public boolean setPassword(String password) {
		if (Validation.validatePassword(password)) {
			this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
			return true;
		}
		return false;
	}

	public void resetPassword() {
		this.passwordHash = BCrypt.hashpw("password", BCrypt.gensalt());
	}
}