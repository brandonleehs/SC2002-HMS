package hms.control.receptionist;

import hms.boundary.receptionist.ReceptionistMenuView;
import hms.control.MenuController;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Receptionist;
import hms.entity.user.User;

public class ReceptionistMenuController extends MenuController {
    private final Receptionist receptionist;
    private final ReceptionistMenuView receptionistMenuView;

    public ReceptionistMenuController(Receptionist receptionist){
        this.receptionist = receptionist;
        this.receptionistMenuView = new ReceptionistMenuView(receptionist);
    }

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
