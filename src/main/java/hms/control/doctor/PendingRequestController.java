package hms.control.doctor;

import hms.boundary.doctor.PendingRequestView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;

public class PendingRequestController extends Controller {
	PendingRequestView pendingRequestView;
	private Doctor doctor;

	public PendingRequestController(Doctor doctor) {
		this.pendingRequestView = new PendingRequestView();
		this.doctor = doctor;
	}

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