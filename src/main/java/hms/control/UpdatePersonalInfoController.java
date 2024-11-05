package hms.control;

import hms.Prompt;
import hms.boundary.InputHandler;
import hms.boundary.patient.UpdatePersonalInfoView;
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
		this.updatePersonalInfoView.displayOptions();

		int choice = InputHandler.getChoice();
		switch (choice) {
		case 1:
			Prompt.displayPhoneNumberPrompt();
			String phoneNumber = InputHandler.getString();
			patient.setPhoneNumber(phoneNumber);
			this.updatePersonalInfoView.displayPhoneNumberUpdate();
			break;
		case 2:
			Prompt.displayEmailAddressPrompt();
			String emailAddress = InputHandler.getString();
			patient.setEmailAddress(emailAddress);
			this.updatePersonalInfoView.displayEmailAddressUpdate();
			break;
		default:
			this.updatePersonalInfoView.displayReturnMenu();
		}
	}
}
