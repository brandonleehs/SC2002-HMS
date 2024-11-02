package hms.entity.user;

import java.util.ArrayList;
import java.util.List;

import hms.entity.attributes.Gender;
import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineInventory;
import hms.entity.record.AppointmentOutcomeRecord;

public class Pharmacist extends User {
	private final int age;
	private static final MedicineInventory medicineInventory = MedicineInventory.getInstance();

	public Pharmacist(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	// If successful, returns true
	public boolean dispenseMedicine(Patient patient, Medicine medicine) {
		return medicineInventory.dispenseMedicine(medicine.getName());
	}

	public List<Medicine> getAllPendingMedicine(Patient patient) {
		List<Medicine> medicineList = new ArrayList<Medicine>();
		List<AppointmentOutcomeRecord> appointmentOutcomeRecordList = patient.getAppointmentOutcomeRecordList();
		for (AppointmentOutcomeRecord appointmentOutcomeRecord : appointmentOutcomeRecordList) {
			for (Medicine medicine : appointmentOutcomeRecord.getPrescribedMedicineList()) {
				medicineList.add(medicine);
			}
		}
		return medicineList;
	}

	public void submitReplenishmentRequest(String medicineName, int stockToAdd) {
		medicineInventory.addReplenishmentRequest(medicineName, stockToAdd);
	}
//
//	public void updatePrescriptionStatus(Patient pname) {
//		// Toggles between pending and dispensed (e.g. if pending, then changes to
//		// dispensed)
//		MedicalRecord temp = pname.getAppointmentOutcomeRecordLatest();
//		temp.setPrescriptionStatus();
//	}
//
//	public void checkInventory(MedicineInventory medin) {
//		medin.printAvailableMedicines();
//	}
//
//	public void checkInventoryStock(MedicineInventory medin) {
//		medin.viewInventoryStock();
//	}
//
//	public void checkInventoryWarning(MedicineInventory medin) {
//		medin.viewInventoryWarning();
//	}
}