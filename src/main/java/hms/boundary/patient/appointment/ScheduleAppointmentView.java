package hms.boundary.patient.appointment;

public class ScheduleAppointmentView extends AppointmentView {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Schedule Appointment");
	}

	public void displayScheduleSuccess() {
		System.out.println("Appointment scheduled.");
	}

	public void displayScheduleFailure() {
		System.out.println("Appointment unavailable. Please check availability again.");
	}
}
