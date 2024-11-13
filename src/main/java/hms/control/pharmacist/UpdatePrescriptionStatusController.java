package hms.control.pharmacist;

import hms.boundary.pharmacist.UpdatePrescriptionStatusView;
import hms.control.Controller;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class UpdatePrescriptionStatusController extends Controller{
    private UpdatePrescriptionStatusView updatePrescriptionStatusView = new UpdatePrescriptionStatusView();
    private Patient patient;

    public UpdatePrescriptionStatusController(Patient patient){
        this.patient = patient;
        this.updatePrescriptionStatusView = new UpdatePrescriptionStatusView();
    }

    @Override
    public void navigate() {
       try {
           updatePrescriptionStatus();
       } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
           return;
       }
    }

   private void updatePrescriptionStatus() throws InvalidChoiceFormatException, InvalidChoiceValueException {
    //    this.updatePrescriptionStatusView();
   }

}