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

public class AppointmentOutcomeRecordRepository {
	private final AppointmentRepository appointmentRepository;
	private final static String FILEPATH = "./src/main/resources/AppointmentOutcomeRecord_List.csv";

	public AppointmentOutcomeRecordRepository(PatientRepository patientRepository, DoctorRepository doctorRepository,
			AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
		AppointmentOutcomeRecordSerializer appointmentOutcomeRecordSerializer = new AppointmentOutcomeRecordSerializer(
				FILEPATH, appointmentRepository, patientRepository, doctorRepository);
		appointmentOutcomeRecordSerializer.serialize();
	}

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
