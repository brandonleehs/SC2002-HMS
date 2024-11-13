package hms.boundary.patient.record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hms.boundary.View;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Patient;

public class MedicalRecordView extends View {
	private Patient patient;
	private final AppointmentOutcomeRecordView appointmentOutcomeRecordView;

	public MedicalRecordView(Patient patient) {
		this.patient = patient;
		this.appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();
	}

	public void displayMedicalRecord() {
		displayBorderedText(WIDTH, "Medical Record");
		System.out.println(String.format("Patient Id: %s", this.patient.getId()));
		System.out.println(String.format("Name: %s", this.patient.getName()));
		System.out.println(String.format("Date of Birth: %s", this.patient.getDateOfBirth()));
		System.out.println(String.format("Gender: %s", this.patient.getGender()));
		System.out.println(String.format("Phone Number: %s", this.patient.getPhoneNumber()));
		System.out.println(String.format("Email Address: %s", this.patient.getEmailAddress()));
		System.out.println(String.format("Blood Type: %s", this.patient.getBloodType()));

		displayBorderedText(WIDTH, "Appointment Records");
		this.appointmentOutcomeRecordView.displayRecords(this.patient);
	
	}

	public void displayAppointmentOutcomeRecord(){
		List<AppointmentOutcomeRecord> records = this.patient.getAppointmentOutcomeRecordList();
		if (records.isEmpty()) {
			System.out.println("No records found.");
		}
		int index = 1;
		for (AppointmentOutcomeRecord appointmentOutcomeRecord : records) {
			System.out.println(String.format("Date: %s", appointmentOutcomeRecord.getDate()));
			System.out.println(String.format("Service Type: %s", appointmentOutcomeRecord.getServiceType()));
			System.out.println(String.format("Diagnosis: %s", appointmentOutcomeRecord.getConsultationNotes()));
			System.out.println("+" + "=".repeat(WIDTH - 2) + "+");
			System.out.println("Appointment Record: "+index++);
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
		// String format = "| %-" + (WIDTH - 25) + "s | %-" + 18 + "s |\n";
		String format = "| %-" + (WIDTH - 31) + "s | %-" + 10 + "s | %-" + 10 + "s |\n";


		System.out.println("-".repeat(WIDTH));
		System.out.println(" ".repeat(leftPadding) + title + " ".repeat(rightPadding));
		System.out.println("-".repeat(WIDTH));

		HashMap<Medicine, Integer> prescribedMedicineList = appointmentOutcomeRecord.getPrescribedMedicineList();
		if (prescribedMedicineList.isEmpty()) {
			System.out.println("No medicine prescribed.");
		} else {
			System.out.printf(format, "Medicine Name", "Amount", "Status");
			System.out.println("|" + "-".repeat(WIDTH - 29) + "|" + "-".repeat(12) + "|"+"-".repeat(13) + "|");
			for (Map.Entry<Medicine, Integer> entry : prescribedMedicineList.entrySet()) {
				
				System.out.printf(format, entry.getKey().getName(), entry.getValue(), entry.getKey().getMedicineStatus());
			}
		}
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Medical Record");
	}

}
