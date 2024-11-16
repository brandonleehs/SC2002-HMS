package hms.control.receptionist;

import hms.boundary.receptionist.ReceptionistMenuView;
import hms.control.Controller;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Receptionist;

public class ReceptionistMenuController extends Controller{
    private final Receptionist receptionist;
    private final ReceptionistMenuView receptionistMenuView;

    public ReceptionistMenuController(Receptionist receptionist){
        this.receptionist = receptionist;
        this.receptionistMenuView = new ReceptionistMenuView(receptionist);
    }

    @Override
    public void navigate() {
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
                    ChangePasswordController changePasswordController = new ChangePasswordController(receptionist);
                    changePasswordController.navigate();
                case 3:
                    System.out.println("Logging out...");
                    break;
                default:
            }
        } while (choice < 3);
    }
}
