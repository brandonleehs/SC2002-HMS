package hms.boundary.patient.appointment;

import hms.entity.user.Patient;
import hms.repository.DoctorRepository;

public class RescheduleAppointmentView extends AppointmentView {

	@Override
	public void displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		super.displayAppointments(patient, doctorRepository);
		System.out.println("Please choose an appointment to reschedule: ");
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Reschedule Appointment");
	}

//	public void displayDoctorsAll(DoctorRepository doctorRepository) {
//		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
//			Doctor doctor = doctorRepository.getAll().get(i);
//			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
//		}
//	}

	public void displayRescheduleSuccess() {
		System.out.println("Appointment rescheduled.");
	}

	public void displayRescheduleFailure() {
		System.out.println("Appointment unavailable. Please check availability again.");
	}

	@Override
	public void displayNoAppointments() {
		System.out.println("No appointments to reschedule.");
	}

}
