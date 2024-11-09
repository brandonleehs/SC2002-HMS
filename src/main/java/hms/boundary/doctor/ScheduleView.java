package hms.boundary.doctor;

import java.time.LocalDate;

import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.repository.PatientRepository;

public class ScheduleView extends View {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Schedule");
	}

	public void displayAppointments(Doctor doctor, LocalDate date, PatientRepository patientRepository) {
		displayBorderedText(WIDTH, "Schedule");
		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 13 + "s | %-" + (WIDTH - 49) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Patient Name");
		Appointment[] appointmentArr = doctor.getSchedule().getScheduleMap().get(date);

		int index = 0;
		for (int i = 0; i < appointmentArr.length; i++) {
			Appointment appointment = appointmentArr[i];
			if (appointment != null) {
				index++;
				System.out.printf(format, index, appointment.getDate(), appointment.getTime(),
						appointment.getAppointmentStatus(),
						patientRepository.getById(appointment.getPatientId()).getName());
			}
		}
		System.out.println();
	}

	public void displayUpcomingAppointments(Doctor doctor, PatientRepository patientRepository) {
		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 13 + "s | %-" + (WIDTH - 49) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Patient Name");

		for (int i = 0; i < doctor.getConfirmedAppointmentList().size(); i++) {
			Appointment appointment = doctor.getConfirmedAppointmentList().get(i);
			System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(),
					appointment.getAppointmentStatus(),
					patientRepository.getById(appointment.getPatientId()).getName());
		}

		System.out.println();
	}

}
