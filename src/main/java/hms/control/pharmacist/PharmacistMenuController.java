package hms.control.pharmacist;

import hms.boundary.InputHandler;
import hms.boundary.pharmacist.PharmacistMenuView;
import hms.control.Controller;
import hms.control.user.ChangePasswordController;
import hms.boundary.patient.record.MedicalRecordView;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class PharmacistMenuController extends Controller{
    private final PharmacistMenuView pharmacistMenuView;
    private Pharmacist pharmacist;

    public PharmacistMenuController(Pharmacist pharmacist){
        this.pharmacist=pharmacist;
        this.pharmacistMenuView=new PharmacistMenuView(pharmacist);
    }

    @Override
    public void navigate() {
        int choice = 0;
        do{
            this.pharmacistMenuView.displayHeader();
            this.pharmacistMenuView.displayOptions();

            try{
                choice = InputHandler.getChoice(1, 9);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				// Continue loop if invalid choice
				choice = -1;
				continue;
			}

            switch (choice) {
            case 1: //view appointment outcome record
                MedicalRecordView medicalRecordView = new MedicalRecordView(choosePatient());
                medicalRecordView.displayAppointmentOutcomeRecord();
                break;
            // case 2: //update patient medical records
            //     UpdatePatientMedicalRecordController updatePatientMedicalRecordController = new UpdatePatientMedicalRecordController(choosePatient());
            //     updatePatientMedicalRecordController.navigate();
            //     break;
            // case 3: //view personal schedule (all appointments in DB)
            //     scheduleView.displayAllAppointments(doctor.getSchedule(), patientRepository);
            //     break;
            // case 4: //set availability for appointments
            //     SetDoctorAvailabilityController setDoctorAvailabilityController = new SetDoctorAvailabilityController(doctor);
            //     setDoctorAvailabilityController.navigate();
            //     break;
            // case 5: // accept or decline appt requests
            //     PendingRequestController pendingRequestController = new PendingRequestController(doctor);
            //     pendingRequestController.navigate();
            //     break;
            // case 6: //view all appts today & tmr
            //     scheduleView.displayUpcomingAppointments(doctor.getSchedule(), patientRepository);
            //     break;
            // case 7: //record appt outcome
            //     CompleteAppointmentController completeAppointmentController = new CompleteAppointmentController(doctor);
            //     completeAppointmentController.navigate();
            //     break;
            case 8: //change password
                ChangePasswordController changePasswordController = new ChangePasswordController(pharmacist);
                changePasswordController.navigate();
                break;
            case 9: //logout
                System.out.println("Logging out.");
                break;
            default:
            }
        } while (choice < 9);
    }
    
    private Patient choosePatient() {
        pharmacistMenuView.displayPatientIDPrompt();
        String patientID = InputHandler.getString();
        return patientRepository.getById(patientID);
    }

}