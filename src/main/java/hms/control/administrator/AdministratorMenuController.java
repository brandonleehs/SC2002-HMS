package hms.control.administrator;

import hms.boundary.InputHandler;
import hms.boundary.Prompt;
import hms.boundary.administrator.AdministratorMenuView;
import hms.control.Controller;
import hms.control.administrator.InventoryManagement.InventoryManagementController;
import hms.control.administrator.ManageStaff.ManageStaffController;
import hms.entity.user.Administrator;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class AdministratorMenuController extends Controller{
    private final Administrator administrator;
    private final AdministratorMenuView adminMenuView;
    private final ManageStaffController staffController;
    private final InventoryManagementController inventoryManagementController;
    private final ReplenishmentController replenishmentController;

    public AdministratorMenuController(Administrator administrator) {
        this.administrator = administrator;
        this.adminMenuView = new AdministratorMenuView(administrator);
        this.staffController = new ManageStaffController();
        this.inventoryManagementController = new InventoryManagementController();
        this.replenishmentController = new ReplenishmentController();
    }

    @Override
    public void navigate() {
        int choice;
        do {
            adminMenuView.displayHeader();
            adminMenuView.displayOptions();
            try{
                choice = InputHandler.getChoice(1, 9);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				// Continue loop if invalid choice
				choice = -1;
				continue;
			}

            switch (choice) {
                case 1:
                    staffController.navigate();
                    break;
                case 2:
                    Prompt.displayPatientIdPrompt();
                    String patientID = InputHandler.getString();
                    Patient patient = patientRepository.getById(patientID);
                    AppointmentController appointmentController = new AppointmentController(patient);
                    appointmentController.navigate();
                    break;
                case 3:
                    inventoryManagementController.navigate();
                    break;
                // case 4:
                //     replenishmentController.approveReplenishmentRequests();
                //     break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
            }
        } while (choice < 5);
    }
}