package hms.entity.user;

import hms.entity.user.attributes.Gender;

public class Pharmacist extends User {
	private final int age;

	public Pharmacist(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}
}