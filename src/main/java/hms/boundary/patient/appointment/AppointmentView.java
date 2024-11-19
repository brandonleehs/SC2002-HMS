package hms.boundary.patient.appointment;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.repository.DoctorRepository;

/**
 * The AppointmentView class handles the user interface for viewing and
 * interacting with appointments for patients. It provides functionality to
 * display scheduled appointments, list all doctors, and manage related user
 * inputs.
 */
public class AppointmentView extends View {
	/**
	 * Displays the header for the "Scheduled Appointment" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Scheduled Appointment");
	}

	/**
	 * Displays a list of appointments scheduled by a given patient, along with
	 * associated doctor details.
	 *
	 * @param patient          the patient whose appointments are to be displayed.
	 * @param doctorRepository the repository to fetch doctor information.
	 * @return 0
	 */
	public int displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 13 + "s | %-" + (WIDTH - 49) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Doctor Name");
		for (int i = 0; i < patient.getScheduledAppointmentList().size(); i++) {
			Appointment appointment = patient.getScheduledAppointmentList().get(i);
			System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(),
					appointment.getAppointmentStatus(), doctorRepository.getById(appointment.getDoctorId()).getName());
		}
		System.out.println();
		return 0;
	}

	/**
	 * Displays a list of all doctors available in the system and allows the user to
	 * select one by index.
	 *
	 * @param doctorRepository the repository to fetch the list of doctors.
	 * @return the index of the selected doctor (0-based), or -1 if an invalid
	 *         choice is made.
	 */
	public int displayDoctorsAll(DoctorRepository doctorRepository) {
		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
			Doctor doctor = doctorRepository.getAll().get(i);
			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
		}
		int choice;
		try {
			choice = InputHandler.getChoice(1, doctorRepository.getAll().size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice - 1;
	}

	/**
	 * Displays a message indicating that no appointments are currently scheduled
	 * for the patient.
	 */
	public void displayNoAppointments() {
		System.out.println("No appointments scheduled.");
	}
}
