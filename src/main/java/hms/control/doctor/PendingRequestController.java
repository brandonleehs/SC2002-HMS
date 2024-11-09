package hms.control.doctor;

import hms.boundary.InputHandler;
import hms.boundary.doctor.PendingRequestView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

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
		pendingRequestView.displayAppointmentPrompt();

		int choice = 0;
		try {
			choice = InputHandler.getChoice(1, this.doctor.getPendingAppointmentList().size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return;
		}

		Appointment appointment = this.doctor.getPendingAppointmentList().get(choice - 1);
		pendingRequestView.displayOptions();

		try {
			choice = InputHandler.getChoice(1, 2);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return;
		}

		switch (choice) {
		case 1:
			this.doctor.acceptAppointment(appointment);
			break;
		case 2:
			this.doctor.cancelAppointment(appointment);
			break;
		default:

		}

//		while (appointment.getPatientId() != null) {
//			try {
//				choice = InputHandler.getChoice(0, 1);
//			} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
//				// Continue loop if invalid choice
//				choice = -1;
//				break;
//			}
//			switch (choice) {
//			case 0:
//				appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
//				break;
//			case 1:
//				appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
//			default:
//
//				schedule = doctor.getSchedule();
//				appointment = pendingRequestView.displayFirstPendingAppointments(schedule, patientRepository);
//			}
//		}
//		System.out.println("No more pending Appointments.");
	}
}