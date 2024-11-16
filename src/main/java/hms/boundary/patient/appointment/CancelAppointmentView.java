package hms.boundary.patient.appointment;

import hms.boundary.InputHandler;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.repository.DoctorRepository;

public class CancelAppointmentView extends AppointmentView {
	@Override
	public int displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		super.displayAppointments(patient, doctorRepository);
		System.out.println("Please choose an appointment to cancel: ");
		int i;
        try{
            i = InputHandler.getChoice(1, patient.getScheduledAppointmentList().size());
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return i-1;
	}

	@Override
	public void displayNoAppointments() {
		System.out.println("No appointments to cancel.");
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Cancel Appointment");
	}

//	public void displayDoctorsAll(DoctorRepository doctorRepository) {
//		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
//			Doctor doctor = doctorRepository.getAll().get(i);
//			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
//		}
//	}

	public void displayCancelConfirmation() {
		System.out.println("Appointment has been cancelled.");
	}
}
