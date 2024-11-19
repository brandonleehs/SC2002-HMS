package hms.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.repository.DoctorRepository;
import hms.repository.PatientRepository;

/**
 * The AppointmentSerializer class is responsible for deserializing appointment data from a CSV file
 * and processing it. It associates each appointment with the relevant patient and doctor, and schedules
 * or updates the appointment status based on the data.
 */
public class AppointmentSerializer extends Serializer {
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;

	/**
     * Constructs an AppointmentSerializer with the specified file path and repositories.
     * This constructor initializes the serializer to read from the given file and uses the provided
     * repositories for patient and doctor data.
     * 
     * @param filepath the path to the CSV file containing appointment data
     * @param patientRepository the repository for retrieving patient data
     * @param doctorRepository the repository for retrieving doctor data
     */
	public AppointmentSerializer(String filepath, PatientRepository patientRepository,
			DoctorRepository doctorRepository) {
		super(filepath);
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

	/**
     * Deserializes the appointment data from the CSV file and processes each record.
     * For each record, an appointment is created, and its status is checked. The appointment is then
     * associated with the relevant patient and doctor, and scheduled or updated accordingly.
     */
	public void serialize() {
		if (this.br == null) {
			return;
		}

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
			e.printStackTrace();
		}
	}

	/**
     * Converts a CSV row into an Appointment object.
     * This method parses the appointment details from the row, including the unique identifier, patient ID,
     * doctor ID, appointment status, date, and time.
     * 
     * @param row the CSV row containing the appointment data
     * @return an Appointment object representing the data from the row
     */
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
