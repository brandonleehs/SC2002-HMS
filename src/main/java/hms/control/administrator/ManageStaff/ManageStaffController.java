package hms.control.administrator.ManageStaff;

import hms.boundary.administrator.ManageStaff.ManageStaffView;
import hms.control.Controller;

public class ManageStaffController extends Controller {
	private final ManageStaffView manageStaffView = new ManageStaffView();
	private final AddStaffController addStaffController = new AddStaffController();
	private final DisplayStaffController displayStaffController = new DisplayStaffController();
	private final UpdateStaffController updateStaffController = new UpdateStaffController();
	private final RemoveStaffController removeStaffController = new RemoveStaffController();

	public ManageStaffController() {
	}

	@Override
	public void navigate() {
		int choice;
		do {
			manageStaffView.displayHeader();
			manageStaffView.displayOptions();
			choice = manageStaffView.getChoice();

			switch (choice) {
			case 1:
				this.addStaffController.navigate();
				break;
			case 2:
				this.updateStaffController.navigate();
				break;
			case 3:
				this.removeStaffController.navigate();
				break;
			case 4:
				this.displayStaffController.navigate();
				break;
			case 5:
				manageStaffView.printExit();
				break;
			default:
			}
		} while (choice < 5);
	}
}