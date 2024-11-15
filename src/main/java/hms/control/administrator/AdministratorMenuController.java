package hms.control.administrator;

import hms.boundary.InputHandler;
import hms.boundary.administrator.AdministratorMenuView;
import hms.control.Controller;
import hms.control.administrator.InventoryManagement.InventoryManagementController;
import hms.control.administrator.ManageStaff.ManageStaffController;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Administrator;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class AdministratorMenuController extends Controller{
    private final Administrator administrator;
    private final AdministratorMenuView adminMenuView;
    private AppointmentController appointmentController;

    public AdministratorMenuController(Administrator administrator){
        this.administrator = administrator;
        this.adminMenuView = new AdministratorMenuView(administrator);
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
                    ManageStaffController staffController = new ManageStaffController();
                    staffController.navigate();
                    break;
                case 2:
                    appointmentController = new AppointmentController(choosePatient());
                    appointmentController.navigate();
                    break;
                case 3:
                    InventoryManagementController inventoryManagementController = new InventoryManagementController();
                    inventoryManagementController.navigate();
                    break;
                case 4:
                    ReplenishmentController replenishmentController = new ReplenishmentController();
                    replenishmentController.navigate();
                    break;
                case 5:
                    ChangePasswordController changePasswordController = new ChangePasswordController(administrator);
                    changePasswordController.navigate();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
            }
        } while (choice < 6);    
    }

    private Patient choosePatient() {
        adminMenuView.displayPatientIdPrompt();
        String patientID = InputHandler.getString();
        return patientRepository.getById(patientID);
    }
}