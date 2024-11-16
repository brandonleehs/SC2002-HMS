package hms.boundary;

import hms.entity.user.Patient;
import hms.repository.PatientRepository;

public abstract class StaffView extends View {
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
