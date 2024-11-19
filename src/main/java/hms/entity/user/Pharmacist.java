package hms.entity.user;

import hms.entity.user.attributes.Gender;

/**
 * Represents a Pharmacist, a type of User with a specific age.
 */
public class Pharmacist extends User {
	private final int age;

	/**
     * Constructs a Pharmacist instance with the specified details.
     *
     * @param id the unique ID of the pharmacist.
     * @param password the password associated with the pharmacist's account.
     * @param name the name of the pharmacist.
     * @param gender the gender of the pharmacist.
     * @param age the age of the pharmacist.
     */
	public Pharmacist(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
	}

	/**
     * Retrieves the age of the pharmacist.
     *
     * @return the age of the pharmacist.
     */
	public int getAge() {
		return this.age;
	}
}