package hms.control.receptionist;

import java.time.LocalDate;
import java.util.List;

import hms.boundary.receptionist.AddPatientView;
import hms.control.Controller;
import hms.entity.record.MedicalRecord;
import hms.entity.user.Patient;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

/**
 * Controller for adding a new patient to the system.
 * This class manages the process of gathering patient details from the receptionist,
 * creating a new patient record, and saving it to the patient repository.
 */
public class AddPatientController extends Controller {
    private final AddPatientView addPatientView;

    /**
     * Constructs an instance of AddPatientController.
     * Initializes the view for adding patient details.
     */
    public AddPatientController(){
        this.addPatientView = new AddPatientView();
    }

    /**
     * Navigates the receptionist through the process of adding a new patient.
     */
    @Override
    public void navigate(){
        addPatientView.displayHeader();
        List<Object> patientDetails = addPatientView.getDetails();
        if (patientDetails == null) return;

        String id = String.format("P1%03d", (patientRepository.getAll().size() + 1));

        MedicalRecord medicalRecord = new MedicalRecord(id, (String)patientDetails.get(0), 
            (LocalDate)patientDetails.get(1), (Gender)patientDetails.get(2), 
            (BloodType)patientDetails.get(3), (String)patientDetails.get(4), 
            (String)patientDetails.get(5));

        patientRepository.addPatient(id, new Patient(medicalRecord, "password"));
        addPatientView.successMessage(id);
    }
}
