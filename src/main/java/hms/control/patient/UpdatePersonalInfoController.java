package hms.control.patient;

import hms.boundary.Prompt;
import hms.boundary.patient.UpdatePersonalInfoView;
import hms.control.Controller;
import hms.entity.user.Patient;

public class UpdatePersonalInfoController extends Controller {
	private final UpdatePersonalInfoView updatePersonalInfoView;
	private Patient patient;

	public UpdatePersonalInfoController(Patient patient) {
		this.patient = patient;
		this.updatePersonalInfoView = new UpdatePersonalInfoView();
	}

	@Override
	public void navigate() {
		this.updatePersonalInfoView.displayHeader();
		int choice = this.updatePersonalInfoView.displayOptions();

		switch (choice) {
		case 1:
			patient.setPhoneNumber(Prompt.displayPhoneNumberPrompt());
			this.updatePersonalInfoView.displayPhoneNumberUpdate();
			break;
		case 2:
			patient.setEmailAddress(Prompt.displayEmailAddressPrompt());
			this.updatePersonalInfoView.displayEmailAddressUpdate();
			break;
		case 3:
			this.updatePersonalInfoView.displayReturnMenu();
		default:
		}
	}
}
