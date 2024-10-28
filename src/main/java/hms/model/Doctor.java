package hms.model;

import hms.attributes.Gender;
import hms.record.MedicalRecord;
import hms.Schedule;
import hms.appointment.Appointment;

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

    public void viewPatientMedicalRecord(MedicalRecord medicalRecord){
        medicalRecord.print();
    }

    public void updatePatientMedicalRecord(){

    }

    public void viewPersonalSchedule(){
        //print schedule
    }

    public void setAvailability(){
        //take start and end time as input
    }

    public void apptRequests(){
        //print out all entries in (new) ApptRequests.csv that match Doctor's ID
        //allow Doctor to accept or deny each request
    }

    public void viewUpcoming(){
        //prints all appointments scheduled for today
    }

    public void apptOutcome(Appointment appt){
        //user input for service type
        //user input for consultation notes
        //while loop to add medicine list
    }
}
