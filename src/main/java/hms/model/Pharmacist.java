package hms.model;

import hms.attributes.Gender;

public class Pharmacist extends User{
    private final int age;
    
    public Pharmacist(String id, String password, String name, Gender gender, int age){
        super(id, password, name, gender);
        age = age;
    }

    public int getAge{
        return this.age;
    }

    public MedicalRecord viewAppointOutcomeRecord(Patient pname){
        return pname.getAppointmentOutcomeRecordLatest();
    }

    public void updatePrescriptionStatus(Patient pname){
        // Toggles between pending and dispensed (e.g. if pending, then changes to dispensed)
        MedicalRecord temp = pname.getAppointmentOutcomeRecordLatest();
        temp.setPrescriptionStatus();
    }

    public void checkInventory(MedicineInventory medin){
        medin.printAvailableMedicines();
    }

    public void checkInventoryStock(MedicineInventory medin){
        medin.viewInventoryStock();
    }

    public void checkInventoryWarning(MedicineInventory medin){
        medin.viewInventoryWarning();
    }
}