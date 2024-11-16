package hms.boundary.doctor;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.repository.PatientRepository;

public class PendingRequestView extends View {

//	public Appointment displayFirstPendingAppointments(Schedule schedule, PatientRepository patientRepository) {
//		Map<LocalDate, Appointment[]> sm = schedule.getScheduleMap();
//		for (Map.Entry<LocalDate, Appointment[]> entry : sm.entrySet()) {
//			for (Appointment appt : entry.getValue()) {
//				if (appt != null) {
//					if (appt.getAppointmentStatus() == AppointmentStatus.PENDING) {
//						System.out.println("\n" + entry.getKey() + "\t" + appt.getTime() + ": "
//								+ (patientRepository.getById(appt.getPatientId())).getName() // patient name
//								+ "(" + appt.getPatientId() + ")");
//						System.out.print("Enter 1 to confirm and 0 to cancel: ");
//						return appt;
//					}
//				}
//			}
//		}
//		return new Appointment(null, null, null, null);
//	}

	public void displayPendingAppointments(Doctor doctor, PatientRepository patientRepository) {
		List<Appointment> pendingAppointmentList = doctor.getPendingAppointmentList();

		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 13 + "s | %-" + (WIDTH - 49) + "s |\n";
		System.out.printf(format, "Index", "Date", "Time", "Status", "Patient Name");

		for (int i = 0; i < pendingAppointmentList.size(); i++) {
			Appointment appointment = pendingAppointmentList.get(i);
			System.out.printf(format, i + 1, appointment.getDate().toString(), appointment.getTime().toString(),
					appointment.getAppointmentStatus().toString(),
					patientRepository.getById(appointment.getPatientId()).getName());
		}
	}

	public int displayOptions() {
		System.out.println("Please select an option:");
		System.out.println("1. Confirm");
		System.out.println("2. Cancel");
		int choice;
        try{
            choice = InputHandler.getChoice(1, 2);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return choice;
	}

	public int displayAppointmentPrompt(int size) {
		System.out.println("Please select an appointment index:");
		int i;
        try{
            i = InputHandler.getChoice(1, size);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				return -1;
		}
        return i-1;
	}

	public void displayNoPending() {
		System.out.println("No pending Appointment requests.");
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Pending Requests");
	}
}