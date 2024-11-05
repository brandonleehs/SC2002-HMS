package hms.boundary.patient;

import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.user.Patient;
import hms.repository.DoctorRepository;

public class AppointmentView extends View {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Scheduled Appointment");
	}

	public void displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 10 + "s | %-" + (WIDTH - 46) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Doctor Name");
		for (int i = 0; i < patient.getScheduledAppointmentList().size(); i++) {
			Appointment appointment = patient.getScheduledAppointmentList().get(i);
			System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(),
					appointment.getAppointmentStatus(), doctorRepository.getById(appointment.getDoctorId()).getName());
		}
		System.out.println();
	}
}
