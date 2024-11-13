package hms.boundary.patient.appointment;

import hms.entity.user.Patient;
import hms.repository.DoctorRepository;

public class CancelAppointmentView extends AppointmentView {
	@Override
	public void displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		super.displayAppointments(patient, doctorRepository);
		System.out.println("Please choose an appointment to cancel: ");
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
