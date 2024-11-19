package hms.boundary.patient.record;

import hms.boundary.View;
import hms.entity.user.Patient;

/**
 * The MedicalRecordView class displays the medical record of a given patient,
 * including personal details and appointment outcome records. It uses
 * AppointmentOutcomeRecordView to display the patient's appointment outcome
 * records.
 */
public class MedicalRecordView extends View {
	private Patient patient;
	private final AppointmentOutcomeRecordView appointmentOutcomeRecordView;

	/**
	 * Constructs a MedicalRecordView instance for the specified patient.
	 *
	 * @param patient the patient whose medical record is to be displayed.
	 */
	public MedicalRecordView(Patient patient) {
		this.patient = patient;
		this.appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();
	}

	/**
	 * Displays the medical record of the patient, including personal information
	 * and appointment outcome records.
	 */
	public void displayMedicalRecord() {
		displayBorderedText(WIDTH, "Medical Record");
		System.out.println(String.format("Patient Id: %s", this.patient.getId()));
		System.out.println(String.format("Name: %s", this.patient.getName()));
		System.out.println(String.format("Date of Birth: %s", this.patient.getDateOfBirth()));
		System.out.println(String.format("Gender: %s", this.patient.getGender()));
		System.out.println(String.format("Phone Number: %s", this.patient.getPhoneNumber()));
		System.out.println(String.format("Email Address: %s", this.patient.getEmailAddress()));
		System.out.println(String.format("Blood Type: %s", this.patient.getBloodType()));

		this.appointmentOutcomeRecordView.displayRecords(this.patient);
	}

	/**
	 * Displays the header for the "Medical Record" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Medical Record");
	}
}
