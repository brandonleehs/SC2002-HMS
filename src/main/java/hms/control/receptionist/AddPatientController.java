package hms.control.receptionist;

import java.time.LocalDate;
import java.util.List;

import hms.boundary.receptionist.AddPatientView;
import hms.control.Controller;
import hms.entity.record.MedicalRecord;
import hms.entity.user.Patient;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;

public class AddPatientController extends Controller {
    private final AddPatientView addPatientView;
    
    public AddPatientController(){
        this.addPatientView = new AddPatientView();
    }

    @Override
    public void navigate(){
        addPatientView.displayHeader();
        List<Object> patientDetails = addPatientView.getDetails();
        if (patientDetails == null) return;

        String id = String.format("P1%03d", patientRepository.getAll().size());

        MedicalRecord medicalRecord = new MedicalRecord(id, (String)patientDetails.get(0), 
            (LocalDate)patientDetails.get(1), (Gender)patientDetails.get(2), 
            (BloodType)patientDetails.get(3), (String)patientDetails.get(4), 
            (String)patientDetails.get(5));

        patientRepository.addPatient(id, new Patient(medicalRecord, "password"));
        addPatientView.successMessage(id);
    }
}
