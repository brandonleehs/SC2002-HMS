package hms.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineInventory;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.record.MedicalRecord;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

class PharmacistTest {
	@Test
	void testGetAllPendingMedicine() {
		Pharmacist pharmacist = new Pharmacist("P001", "password", "Mark Lee", Gender.MALE, 29);
		MedicalRecord medicalRecord = new MedicalRecord("P1001", "Alice Brown", LocalDate.of(1980, 5, 1), Gender.FEMALE,
				BloodType.A_POS, "999", "alice.brown@example.com");
		Patient patient = new Patient(medicalRecord, "password");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		Medicine medicine = new Medicine("Paracetamol");
		AppointmentOutcomeRecord appointmentOutcomeRecord = new AppointmentOutcomeRecord(LocalDate.of(2024, 11, 2),
				"General", "Fever", UUID.randomUUID());
		doctor.prescribeMedicine(medicine, appointmentOutcomeRecord);
		patient.addAppointmentOutcomeRecord(appointmentOutcomeRecord);
		assertTrue(pharmacist.getAllPendingMedicine(patient).get(0) == medicine);
	}

	@Test
	void testGetAllPendingMedicine2() {
		Pharmacist pharmacist = new Pharmacist("P001", "password", "Mark Lee", Gender.MALE, 29);
		MedicalRecord medicalRecord = new MedicalRecord("P1001", "Alice Brown", LocalDate.of(1980, 5, 1), Gender.FEMALE,
				BloodType.A_POS, "999", "alice.brown@example.com");
		Patient patient = new Patient(medicalRecord, "password");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		Medicine medicine1 = new Medicine("Paracetamol");
		Medicine medicine2 = new Medicine("Ibuprofen");
		AppointmentOutcomeRecord appointmentOutcomeRecord = new AppointmentOutcomeRecord(LocalDate.of(2024, 11, 2),
				"General", "Fever", UUID.randomUUID());
		doctor.prescribeMedicine(medicine1, appointmentOutcomeRecord);
		doctor.prescribeMedicine(medicine2, appointmentOutcomeRecord);
		patient.addAppointmentOutcomeRecord(appointmentOutcomeRecord);
		assertTrue(pharmacist.getAllPendingMedicine(patient).get(0) == medicine1);
		assertTrue(pharmacist.getAllPendingMedicine(patient).get(1) == medicine2);
	}

	@Test
	void testSubmitReplenishmentRequest() {
		Pharmacist pharmacist = new Pharmacist("P001", "password", "Mark Lee", Gender.MALE, 29);
		pharmacist.submitReplenishmentRequest("Paracetamol", 100);
		assertTrue(MedicineInventory.getInstance().getReplenishmentRequestList().get(0)
				.getMedicineName() == "Paracetamol");
		assertTrue(MedicineInventory.getInstance().getReplenishmentRequestList().get(0).getStockToAdd() == 100);
	}

}
