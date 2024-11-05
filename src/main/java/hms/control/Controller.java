package hms.control;

import hms.repository.AdministratorRepository;
import hms.repository.DoctorRepository;
import hms.repository.PatientRepository;
import hms.repository.PharmacistRepository;

public abstract class Controller {
	protected static final PatientRepository patientRepository = new PatientRepository();
	protected static final DoctorRepository doctorRepository = new DoctorRepository();
	protected static final PharmacistRepository pharmacistRepository = new PharmacistRepository();
	protected static final AdministratorRepository administratorRepository = new AdministratorRepository();

	public abstract void navigate();
}
