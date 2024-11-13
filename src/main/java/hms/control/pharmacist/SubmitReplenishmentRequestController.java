package hms.control.pharmacist;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.pharmacist.SubmitReplenishmentRequestView;
import hms.control.Controller;
import hms.entity.medicine.ReplenishRequest;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
;

public class SubmitReplenishmentRequestController extends Controller{
    private final SubmitReplenishmentRequestView submitReplenishmentRequestView;
    private final ShowMedicationInventoryController showMedicationInventoryController;

    public SubmitReplenishmentRequestController(){
        this.submitReplenishmentRequestView = new SubmitReplenishmentRequestView();
        this.showMedicationInventoryController = new ShowMedicationInventoryController();
    }

    public void navigate(){
        int medicineChoice=0, amount = 0;
        List<String> medicineNames = medicineInventory.getMedicineNames();
        this.submitReplenishmentRequestView.displayHeader();
        showMedicationInventoryController.navigate();
        // Prompt for choosing which medicine
        this.submitReplenishmentRequestView.MedicineIndexPrompt();
        try{
            medicineChoice = InputHandler.getChoice(1, medicineNames.size());
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
        }
        // Prompt for amount to be replenished
        this.submitReplenishmentRequestView.MedicineAmountPrompt();
        try{
            amount = InputHandler.getChoice(1, 700);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
        }
        ReplenishRequest replenishRequest = new ReplenishRequest(medicineNames.get(medicineChoice-1), amount);
        medicineInventory.addReplenishmentRequest(replenishRequest);
        this.submitReplenishmentRequestView.SuccessfulRequestPrompt();
    }

}