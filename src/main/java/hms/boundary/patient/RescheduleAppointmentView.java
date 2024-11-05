package hms.boundary.patient;

import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.repository.DoctorRepository;

public class RescheduleAppointmentView extends View {

	public void displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		System.out.println("Please choose an appointment to reschedule: ");
		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 10 + "s | %-" + (WIDTH - 46) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Doctor Name");
		for (int i = 0; i < patient.getScheduledAppointmentList().size(); i++) {
			Appointment appointment = patient.getScheduledAppointmentList().get(i);
			System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(),
					appointment.getAppointmentStatus(), doctorRepository.getById(appointment.getDoctorId()).getName());
		}
		System.out.println();
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Reschedule Appointment");
	}

	public void displayDoctorsAll(DoctorRepository doctorRepository) {
		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
			Doctor doctor = doctorRepository.getAll().get(i);
			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
		}
	}

	public void displayRescheduleSuccess() {
		System.out.println("Appointment rescheduled.");
	}

	public void displayRescheduleFailure() {
		System.out.println("Appointment unavailable. Please check availability again.");
	}

}
