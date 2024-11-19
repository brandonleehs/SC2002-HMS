package hms.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Map;

import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineStatus;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.serializer.AppointmentOutcomeRecordSerializer;


/**
 * This class represents a repository for managing {@link AppointmentOutcomeRecord} objects.
 * It is responsible for serializing and deserializing appointment outcome data to and from a CSV file.
 * It works with the {@link AppointmentRepository} to retrieve completed appointments and their associated outcome records.
 */
public class AppointmentOutcomeRecordRepository {
	private final AppointmentRepository appointmentRepository;
	private final static String FILEPATH = "./src/main/resources/AppointmentOutcomeRecord_List.csv";

	/**
     * Constructs a new {@code AppointmentOutcomeRecordRepository}.
     * This constructor initializes the repository by serializing the appointment outcome records 
     * to a CSV file using the provided {@link AppointmentRepository}, {@link PatientRepository}, and {@link DoctorRepository}.
     *
     * @param patientRepository the {@link PatientRepository} used to access patient data
     * @param doctorRepository the {@link DoctorRepository} used to access doctor data
     * @param appointmentRepository the {@link AppointmentRepository} used to access appointment data
     */
	public AppointmentOutcomeRecordRepository(PatientRepository patientRepository, DoctorRepository doctorRepository,
			AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
		AppointmentOutcomeRecordSerializer appointmentOutcomeRecordSerializer = new AppointmentOutcomeRecordSerializer(
				FILEPATH, appointmentRepository, patientRepository, doctorRepository);
		appointmentOutcomeRecordSerializer.serialize();
	}

	/**
     * Deserializes the appointment outcome records and writes them to a CSV file.
     * The file includes the UUID, date, service type, consultation notes, and prescribed medicines for completed appointments.
     * Comma characters in service type and consultation notes are encoded using Base64 to avoid format issues.
     */
	public void deserialize() {
		File file = new File(FILEPATH);
		try {
			PrintWriter printWriter = new PrintWriter(file);
			String header = String.join(",", "UUID", "Date", "Service Type", "Consultation Notes",
					"Prescribed Medicines");
			printWriter.println(header);
			for (Appointment appointment : appointmentRepository.getAll()) {
				if (appointment.getAppointmentStatus() == AppointmentStatus.COMPLETED) {
					AppointmentOutcomeRecord aor = appointment.getAppointmentOutcomeRecord();
					String serviceType = aor.getServiceType();
					String consultationNotes = aor.getConsultationNotes();

					// Use base64 encoding to prevent commas in string
					String encodedServiceType = Base64.getEncoder().encodeToString(serviceType.getBytes());
					String encodedConsultationNotes = Base64.getEncoder().encodeToString(consultationNotes.getBytes());

					String data = String.join(",", aor.getUUID().toString(), aor.getDate().toString(),
							encodedServiceType, encodedConsultationNotes);
					Map<Medicine, Integer> prescribedMedicineMap = aor.getPrescribedMedicineMap();
					for (Map.Entry<Medicine, Integer> entry : prescribedMedicineMap.entrySet()) {
						String medName = entry.getKey().getName();
						Integer amt = entry.getValue();
						MedicineStatus medicineStatus = entry.getKey().getMedicineStatus();
						data = data + "," + medName + "," + String.valueOf(amt) + "," + medicineStatus.toString2();
					}
					printWriter.println(data);
				}
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
