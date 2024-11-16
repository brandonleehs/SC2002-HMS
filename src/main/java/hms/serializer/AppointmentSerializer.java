package hms.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.repository.DoctorRepository;
import hms.repository.PatientRepository;

public class AppointmentSerializer extends Serializer {
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;

	public AppointmentSerializer(String filepath, PatientRepository patientRepository,
			DoctorRepository doctorRepository) {
		super(filepath);
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

	public void serialize() {
		if (this.br == null) {
			return;
		}

		List<Appointment> appointmentList = new ArrayList<Appointment>();

		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				Appointment appointment = getAppointmentFromRow(row);
				Patient patient = patientRepository.getById(appointment.getPatientId());
				Doctor doctor = doctorRepository.getById(appointment.getDoctorId());
				if (appointment.getAppointmentStatus() == AppointmentStatus.PENDING) {
					patient.scheduleAppointment(doctor, appointment);
				} else if (appointment.getAppointmentStatus() == AppointmentStatus.COMPLETED
						|| appointment.getAppointmentStatus() == AppointmentStatus.CANCELLED) {
					patient.getAllAppointmentList().add(appointment);
				} else {
					patient.scheduleAppointment(doctor, appointment);
					doctor.acceptAppointment(appointment);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Appointment getAppointmentFromRow(String[] row) {
		UUID uuid = UUID.fromString(row[0]);
		String patientId = row[1];
		String doctorId = row[2];
		AppointmentStatus appointmentStatus = AppointmentStatus.fromString(row[3]);
		// Parse as LocalDate
		LocalDate date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(row[4]));
		LocalTime time = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(row[5]));
		return new Appointment(uuid, patientId, doctorId, appointmentStatus, date, time);
	}
}
