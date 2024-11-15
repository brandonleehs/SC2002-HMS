package hms.control.pharmacist;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.patient.record.AppointmentOutcomeRecordView;
import hms.boundary.pharmacist.UpdatePrescriptionStatusView;
import hms.control.Controller;
import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineStatus;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Patient;

public class UpdatePrescriptionStatusController extends Controller{
    private UpdatePrescriptionStatusView updatePrescriptionStatusView = new UpdatePrescriptionStatusView();
    private Patient patient;
    private AppointmentOutcomeRecordView appointmentOutcomeRecordView = new AppointmentOutcomeRecordView();

    public UpdatePrescriptionStatusController(Patient patient){
        this.patient = patient;
    }

    @Override
    public void navigate() {
        // Display index table for user to see which appointment
        List<AppointmentOutcomeRecord> records = appointmentOutcomeRecordView.displayUnprescribedAppointmentOutcomeRecord(this.patient);
        updatePrescriptionStatusView.displayHeader();
        int appointmentIndex = updatePrescriptionStatusView.AppointmentPrompt(records.size());
        if (appointmentIndex == -1) return;
        AppointmentOutcomeRecord editingRecord = records.get(appointmentIndex-1);
        List<Medicine> medicines = new ArrayList<>(editingRecord.getPrescribedMedicineList().keySet());
        
        // Display index table for user to see which medicine
        appointmentOutcomeRecordView.displayPrescriptionTable(editingRecord);
        int medicineIndex = updatePrescriptionStatusView.MedicinePrompt(editingRecord.getPrescribedMedicineList().size());
        Medicine medicineChosen = medicines.get(medicineIndex);
        int quantityToDispense = editingRecord.getPrescribedMedicineList().get(medicineChosen);

        // Display action choice for medicine dispensing (dispense or change back to pending)
        int dispenseOrUndispense = updatePrescriptionStatusView.DecisionPrompt();
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