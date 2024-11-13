package hms.control.patient;

import hms.boundary.patient.appointment.AppointmentView;
import hms.control.Controller;
import hms.entity.user.Patient;

public class AppointmentController extends Controller {
	private Patient patient;
	private AppointmentView appointmentView;

	public AppointmentController(Patient patient) {
		this.patient = patient;
		this.appointmentView = new AppointmentView();
	}

	@Override
	public void navigate() {
		this.appointmentView.displayHeader();
		if (patient.getAllAppointmentList().isEmpty()) {
			this.appointmentView.displayNoAppointments();
		} else {
			this.appointmentView.displayAppointments(patient, doctorRepository);
		}
	}

}
