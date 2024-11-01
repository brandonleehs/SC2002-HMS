package hms.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import hms.Medicine;
import hms.attributes.BloodType;
import hms.attributes.Gender;
import hms.record.AppointmentOutcomeRecord;
import hms.record.MedicalRecord;

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
				"General", "Fever");
		doctor.prescribeMedicine(medicine, appointmentOutcomeRecord);
		patient.addAppointmentOutcomeRecord(appointmentOutcomeRecord);
		System.out.println(patient.getAppointmentOutcomeRecordList().get(0));
		System.out.println(pharmacist.getAllPendingMedicine(patient));
		assertTrue(pharmacist.getAllPendingMedicine(patient).get(0) == medicine);
	}

}
