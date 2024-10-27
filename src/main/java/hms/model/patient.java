package hms.model;

import java.util.List;

public class Patient extends User {

    private MedicalRecord medicalRecord;

    public Patient(String id, String password, String name, Gender gender, MedicalRecord medicalRecord) {
        super(id, password, name, gender);
        this.medicalRecord = medicalRecord;
    }

    public MedicalRecord viewMedicalRecord() {
        return this.medicalRecord;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


  
    public void scheduleAppointment(Appointment appointment) {
        System.out.println("Scheduling appointment: " + appointment);
    }

    public void rescheduleAppointment(Appointment appointment) {
        System.out.println("Rescheduling appointment: " + appointment);
    }

    public void cancelAppointment(Appointment appointment) {
        System.out.println("Canceling appointment: " + appointment);
    }
}
