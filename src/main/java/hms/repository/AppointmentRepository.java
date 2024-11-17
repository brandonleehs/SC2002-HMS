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

public class AppointmentRepository {
	private final PatientRepository patientRepository;
	private final static String FILEPATH = "./src/main/resources/Appointment_List.csv";

	public AppointmentRepository(PatientRepository patientRepository, DoctorRepository doctorRepository) {
		this.patientRepository = patientRepository;
		AppointmentSerializer appointmentSerializer = new AppointmentSerializer(FILEPATH, patientRepository,
				doctorRepository);
		appointmentSerializer.serialize();
	}

	public List<Appointment> getAll() {
		List<Appointment> appointmentList = new ArrayList<Appointment>();

		for (Patient patient : patientRepository.getAll()) {
			appointmentList.addAll(patient.getAllAppointmentList());
		}
		return appointmentList;
	}

	public Appointment getById(UUID uuid) {
		for (Appointment appointment : getAll()) {
			if (appointment.getUUID().equals(uuid)) {
				return appointment;
			}
		}
		return null;
	}

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
