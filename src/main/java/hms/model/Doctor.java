package hms.model;

import java.util.Scanner;
import java.time.LocalDate;

import hms.attributes.Gender;
import hms.record.MedicalRecord;
import hms.Medicine;
import hms.Schedule;
import hms.appointment.Appointment;
import hms.record.*;

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

    public void apptOutcome(Appointment appt, Scanner sc){
        System.out.print("Enter service type: ");
        String type = sc.nextLine();

        System.out.print("Enter consultation notes: ");
        String notes = sc.nextLine();

        AppointmentOutcomeRecord outcome = new AppointmentOutcomeRecord(LocalDate.now(), type, notes);

        System.out.print("How many medication types prescribed?: ");
        int numMed = sc.nextInt();

        for(int i=0; i < numMed; i++){
            System.out.print("Input name of medication number " + (i+1) + ": ");
            String medName = sc.nextLine();

            System.out.print("Input quantity of " + medName + " prescribed: ");
            int medQuant = sc.nextInt();

            outcome.addPrescribedMedicine(new Medicine(medName, medQuant));
        }
    }
}
