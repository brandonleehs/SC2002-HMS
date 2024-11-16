package hms.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.medicine.Medicine;
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
					String data = String.join(",", aor.getUUID().toString(), aor.getDate().toString(),
							aor.getServiceType(), aor.getConsultationNotes());
					Map<Medicine, Integer> prescribedMedicineMap = aor.getPrescribedMedicineMap();
					for (Map.Entry<Medicine, Integer> entry : prescribedMedicineMap.entrySet()) {
						String medName = entry.getKey().getName();
						Integer amt = entry.getValue();
						data = data + "," + medName + "," + String.valueOf(amt);
					}
					printWriter.println(data);
				}
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
