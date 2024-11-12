package hms.control.administrator;

import hms.boundary.administrator.AdministratorMenuView;
import hms.control.administrator.ManageStaffController;
import hms.control.administrator.AppointmentController;
import hms.control.administrator.InventoryController;
import hms.control.administrator.ReplenishmentController;
import hms.entity.user.Administrator;

public class AdministratorMenuController {
    private final Administrator administrator;
    private final AdministratorMenuView adminMenuView;
    private final ManageStaffController staffController;
    private final AppointmentController appointmentController;
    private final InventoryController inventoryController;
    private final ReplenishmentController replenishmentController;

    public AdministratorMenuController(Administrator administrator) {
        this.administrator = administrator;
        this.adminMenuView = new AdministratorMenuView(administrator);
        this.staffController = new ManageStaffController();
        this.appointmentController = new AppointmentController();
        this.inventoryController = new InventoryController();
        this.replenishmentController = new ReplenishmentController();
    }

    public void navigate() {
        int choice;
        do {
            adminMenuView.displayHeader();
            adminMenuView.displayOptions();
            choice = adminMenuView.getUserChoice();

            switch (choice) {
                case 1:
                    staffController.manageStaff();
                    break;
                case 2:
                    appointmentController.viewAppointments();
                    break;
                case 3:
                    inventoryController.manageInventory();
                    break;
                case 4:
                    replenishmentController.approveReplenishmentRequests();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
