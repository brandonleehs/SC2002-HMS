package hms.entity.user;

import hms.entity.attributes.Gender;

public class Administrator extends User {
	private final int age;

	public Administrator(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}
}
