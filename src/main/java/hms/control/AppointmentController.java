package hms.control;

import hms.boundary.patient.AppointmentView;
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
		this.appointmentView.displayAppointments(patient, doctorRepository);
	}

}