package hms.boundary.administrator;

import java.util.List;

import hms.boundary.patient.appointment.AppointmentView;
import hms.boundary.patient.record.AppointmentOutcomeRecordView;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Doctor;
import hms.repository.DoctorRepository;

/**
 * The view class for displaying appointments. It handles the display of
 * appointment details for all types of appointments and handles both the
 * completed and non-completed appointments.
 */
public class ViewAppointmentsView extends AppointmentView {

	/**
	 * Displays the header for the "All Appointments" section.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "All Appointments");
	}

	/**
	 * Displays a message indicating that there are no appointments for the
	 * specified status.
	 * 
	 * @param status The specified appointment status (e.g., Pending, Confirmed,
	 *               Cancelled, Completed).
	 */
	public void displayNoAppointmentsType(AppointmentStatus status) {
		System.out.println("No " + status + " appointments scheduled.");
	}

	/**
	 * Displays a list of appointments of a specific type (e.g., completed or
	 * scheduled). It shows appointment details such as the date, time, doctor, and
	 * any associated outcome record for completed appointments. If there are no
	 * appointments, it displays a relevant message.
	 * 
	 * @param appointments     The list of appointments to display.
	 * @param doctorRepository The repository containing doctor data for retrieving
	 *                         doctor information.
	 * @param status           The status of the appointments to display (e.g.,
	 *                         completed, scheduled).
	 */
	public void displayAppointmentsType(List<Appointment> appointments, DoctorRepository doctorRepository,
			AppointmentStatus status) {
		System.out.println(status + " appointments:");
		if (appointments == null) {
			displayNoAppointmentsType(status);
			return;
		}

		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 13 + "s | %-" + (WIDTH - 49) + "s |\n";

		if (status == AppointmentStatus.COMPLETED) {
			AppointmentOutcomeRecordView appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();
			System.out.printf(format, "Index", "Date", "Time", "Doctor ID", "Doctor Name");
			for (int i = 0; i < appointments.size(); i++) {
				Appointment appointment = appointments.get(i);
				Doctor doctor = doctorRepository.getById(appointment.getDoctorId());
				if (doctor == null) {
					System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(),
							appointment.getDoctorId(), "[FIRED DOCTOR]");
				} else {
					System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(),
							appointment.getDoctorId(), doctorRepository.getById(appointment.getDoctorId()).getName());
				}
				AppointmentOutcomeRecord appointmentOutcomeRecord = appointment.getAppointmentOutcomeRecord();
				System.out.println(String.format("Service Type: %s", appointmentOutcomeRecord.getServiceType()));
				System.out.println(String.format("Diagnosis: %s", appointmentOutcomeRecord.getConsultationNotes()));
				appointmentOutcomeRecordView.displayPrescriptionTable(appointmentOutcomeRecord);
			}
			return;
		}

		System.out.printf(format, "Index", "Date", "Time", "Doctor ID", "Doctor Name");
		for (int i = 0; i < appointments.size(); i++) {
			Appointment appointment = appointments.get(i);
			Doctor doctor = doctorRepository.getById(appointment.getDoctorId());
			if (doctor == null) {
				System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(),
						appointment.getDoctorId(), "[FIRED DOCTOR]");
			} else {
				System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(), doctor.getId(),
						doctor.getName());
			}
		}
		System.out.println();
	}

}