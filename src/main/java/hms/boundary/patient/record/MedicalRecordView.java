package hms.boundary.patient.record;

import hms.boundary.View;
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

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Medical Record");
	}
}
