package hms.control.patient;

import hms.boundary.Prompt;
import hms.boundary.patient.UpdatePersonalInfoView;
import hms.control.Controller;
import hms.entity.user.Patient;

/**
 * Controller class for handling the update of personal information for a patient.
 * Provides options for the patient to update their phone number or email address.
 */
public class UpdatePersonalInfoController extends Controller {
	private final UpdatePersonalInfoView updatePersonalInfoView;
	private Patient patient;

	/**
	 * Constructs a new UpdatePersonalInfoController with the specified patient.
	 *
	 * @param patient the patient whose personal information is being updated
	 */
	public UpdatePersonalInfoController(Patient patient) {
		this.patient = patient;
		this.updatePersonalInfoView = new UpdatePersonalInfoView();
	}

	/**
	 * Displays the menu for updating the patient's personal information. The patient can choose to update
	 * their phone number, email address, or return to the previous menu. Upon successful updates,
	 * confirmation messages are shown.
	 */
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
			if (patient.setEmailAddress(Prompt.displayEmailAddressPrompt())) {
				this.updatePersonalInfoView.displayEmailAddressUpdate();
			} else {
				this.updatePersonalInfoView.displayNoEmailAddressUpdate();
			}
			break;
		case 3:
			this.updatePersonalInfoView.displayReturnMenu();
		default:
		}
	}
}
