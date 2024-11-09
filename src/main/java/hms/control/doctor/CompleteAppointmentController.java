package hms.control.doctor;

import hms.boundary.InputHandler;
import hms.boundary.doctor.CompleteAppointmentView;
import hms.boundary.doctor.ScheduleView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class CompleteAppointmentController extends Controller {
	private ScheduleView scheduleView;
	private CompleteAppointmentView completeAppointmentView;
	private Doctor doctor;

	public CompleteAppointmentController(Doctor doctor) {
		this.scheduleView = new ScheduleView();
		this.completeAppointmentView = new CompleteAppointmentView();
		this.doctor = doctor;
	}

	public void navigate() {
		completeAppointmentView.displayHeader();

		if (doctor.getConfirmedAppointmentList().isEmpty()) {
			completeAppointmentView.displayNoAppointments();
			return;
		}
		scheduleView.displayUpcomingAppointments(doctor, patientRepository);
		completeAppointmentView.displayApptOptionPrompt();

		int choice = 0;
		try {
			choice = InputHandler.getChoice(1, doctor.getConfirmedAppointmentList().size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return;
		}

		Appointment appointment = doctor.getConfirmedAppointmentList().get(choice - 1);

		completeAppointmentView.displayServiceTypePrompt();
		String serviceType = InputHandler.getString();
		completeAppointmentView.displaySetConsultationNotesPrompt();
		String consultationNotes = InputHandler.getString();

		doctor.completeAppointment(patientRepository.getById(appointment.getPatientId()), appointment, serviceType,
				consultationNotes);
//		Prompt.displayDatePrompt();
//		LocalDate date;
//		try {
//			date = InputHandler.getDate();
//		} catch (InvalidDateException e) {
//			return;
//		}
//
//		Schedule schedule = doctor.getSchedule();
//		Appointment[] appointmentArr = (schedule.getScheduleMap()).get(date);
//		int count = 0;
//		for (Appointment appt : appointmentArr) {
//			if (appt == null) {
//				continue;
//			}
//			if (appt.getAppointmentStatus() == AppointmentStatus.CONFIRMED) {
//				count++;
//			}
//		}
//		if (count == 0) {
//			completeAppointmentView.displayNoAppointments(date);
//			return;
//		}
//		scheduleView.displayAppointmentByDate(schedule, patientRepository, date);
//
//		completeAppointmentView.displayAppointmentChoice();
//		LocalTime startTime;
//		try {
//			startTime = InputHandler.getTime();
//		} catch (InvalidTimeException e) {
//			return;
//		}
//		Appointment appointment = appointmentArr[Schedule.getTimeslot(startTime)];
//		Patient patient = patientRepository.getById(appointment.getPatientId());
//
//		completeAppointmentView.displayServiceTypePrompt();
//		String service = InputHandler.getString();
//		completeAppointmentView.displayNotesPrompt();
//		String notes = InputHandler.getString();
//
//		AppointmentOutcomeRecord appointmentRecord = doctor.completeAppointment(patient, appointment, service, notes);
//
//		completeAppointmentView.displayPrescriptionChoicePrompt();
//		int numPre = -1;
//		while (numPre == -1) {
//			try {
//				numPre = InputHandler.getChoice(0, 10);
//			} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
//				continue;
//			}
//		}
//
//		for (int i = 0; i < numPre; i++) {
//			completeAppointmentView.displayAddPrescriptionNamePrompt();
//			String medName = InputHandler.getString();
//			completeAppointmentView.displayAddPrescriptionQtyPrompt();
//			int medQty = -1;
//			while (medQty == -1) {
//				try {
//					medQty = InputHandler.getChoice(0, 100);
//				} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
//					continue;
//				}
//			}
//			appointmentRecord.addPrescribedMedicine(new Medicine(medName, medQty));
//		}
	}
}