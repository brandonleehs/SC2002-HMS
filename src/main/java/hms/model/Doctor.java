package hms.model;

import hms.attributes.Gender;
import hms.Schedule;

public class Doctor extends User {
    private int age;
    private Schedule schedule;

    public Doctor(String id, String password){
        super(id, password, "name", Gender.MALE); //read name and gender from ..\..\resources\Staff_List.xlsx
        this.age=0;//read age from ..\..\resources\Staff_List.xlsx
        this.schedule=new Schedule();
    }

    public int getAge(){
        return this.age;
    }

    public Schedule getSchedule(){
        return this.schedule;
    }
}
