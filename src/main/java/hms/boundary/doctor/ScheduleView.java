package hms.boundary.doctor;

import java.time.LocalDate;

import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.repository.PatientRepository;

/**
 * The ScheduleView class is responsible for displaying a doctor's schedule,
 * including their appointments, both confirmed and scheduled on a specific
 * date. It provides methods for displaying appointment details and checking
 * whether appointments exist on a given date.
 */
public class ScheduleView extends View {

	/**
	 * Displays the header for the schedule view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Schedule");
	}

	/**
	 * Displays the appointments for a specific doctor on a given date. If no
	 * appointments exist for the given date, a message indicating that no
	 * appointments are scheduled is shown.
	 * 
	 * @param doctor            the doctor whose appointments are being displayed.
	 * @param date              the date for which the appointments are being
	 *                          displayed.
	 * @param patientRepository the repository that provides access to patient
	 *                          information.
	 */
	public void displayAppointments(Doctor doctor, LocalDate date, PatientRepository patientRepository) {
		displayBorderedText(WIDTH, "Schedule");
		System.out.println();

		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 13 + "s | %-" + (WIDTH - 49) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Patient Name");
		Appointment[] appointmentArr = doctor.getSchedule().getScheduleMap().get(date);

		if (IfNoAppointments(appointmentArr)) {
			displayNoAppointments();
			return;
		}

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

	private boolean IfNoAppointments(Appointment[] appointmentArr) {
		if (appointmentArr == null) {
			return true;
		}

		int count = 0;
		for (int i = 0; i < appointmentArr.length; i++) {
			if (appointmentArr[i] == null) {
				count++;
			}
		}
		return count == appointmentArr.length;
	}

	private void displayNoAppointments() {
		System.out.println("No appointments scheduled on this date.");
	}

	/**
	 * Displays a list of upcoming confirmed appointments for the doctor. If there
	 * are no upcoming appointments, a message is shown indicating that there are no
	 * upcoming appointments.
	 * 
	 * @param doctor            the doctor whose upcoming confirmed appointments are
	 *                          being displayed.
	 * @param patientRepository the repository that provides access to patient
	 *                          information.
	 */
	public void displayUpcomingAppointments(Doctor doctor, PatientRepository patientRepository) {
		if (doctor.getConfirmedAppointmentList().isEmpty()) {
			System.out.println("No upcoming appointments.");
			return;
		}
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
