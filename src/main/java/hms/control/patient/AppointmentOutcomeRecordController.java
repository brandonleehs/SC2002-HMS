package hms.control.patient;

import hms.boundary.patient.record.AppointmentOutcomeRecordView;
import hms.control.Controller;
import hms.entity.user.Patient;

/**
 * Controller class for displaying the outcome records of a patient's appointments.
 * Facilitates the process of retrieving and displaying records related to the outcomes of appointments.
 */
public class AppointmentOutcomeRecordController extends Controller {
	private Patient patient;
	private AppointmentOutcomeRecordView appointmentOutcomeRecordView;

	/**
	 * Constructs a new AppointmentOutcomeRecordController with the specified patient.
	 *
	 * @param patient the patient whose appointment outcome records are to be displayed
	 */
	public AppointmentOutcomeRecordController(Patient patient) {
		this.patient = patient;
		this.appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();
	}

	/**
	 * Retrieves and displays the appointment outcome records for the specified patient.
	 * The records are presented using the appointment outcome record view.
	 */
	@Override
	public void navigate() {
		this.appointmentOutcomeRecordView.displayRecords(patient);
	}

}
