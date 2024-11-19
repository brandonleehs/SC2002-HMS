package hms.control.administrator;

import hms.boundary.StaffView;
import hms.boundary.administrator.AdministratorMenuView;
import hms.control.MenuController;
import hms.control.administrator.InventoryManagement.InventoryManagementController;
import hms.control.administrator.ManageStaff.ManageStaffController;
import hms.control.user.ChangePasswordController;
import hms.entity.user.Administrator;
import hms.entity.user.Patient;
import hms.entity.user.User;

/**
 * Controller class for managing the menu options available to an administrator.
 * Facilitates navigation between different administrative functions such as managing staff,
 * patient appointments, inventory management, and password management.
 */
public class AdministratorMenuController extends MenuController {
	private final Administrator administrator;
	private final AdministratorMenuView adminMenuView;

	/**
	 * Constructs a new AdministratorMenuController with the specified administrator.
	 *
	 * @param administrator the administrator for whom the menu is being displayed
	 */
	public AdministratorMenuController(Administrator administrator) {
		this.administrator = administrator;
		this.adminMenuView = new AdministratorMenuView(administrator);
	}

	/**
	 * Facilitates the navigation of the administrator menu.
	 */
	@Override
	public void navigate() {
		checkNewUser((User)administrator);
		int choice;
		do {
			adminMenuView.displayHeader();
			choice = adminMenuView.displayOptions();

			switch (choice) {
			case 1:
				ManageStaffController staffController = new ManageStaffController();
				staffController.navigate();
				break;
			case 2:
				Patient patient = StaffView.choosePatient(patientRepository);
				if (patient == null)
					continue;
				AppointmentController appointmentController = new AppointmentController(patient);
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
				ResetPasswordController resetPasswordController = new ResetPasswordController();
				resetPasswordController.navigate();
				break;
			case 6:
				ChangePasswordController changePasswordController = new ChangePasswordController((User)administrator);
				changePasswordController.navigate();
				break;
			case 7:
				System.out.println("Logging out...");
				break;
			default:
			}
		} while (choice < 7);
	}

}