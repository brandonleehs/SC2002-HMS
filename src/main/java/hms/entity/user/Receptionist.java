package hms.entity.user;

import hms.entity.user.attributes.Gender;

public class Receptionist extends User {
    private final int age;

    public Receptionist(String id, String password, String name, Gender gender, int age) {
        super(id, password, name, gender);
        this.age=age;
    }

    public int getAge() {
        return this.age;
    }
}
