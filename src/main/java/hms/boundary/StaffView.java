package hms.boundary;

import hms.entity.user.Patient;
import hms.repository.PatientRepository;

/**
 * An abstract class that represents the view layer for staff members. It
 * provides methods for interacting with patients, including selecting a patient
 * based on their ID from a patient repository.
 */
public abstract class StaffView extends View {
	/**
	 * Prompts the user to enter a patient ID, retrieves the corresponding patient
	 * from the repository, and returns the patient if valid. If the patient ID is
	 * invalid, it recursively prompts the user to enter a valid ID until a valid
	 * patient is found.
	 * 
	 * @param patientRepository The repository used to look up patients by ID.
	 * @return The patient object corresponding to the entered ID.
	 */
	public static Patient choosePatient(PatientRepository patientRepository) {
		Prompt.displayPatientIdPrompt();
		Patient patient = patientRepository.getById(InputHandler.getString());
		if (patient == null) {
			System.out.println("Invalid Patient ID!");
			patient = choosePatient(patientRepository);
		}
		return patient;
	}
}
