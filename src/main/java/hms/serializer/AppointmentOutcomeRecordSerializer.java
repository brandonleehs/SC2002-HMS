package hms.serializer;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import hms.entity.appointment.Appointment;
import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineStatus;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.repository.AppointmentRepository;
import hms.repository.DoctorRepository;
import hms.repository.PatientRepository;


/**
 * The AppointmentOutcomeRecordSerializer class is responsible for deserializing and processing
 * appointment outcome records from a CSV file. It converts each record into an {@link Appointment}
 * outcome, updating associated {@link Doctor}, {@link Patient}, and {@link Medicine} data.
 */
public class AppointmentOutcomeRecordSerializer extends Serializer {
	private final AppointmentRepository appointmentRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;

	/**
     * Constructs an AppointmentOutcomeRecordSerializer with the specified file path and repositories.
     * This constructor initializes the serializer to read from the given file and use the provided
     * repositories for appointment, patient, and doctor data.
     * 
     * @param filepath the path to the CSV file containing appointment outcome records
     * @param appointmentRepository the repository for retrieving appointment data
     * @param patientRepository the repository for retrieving patient data
     * @param doctorRepository the repository for retrieving doctor data
     */
	public AppointmentOutcomeRecordSerializer(String filepath, AppointmentRepository appointmentRepository,
			PatientRepository patientRepository, DoctorRepository doctorRepository) {
		super(filepath);
		this.appointmentRepository = appointmentRepository;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

	/**
     * Deserializes the appointment outcome records from the CSV file and processes each record.
     * Each record is read, decoded, and used to update the relevant {@link Appointment}, {@link Patient},
     * {@link Doctor}, and {@link Medicine} data.
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
				initAppointmentOutcomeRecordFromRow(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Initializes an Appointment Outcome record from a given CSV row.
     * This method decodes the service type and consultation notes, creates a map of prescribed medicines,
     * and updates the associated {@link Doctor} and {@link Patient} data for the relevant appointment.
     * 
     * @param row the CSV row containing the appointment outcome record
     */
	private void initAppointmentOutcomeRecordFromRow(String[] row) {
		UUID uuid = UUID.fromString(row[0]);
		String encodedServiceType = row[2];
		String encodedConsultationNotes = row[3];
		String serviceType = new String(Base64.getDecoder().decode(encodedServiceType));
		String consultationNotes = new String(Base64.getDecoder().decode(encodedConsultationNotes));

		Map<Medicine, Integer> prescribedMedicineMap = new HashMap<Medicine, Integer>();
		for (int i = 4; i < row.length; i += 3) {
			String medicineName = row[i];
			Integer amt = Integer.valueOf(row[i + 1]);
			MedicineStatus medicineStatus = MedicineStatus.fromString(row[i + 2]);
			Medicine medicine = new Medicine(medicineName);
			medicine.setMedicineStatus(medicineStatus);
			prescribedMedicineMap.put(medicine, amt);
		}

		Appointment appointment = this.appointmentRepository.getById(uuid);
		Patient patient = patientRepository.getById(appointment.getPatientId());
		Doctor doctor = doctorRepository.getById(appointment.getDoctorId());
		doctor.completeAppointment(patient, appointment, serviceType, consultationNotes, prescribedMedicineMap);
	}
}
