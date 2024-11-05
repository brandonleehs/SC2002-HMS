package hms.control;

import hms.boundary.InputHandler;
import hms.boundary.patient.CancelAppointmentView;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

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
			int choice = 0;
			try {
				choice = InputHandler.getChoice(1, this.patient.getScheduledAppointmentList().size());
			} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return;
			}

			Appointment appointment = this.patient.getScheduledAppointmentList().get(choice - 1);

			Doctor doctor = doctorRepository.getById(appointment.getDoctorId());
			patient.cancelAppointment(doctor, appointment);
			this.cancelAppointmentView.displayCancelConfirmation();
		}
	}

}
