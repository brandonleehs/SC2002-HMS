package hms.control;

import hms.repository.AdministratorRepository;
import hms.repository.AppointmentOutcomeRecordRepository;
import hms.repository.AppointmentRepository;
import hms.repository.DoctorRepository;
import hms.repository.MedicineInventory;
import hms.repository.PatientRepository;
import hms.repository.PharmacistRepository;
import hms.repository.ReceptionistRepository;

public abstract class Controller {
	protected static final PatientRepository patientRepository = new PatientRepository();
	protected static final DoctorRepository doctorRepository = new DoctorRepository();
	protected static final PharmacistRepository pharmacistRepository = new PharmacistRepository();
	protected static final AdministratorRepository administratorRepository = new AdministratorRepository();
	protected static final ReceptionistRepository receptionistRepository = new ReceptionistRepository();
	protected static final MedicineInventory medicineInventory = new MedicineInventory();
	protected static final AppointmentRepository appointmentRepository = new AppointmentRepository(patientRepository,
			doctorRepository);
	protected static final AppointmentOutcomeRecordRepository appointmentOutcomeRecordRepository = new AppointmentOutcomeRecordRepository(
			patientRepository, doctorRepository, appointmentRepository);

	public abstract void navigate();
}
