package hms.control.patient;

import hms.boundary.InputHandler;
import hms.boundary.Prompt;
import hms.boundary.patient.UpdatePersonalInfoView;
import hms.control.Controller;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

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

		int choice;
		try {
			choice = InputHandler.getChoice(1, 3);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return;
		}

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
		case 3:
			this.updatePersonalInfoView.displayReturnMenu();
		default:
		}
	}
}
