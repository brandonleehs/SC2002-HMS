package hms.entity.user;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.auth.Validation;
import hms.entity.user.attributes.Gender;

/**
 * Represents a User in the system with an ID, name, gender, and password.
 * The password is stored as a hashed value for security.
 */
public abstract class User {

	private String id;
	private String name;
	private String passwordHash;
	private Gender gender;

	/**
     * Constructs a User with the specified ID, password, name, and gender.
     * The password is hashed using BCrypt for security.
     *
     * @param id the unique ID of the user.
     * @param password the password of the user.
     * @param name the name of the user.
     * @param gender the gender of the user.
     */
	protected User(String id, String password, String name, Gender gender) {
		this.id = id;
		this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.name = name;
		this.gender = gender;
	}

	/**
     * Retrieves the ID of the user.
     *
     * @return the ID of the user.
     */
	public String getId() {
		return this.id;
	}

	/**
     * Sets a new ID for the user.
     *
     * @param newID the new ID to set.
     */
	public void setId(String newID) {
		this.id = newID;
	}

	/**
     * Retrieves the hashed password of the user.
     *
     * @return the hashed password of the user.
     */
	public String getPasswordHash() {
		return this.passwordHash;
	}

	/**
     * Retrieves the name of the user.
     *
     * @return the name of the user.
     */
	public String getName() {
		return this.name;
	}

	/**
     * Sets a new name for the user.
     *
     * @param newName the new name to set.
     */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
     * Retrieves the gender of the user.
     *
     * @return the gender of the user.
     */
	public Gender getGender() {
		return this.gender;
	}

	/**
     * Sets a new gender for the user.
     *
     * @param newGender the new gender to set.
     */
	public void setGender(Gender newGender) {
		this.gender = newGender;
	}

	/**
     * Sets a new password for the user after validating it.
     * The password is hashed using BCrypt before being stored.
     *
     * @param password the new password to set.
     * @return true if the password is successfully set, false if validation fails.
     */
	public boolean setPassword(String password) {
		if (Validation.validatePassword(password)) {
			this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
			return true;
		}
		return false;
	}

	/**
     * Sets the hashed password directly. This should generally be used only for password recovery.
     *
     * @param passwordHash the hashed password to set.
     */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
     * Resets the password to a default value ("password").
     * The password is hashed using BCrypt.
     */
	public void resetPassword() {
		this.passwordHash = BCrypt.hashpw("password", BCrypt.gensalt());
	}
}