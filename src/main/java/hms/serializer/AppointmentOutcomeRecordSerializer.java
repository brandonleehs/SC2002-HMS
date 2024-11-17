package hms.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class AppointmentOutcomeRecordSerializer extends Serializer {
	private final AppointmentRepository appointmentRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;

	public AppointmentOutcomeRecordSerializer(String filepath, AppointmentRepository appointmentRepository,
			PatientRepository patientRepository, DoctorRepository doctorRepository) {
		super(filepath);
		this.appointmentRepository = appointmentRepository;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initAppointmentOutcomeRecordFromRow(String[] row) {
		UUID uuid = UUID.fromString(row[0]);
		// Parse as LocalDate
		LocalDate date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(row[1]));
		String serviceType = row[2];
		String consultationNotes = row[3];
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
