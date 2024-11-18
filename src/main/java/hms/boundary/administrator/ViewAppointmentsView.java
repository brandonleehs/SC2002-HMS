package hms.boundary.administrator;

import java.util.List;

import hms.boundary.patient.appointment.AppointmentView;
import hms.boundary.patient.record.AppointmentOutcomeRecordView;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Doctor;
import hms.repository.DoctorRepository;

public class ViewAppointmentsView extends AppointmentView {

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "All Appointments");
	}

	public void displayNoAppointmentsType(AppointmentStatus status) {
		System.out.println("No " + status + " appointments scheduled.");
	}

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