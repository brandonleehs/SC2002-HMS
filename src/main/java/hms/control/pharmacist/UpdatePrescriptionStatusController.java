package hms.control.pharmacist;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.patient.record.AppointmentOutcomeRecordView;
import hms.boundary.pharmacist.UpdatePrescriptionStatusView;
import hms.control.Controller;
import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineStatus;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class UpdatePrescriptionStatusController extends Controller{
    private UpdatePrescriptionStatusView updatePrescriptionStatusView = new UpdatePrescriptionStatusView();
    private Patient patient;
    private AppointmentOutcomeRecordView appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();

    public UpdatePrescriptionStatusController(Patient patient){
        this.patient = patient;
    }

    @Override
    public void navigate() {
       try {
           updatePrescriptionStatus();
       } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
           
       }
    }

   private void updatePrescriptionStatus() throws InvalidChoiceFormatException, InvalidChoiceValueException {
        // Display index table for user to see which appointment
        List<AppointmentOutcomeRecord> records = appointmentOutcomeRecordView.displayUnprescribedAppointmentOutcomeRecord(this.patient);
        updatePrescriptionStatusView.displayHeader();
        updatePrescriptionStatusView.AppointmentPrompt();
        int appointmentIndex = InputHandler.getChoice(1,999);
        AppointmentOutcomeRecord editingRecord = records.get(appointmentIndex-1);
        List<Medicine> medicines = new ArrayList<>(editingRecord.getPrescribedMedicineList().keySet());
        
        // Display index table for user to see which medicine
        appointmentOutcomeRecordView.displayPrescriptionTable(editingRecord);
        updatePrescriptionStatusView.MedicinePrompt();
        Medicine medicineChosen = medicines.get(InputHandler.getChoice(1,999)-1);
        int quantityToDispense = editingRecord.getPrescribedMedicineList().get(medicineChosen);

        // Display action choice for medicine dispensing (dispense or change back to pending)
        updatePrescriptionStatusView.DecisionPrompt();
        int dispenseOrUndispense = InputHandler.getChoice(1, 2);
        if (dispenseOrUndispense==1){
                // Sucessfully deduct medicine from stock
                if (medicineInventory.dispenseMedicine(medicineChosen.getName(), quantityToDispense)){
                    medicineChosen.setMedicineStatus(MedicineStatus.DISPENSED);
                    updatePrescriptionStatusView.SuccessfulDispense();
                }
                // Unsuccessful deduction
                else{
                    updatePrescriptionStatusView.UnsuccessfulDispense();
                }
        }
       
    //    TODO: Consider adding reversal of medicine
    //    else if (dispenseOrUndispense==2){

    //    }

   }

}