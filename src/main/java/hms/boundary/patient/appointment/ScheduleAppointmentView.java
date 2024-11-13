package hms.boundary.patient.appointment;

public class ScheduleAppointmentView extends AppointmentView {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Schedule Appointment");
	}

//	public void displayDoctorsAll(DoctorRepository doctorRepository) {
//		for (int i = 0; i < doctorRepository.getAll().size(); i++) {
//			Doctor doctor = doctorRepository.getAll().get(i);
//			System.out.println(String.format("%d. Dr. %s (%s)", i + 1, doctor.getName(), doctor.getId()));
//		}
//	}

	public void displayScheduleSuccess() {
		System.out.println("Appointment scheduled.");
	}

	public void displayScheduleFailure() {
		System.out.println("Appointment unavailable. Please check availability again.");
	}
}
