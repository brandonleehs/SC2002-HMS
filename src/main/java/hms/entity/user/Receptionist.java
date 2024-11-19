package hms.entity.user;

import hms.entity.user.attributes.Gender;

/**
 * Represents a Receptionist, a type of User with a specific age.
 */
public class Receptionist extends User {
	private final int age;

	/**
     * Constructs a Receptionist instance with the specified details.
     *
     * @param id the unique ID of the receptionist.
     * @param password the password associated with the receptionist's account.
     * @param name the name of the receptionist.
     * @param gender the gender of the receptionist.
     * @param age the age of the receptionist.
     */
	public Receptionist(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
	}

	/**
     * Retrieves the age of the receptionist.
     *
     * @return the age of the receptionist.
     */
	public int getAge() {
		return this.age;
	}
}
