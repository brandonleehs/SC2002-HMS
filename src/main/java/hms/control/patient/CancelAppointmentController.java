package hms.control.patient;

import hms.boundary.patient.appointment.CancelAppointmentView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;

public class CancelAppointmentController extends Controller {
	private CancelAppointmentView cancelAppointmentView;
	private Patient patient;

	public CancelAppointmentController(Patient patient) {
		this.patient = patient;
		this.cancelAppointmentView = new CancelAppointmentView();
	}

	@Override
	public void navigate() {
		this.cancelAppointmentView.displayHeader();
		if (patient.getScheduledAppointmentList().isEmpty()) {
			this.cancelAppointmentView.displayNoAppointments();
		} else {
			int choice = this.cancelAppointmentView.displayAppointments(patient, doctorRepository);
			if (choice == -1) return;

			Appointment appointment = this.patient.getScheduledAppointmentList().get(choice);

			Doctor doctor = doctorRepository.getById(appointment.getDoctorId());
			patient.cancelAppointment(doctor, appointment);
			this.cancelAppointmentView.displayCancelConfirmation();
		}
	}

}
