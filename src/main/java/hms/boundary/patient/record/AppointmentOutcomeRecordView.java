package hms.boundary.patient.record;

import java.util.List;

import hms.boundary.View;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Patient;

public class AppointmentOutcomeRecordView extends View {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Appointment Records");
	}

	public void displayRecords(Patient patient) {
		List<AppointmentOutcomeRecord> records = patient.getAppointmentOutcomeRecordList();
		if (records.isEmpty()) {
			System.out.println("No records found.");
		}
		for (int i = 0; i < records.size(); i++) {
			AppointmentOutcomeRecord appointmentOutcomeRecord = records.get(i);
			displayAppointmentHeader(i);
			System.out.println(String.format("UUID: %s", appointmentOutcomeRecord.getUUID().toString()));
			System.out.println(String.format("Date: %s", appointmentOutcomeRecord.getDate()));
			System.out.println(String.format("Service Type: %s", appointmentOutcomeRecord.getServiceType()));
			System.out.println(String.format("Diagnosis: %s", appointmentOutcomeRecord.getConsultationNotes()));

			displayPrescriptionTable(appointmentOutcomeRecord);
		}
//		for (AppointmentOutcomeRecord appointmentOutcomeRecord : records) {
//			System.out.println(String.format("UUID: %s", appointmentOutcomeRecord.getUUID().toString()));
//			System.out.println(String.format("Date: %s", appointmentOutcomeRecord.getDate()));
//			System.out.println(String.format("Service Type: %s", appointmentOutcomeRecord.getServiceType()));
//			System.out.println(String.format("Diagnosis: %s", appointmentOutcomeRecord.getConsultationNotes()));
//
//			displayPrescriptionTable(appointmentOutcomeRecord);
//		}
	}

	private void displayAppointmentHeader(int i) {
		String text = String.format("Appointment %d", i + 1);
		int leftPadding = (WIDTH - text.length() - 2) / 2;
		int rightPadding = leftPadding;
		if ((WIDTH - text.length()) % 2 != 0) {
			rightPadding++;
		}

		System.out.println("-".repeat(WIDTH));
		System.out.println("|" + " ".repeat(leftPadding) + text + " ".repeat(rightPadding) + "|");
		System.out.println("-".repeat(WIDTH));
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

}
