package hms.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hms.entity.appointment.Appointment;
import hms.entity.user.Patient;
import hms.serializer.AppointmentSerializer;

/**
 * This class represents a repository for managing {@link Appointment} objects.
 * It is responsible for serializing and deserializing appointment data to and
 * from a CSV file. It interacts with the {@link PatientRepository} to retrieve
 * appointments for all patients.
 */
public class AppointmentRepository {
	private final PatientRepository patientRepository;
	private final static String FILEPATH = "./src/main/resources/Appointment_List.csv";

	/**
	 * Constructs a new {@code AppointmentRepository}. This constructor initializes
	 * the repository by serializing the appointment data to a CSV file using the
	 * provided {@link PatientRepository} and {@link DoctorRepository}.
	 *
	 * @param patientRepository the {@link PatientRepository} used to access patient
	 *                          data
	 * @param doctorRepository  the {@link DoctorRepository} used to access doctor
	 *                          data
	 */
	public AppointmentRepository(PatientRepository patientRepository, DoctorRepository doctorRepository) {
		this.patientRepository = patientRepository;
		AppointmentSerializer appointmentSerializer = new AppointmentSerializer(FILEPATH, patientRepository,
				doctorRepository);
		appointmentSerializer.serialize();
	}

	/**
	 * Retrieves all the appointments for all patients.
	 *
	 * @return a list of {@link Appointment} objects for all patients
	 */
	public List<Appointment> getAll() {
		List<Appointment> appointmentList = new ArrayList<Appointment>();

		for (Patient patient : patientRepository.getAll()) {
			appointmentList.addAll(patient.getAllAppointmentList());
		}
		return appointmentList;
	}

	/**
	 * Retrieves an appointment by its unique identifier.
	 *
	 * @param uuid the unique identifier of the appointment
	 * @return the {@link Appointment} object with the given UUID, or {@code null}
	 *         if not found
	 */
	public Appointment getById(UUID uuid) {
		for (Appointment appointment : getAll()) {
			if (appointment.getUUID().equals(uuid)) {
				return appointment;
			}
		}
		return null;
	}

	/**
	 * Deserializes the appointment data and writes it to a CSV file. The file
	 * includes the UUID, patient ID, doctor ID, appointment status, date, and time
	 * for each appointment.
	 * 
	 */
	public void deserialize() {
		File file = new File(FILEPATH);
		try {
			PrintWriter printWriter = new PrintWriter(file);
			String header = String.join(",", "UUID", "Patient ID", "Doctor ID", "Appointment Status", "Date", "Time");
			printWriter.println(header);
			for (Appointment appointment : getAll()) {
				String data = String.join(",", appointment.getUUID().toString(), appointment.getPatientId(),
						appointment.getDoctorId(), appointment.getAppointmentStatus().toString2(),
						appointment.getDate().toString(), appointment.getTime().toString());
				printWriter.println(data);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
