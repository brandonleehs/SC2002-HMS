package hms.control.doctor;

import hms.boundary.doctor.PendingRequestView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;

/**
 * Controller for managing pending appointment requests for a doctor.
 * This class allows a doctor to view, accept, or cancel pending appointment requests from patients.
 */
public class PendingRequestController extends Controller {
	PendingRequestView pendingRequestView;
	private Doctor doctor;

	/**
	 * Constructs a new instance of PendingRequestController for the specified doctor.
	 * Initializes the PendingRequestView and assigns the given doctor.
	 *
	 * @param doctor The doctor who will manage pending appointment requests.
	 */
	public PendingRequestController(Doctor doctor) {
		this.pendingRequestView = new PendingRequestView();
		this.doctor = doctor;
	}

	/**
	 * Navigates through the pending appointment requests and allows the doctor to
	 * accept or cancel appointments.
	 * <p>
	 * If there are no pending appointments, a message is displayed and the method exits.
	 * If there are pending appointments, the doctor can choose to accept or cancel an appointment
	 * after selecting from the list.
	 */
	@Override
	public void navigate() {
		if (this.doctor.getPendingAppointmentList().isEmpty()) {
			pendingRequestView.displayNoPending();
			return;
		}
		pendingRequestView.displayPendingAppointments(this.doctor, patientRepository);
		int choice = pendingRequestView.displayAppointmentPrompt(this.doctor.getPendingAppointmentList().size());
		if (choice == -1)
			return;

		Appointment appointment = this.doctor.getPendingAppointmentList().get(choice);
		choice = pendingRequestView.displayOptions();

		switch (choice) {
		case 1:
			this.doctor.acceptAppointment(appointment);
			break;
		case 2:
			this.doctor.cancelAppointment(appointment);
			break;
		default:

		}
	}
}