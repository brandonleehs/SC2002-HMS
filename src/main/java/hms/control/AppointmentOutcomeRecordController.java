package hms.control;

import hms.boundary.patient.record.AppointmentOutcomeRecordView;
import hms.entity.user.Patient;

public class AppointmentOutcomeRecordController extends Controller {
	private Patient patient;
	private AppointmentOutcomeRecordView appointmentOutcomeRecordView;

	public AppointmentOutcomeRecordController(Patient patient) {
		this.patient = patient;
		this.appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();
	}

	@Override
	public void navigate() {
		this.appointmentOutcomeRecordView.displayHeader();
		this.appointmentOutcomeRecordView.displayRecords(patient);
	}

}
