package hms.control.pharmacist;

import hms.boundary.InputHandler;
import hms.boundary.pharmacist.PharmacistMenuView;
import hms.control.Controller;
import hms.control.user.ChangePasswordController;
import hms.boundary.patient.record.MedicalRecordView;
import hms.boundary.pharmacist.UpdatePrescriptionStatusView;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.exceptions.InvalidDateException;
import hms.exceptions.InvalidTimeException;

public class UpdatePrescriptionStatusController extends Controller{
    private UpdatePrescriptionStatusView updatePrescriptionStatusView;
    private Patient patient;

    public UpdatePrescriptionStatusController(Patient patient){
        this.patient = patient;
        this.updatePrescriptionStatusView = new UpdatePrescriptionStatusView();
    }

    @Override
    public void navigate() {
//        try {
//            updatePrescriptionStatus();
//        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
//            return;
//        }
    }

//    private void updatePrescriptionStatus() throws InvalidChoiceFormatException, InvalidChoiceValueException {
//        this.updatePrescriptionStatusView
//    }

}