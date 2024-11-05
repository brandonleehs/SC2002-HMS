package hms.boundary.patient;

import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.repository.DoctorRepository;

public class RescheduleAppointmentView extends View {
//	private Patient patient;
//
//	public RescheduleAppointmentView(Patient patient) {
//		this.patient = patient;
//	}

//	@Override
//	public void show() {
//		Appointment oldAppointment = displayAppointments();
//
//		System.out.print("Please enter a date (YYYY-MM-DD): ");
//		String input = scanner.nextLine();
//		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate date = LocalDate.parse(input, dateformatter);
//		System.out.println("Note that the time must be in a half hour interval (e.g. 09:00, 09:30, etc).");
//		System.out.print("Please enter a time (HH:MM): ");
//		input = scanner.nextLine();
//		LocalTime time = LocalTime.parse(input, DateTimeFormatter.ISO_LOCAL_TIME);
//
//		System.out.println("Please select from the following doctors:");
//		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
//			Doctor doctor = doctorRepository.getAll().get(i);
//			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
//		}
//		int choice = scanner.nextInt();
//		scanner.nextLine();
//		Doctor newDoctor = doctorRepository.getAll().get(choice - 1);
//		Appointment newAppointment = new Appointment(this.patient.getId(), newDoctor.getId(), date, time);
//		Doctor oldDoctor = doctorRepository.getById(oldAppointment.getDoctorId());
//		if (patient.rescheduleAppointment(oldDoctor, newDoctor, oldAppointment, newAppointment)) {
//			System.out.println("Appointment rescheduled.");
//		} else {
//			System.out.println("Appointment unavailable. Please check availability again.");
//		}
//	}

//	public Appointment displayAppointments() {
//		displayBorderedText(WIDTH, "Reschedule Appointment");
//		System.out.println("Please choose an appointment to reschedule: ");
//		String format = "| %-" + 10 + "s | %-" + 5 + "s | %-" + 6 + "s | %-" + (WIDTH - 34) + "s |\n";
//		System.out.printf(format, "Date", "Time", "Id", "Doctor Name");
//		for (int i = 0; i < this.patient.getScheduledAppointmentList().size(); i++) {
//			Appointment appointment = this.patient.getScheduledAppointmentList().get(i);
//			System.out.printf(format, appointment.getDate(), appointment.getTime(), appointment.getDoctorId(),
//					doctorRepository.getById(appointment.getDoctorId()).getName());
//		}
//		int choice = scanner.nextInt();
//		scanner.nextLine();
//		return this.patient.getScheduledAppointmentList().get(choice - 1);
//	}

	public void displayAppointments(Patient patient, DoctorRepository doctorRepository) {
		System.out.println("Please choose an appointment to reschedule: ");
		String format = "| %-" + 5 + "s | %-" + 10 + "s | %-" + 5 + "s | %-" + 6 + "s | %-" + (WIDTH - 42) + "s |\n";
		System.out.printf(format, "i", "Date", "Time", "Id", "Doctor Name");
		for (int i = 0; i < patient.getScheduledAppointmentList().size(); i++) {
			Appointment appointment = patient.getScheduledAppointmentList().get(i);
			System.out.printf(format, i + 1, appointment.getDate(), appointment.getTime(), appointment.getDoctorId(),
					doctorRepository.getById(appointment.getDoctorId()).getName());
		}
	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Reschedule Appointment");
	}

//	public void displayDatePrompt() {
//		System.out.print("Please enter a date (YYYY-MM-DD): ");
//	}
//
//	public void displayTimePrompt() {
//		System.out.println("Note that the time must be in a half hour interval (e.g. 09:00, 09:30, etc).");
//		System.out.print("Please enter a time (HH:MM): ");
//	}
//
//	public void displayDoctorPrompt() {
//		System.out.println("Please select from the following doctors:");
//	}

	public void displayDoctorsAll(DoctorRepository doctorRepository) {
		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
			Doctor doctor = doctorRepository.getAll().get(i);
			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
		}
	}

//	public String getDateString() {
//		return scanner.nextLine();
//	}
//
//	public String getTimeString() {
//		return scanner.nextLine();
//	}

	public void displayRescheduleSuccess() {
		System.out.println("Appointment rescheduled.");
	}

	public void displayRescheduleFailure() {
		System.out.println("Appointment unavailable. Please check availability again.");
	}

}
