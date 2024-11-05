package hms.boundary.patient;

import hms.boundary.View;
import hms.entity.user.Doctor;
import hms.repository.DoctorRepository;

public class ScheduleAppointmentView extends View {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Schedule Appointment");
	}

//	public void displayDatePrompt() {
//		System.out.print("Please enter a date (YYYY-MM-DD): ");
//	}

//	public String getDateString() {
//		return scanner.nextLine();
//	}

//	public void displayTimePrompt() {
//		System.out.println("Note that the time must be in a half hour interval (e.g. 09:00, 09:30, etc).");
//		System.out.print("Please enter a time (HH:MM): ");
//	}

//	public String getTimeString() {
//		return scanner.nextLine();
//	}

//	public void displayDoctorPrompt() {
//		System.out.println("Please select from the following doctors:");
//	}

	public void displayDoctorsAll(DoctorRepository doctorRepository) {
		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
			Doctor doctor = doctorRepository.getAll().get(i);
			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
		}
	}

	public void displayScheduleSuccess() {
		System.out.println("Appointment scheduled.");
	}

	public void displayScheduleFailure() {
		System.out.println("Appointment unavailable. Please check availability again.");
	}
//	@Override
//	public void show() {
//		displayBorderedText(WIDTH, "Schedule Appointment");
//		System.out.print("Please enter a date (YYYY-MM-DD): ");
//		String input = scanner.nextLine();
//		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate date = LocalDate.parse(input, dateformatter);
//		System.out.println("Note that the time must be in a half hour interval (e.g. 09:00, 09:30, etc).");
//		System.out.print("Please enter a time (HH:MM): ");
//		input = scanner.nextLine();
//		LocalTime time = LocalTime.parse(input, DateTimeFormatter.ISO_LOCAL_TIME);
//		System.out.println("Please select from the following doctors:");
//		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
//			Doctor doctor = doctorRepository.getAll().get(i);
//			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
//		}
//		int choice = scanner.nextInt();
//		scanner.nextLine();
//		Doctor doctor = doctorRepository.getAll().get(choice - 1);
//		Appointment appointment = new Appointment(this.patient.getId(), doctor.getId(), date, time);
//		if (patient.scheduleAppointment(doctor, appointment)) {
//			System.out.println("Appointment scheduled.");
//		} else {
//			System.out.println("Appointment unavailable. Please check availability again.");
//		}
//	}

}
