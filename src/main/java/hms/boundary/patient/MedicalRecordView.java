package hms.boundary.patient;

import java.util.List;

import hms.boundary.View;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Patient;

public class MedicalRecordView extends View {
	private Patient patient;

	public MedicalRecordView(Patient patient) {
		this.patient = patient;
	}

	public void displayMedicalRecord() {
		displayBorderedText(WIDTH, "Medical Record");
		System.out.println(String.format("Patient Id: %s", patient.getId()));
		System.out.println(String.format("Name: %s", patient.getName()));
		System.out.println(String.format("Date of Birth: %s", patient.getDateOfBirth()));
		System.out.println(String.format("Gender: %s", patient.getGender()));
		System.out.println(String.format("Phone Number: %s", patient.getPhoneNumber()));
		System.out.println(String.format("Email Address: %s", patient.getEmailAddress()));
		System.out.println(String.format("Blood Type: %s", patient.getBloodType()));

		displayBorderedText(WIDTH, "Appointment Records");
		List<AppointmentOutcomeRecord> records = patient.getAppointmentOutcomeRecordList();
		if (records.isEmpty()) {
			System.out.println("No records found.");
		}
		for (AppointmentOutcomeRecord appointmentOutcomeRecord : records) {
			System.out.println(String.format("Date: %s", appointmentOutcomeRecord.getDate()));
			System.out.println(String.format("Service Type: %s", appointmentOutcomeRecord.getServiceType()));
			System.out.println(String.format("Diagnosis: %s", appointmentOutcomeRecord.getConsultationNotes()));

			displayPrescriptionTable(appointmentOutcomeRecord);
		}
	}

	private void displayPrescriptionTable(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		String title = "Medicine Prescribed";
		int leftPadding = (WIDTH - title.length()) / 2;
		int rightPadding = leftPadding;
		if ((WIDTH - title.length()) % 2 != 0) {
			rightPadding++;
		}
		String format = "| %-" + (WIDTH - 25) + "s | %-" + 18 + "s |\n";

		System.out.println("-".repeat(WIDTH));
		System.out.println(" ".repeat(leftPadding) + title + " ".repeat(rightPadding));
		System.out.println("-".repeat(WIDTH));

		List<Medicine> prescribedMedicineList = appointmentOutcomeRecord.getPrescribedMedicineList();
		if (prescribedMedicineList.isEmpty()) {
			System.out.println("No medicine prescribed.");
		} else {
			System.out.printf(format, "Medicine Name", "Status");
			System.out.println("|" + "-".repeat(WIDTH - 2) + "|");
			for (Medicine medicine : prescribedMedicineList) {
				System.out.printf(format, medicine.getName(), medicine.getMedicineStatus());
			}
		}
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Medical Record");
	}

}
