package hms.control;

import hms.boundary.ErrorMessage;
import hms.boundary.InputHandler;
import hms.boundary.patient.CancelAppointmentView;
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
			this.cancelAppointmentView.displayAppointments(patient, doctorRepository);
			Integer choice = InputHandler.getChoice();
			if (choice == null) {
				return;
			}
			if (!(1 <= choice && choice <= this.patient.getScheduledAppointmentList().size())) {
				ErrorMessage.displayInvalidChoiceError();
				return;
			}
			Appointment appointment = this.patient.getScheduledAppointmentList().get(choice - 1);

			Doctor doctor = doctorRepository.getById(appointment.getDoctorId());
			patient.cancelAppointment(doctor, appointment);
			this.cancelAppointmentView.displayCancelConfirmation();
		}
	}

}
