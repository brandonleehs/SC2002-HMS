package hms.control.receptionist;

import hms.boundary.receptionist.ReceptionistMenuView;
import hms.control.MenuController;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Receptionist;
import hms.entity.user.User;

/**
 * Controller class for managing the receptionist menu in the hospital management system.
 * Handles navigation between different receptionist functionalities, including adding patients
 * and changing the password.
 */
public class ReceptionistMenuController extends MenuController {
    private final Receptionist receptionist;
    private final ReceptionistMenuView receptionistMenuView;

    /**
     * Constructs a ReceptionistMenuController for the given receptionist.
     *
     * @param receptionist the receptionist whose menu is being managed.
     */
    public ReceptionistMenuController(Receptionist receptionist){
        this.receptionist = receptionist;
        this.receptionistMenuView = new ReceptionistMenuView(receptionist);
    }

    /**
     * Navigates through the receptionist menu, allowing the receptionist to choose an option
     * to either add a new patient, change their password, or log out.
     */
    @Override
    public void navigate() {
        checkNewUser((User)receptionist);
        int choice = 0;
        do{
            this.receptionistMenuView.displayHeader();
            choice = this.receptionistMenuView.displayOptions();

            switch (choice) {
                case 1:
                    AddPatientController addPatientController = new AddPatientController();
                    addPatientController.navigate();
                    break;
                case 2:
                    ChangePasswordController changePasswordController = new ChangePasswordController((User)receptionist);
                    changePasswordController.navigate();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    break;
                default:
            }
        } while (choice < 3);
    }
}
