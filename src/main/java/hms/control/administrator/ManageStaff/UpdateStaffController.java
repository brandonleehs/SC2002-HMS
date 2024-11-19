package hms.control.administrator.ManageStaff;

import java.util.List;

import hms.boundary.administrator.ManageStaff.UpdateStaffView;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.Receptionist;
import hms.entity.user.attributes.Gender;

/**
 * Controller class for managing the update process of different staff members (Doctor, Pharmacist, Receptionist).
 * This class handles the user interactions and invokes the appropriate update methods for each staff role.
 */
public class UpdateStaffController extends Controller {
	private final UpdateStaffView updateStaffView;

	/**
	 * Constructs a new instance of UpdateStaffController with a new UpdateStaffView.
	 */
	public UpdateStaffController() {
		this.updateStaffView = new UpdateStaffView();
	}

	/**
	 * Navigates through the process of updating a staff member's information based on the user's choice.
	 * Displays a header, prompts for the role of the staff to update, and performs actions accordingly.
	 */
	@Override
	public void navigate() {
		updateStaffView.displayHeader();
		int choice = updateStaffView.getRoleChoice();
		if (choice == 4)
			return;
		String tempID = updateStaffView.getID();
		List<String> details;
		int age;
		switch (choice) {
		case 1: // if Doctor chosen
			Doctor editingDoctor = doctorRepository.getById(tempID);
			if (editingDoctor == null) {
				updateStaffView.staffDoesNotExist();
				return;
			}
			details = updateStaffView.getDetails();
			age = this.updateStaffView.getAge();
			if (age == -1) {
				return;
			}
			doctorEditor(details, editingDoctor, age);
			break;

		case 2: // if Pharmacist chosen
			Pharmacist editingPharmacist = pharmacistRepository.getById(tempID);
			if (editingPharmacist == null) {
				updateStaffView.staffDoesNotExist();
				return;
			}
			details = updateStaffView.getDetails();
			age = this.updateStaffView.getAge();
			if (age == -1) {
				return;
			}
			pharmacistEditor(details, editingPharmacist, age);
			break;

		case 3:
			Receptionist editingReceptionist = receptionistRepository.getById(tempID);
			if (editingReceptionist == null) {
				updateStaffView.staffDoesNotExist();
				return;
			}
			details = updateStaffView.getDetails();
			age = this.updateStaffView.getAge();
			if (age == -1) {
				return;
			}
			receptionistEditor(details, editingReceptionist, age);
			break;
		}
	}

	/**
	 * Updates the details of the specified doctor.
	 * Prompts the user for the new information and applies the changes if confirmed.
	 *
	 * @param details List of new details for the doctor.
	 * @param editingDoctor The doctor to be updated.
	 * @param age The new age of the doctor.
	 */
	private void doctorEditor(List<String> details, Doctor editingDoctor, int age) {
		int choice = updateStaffView.printDetails(details, age);
		if (choice == 2) {
			return;
		}
		editingDoctor.setName(details.get(0));
		if (details.get(1).equals("M")) {
			editingDoctor.setGender(Gender.MALE);
		} else {
			editingDoctor.setGender(Gender.FEMALE);
		}
		updateStaffView.updateDoctorSuccessful();
	}

	/**
	 * Updates the details of the specified pharmacist.
	 * Prompts the user for the new information and applies the changes if confirmed.
	 *
	 * @param details List of new details for the pharmacist.
	 * @param editingPharmacist The pharmacist to be updated.
	 * @param age The new age of the pharmacist.
	 */
	private void pharmacistEditor(List<String> details, Pharmacist editingPharmacist, int age) {
		int choice = updateStaffView.printDetails(details, age);
		if (choice == 2) {
			return;
		}
		editingPharmacist.setName(details.get(0));
		if (details.get(1).equals("M")) {
			editingPharmacist.setGender(Gender.MALE);
		} else {
			editingPharmacist.setGender(Gender.FEMALE);
		}
		updateStaffView.updatePharmacistSuccessful();
	}

	/**
	 * Updates the details of the specified receptionist.
	 * Prompts the user for the new information and applies the changes if confirmed.
	 *
	 * @param details List of new details for the receptionist.
	 * @param editingReceptionist The receptionist to be updated.
	 * @param age The new age of the receptionist.
	 */
	private void receptionistEditor(List<String> details, Receptionist editingReceptionist, int age) {
		int choice = updateStaffView.printDetails(details, age);
		if (choice == 2) {
			return;
		}
		editingReceptionist.setName(details.get(0));
		if (details.get(1).equals("M")) {
			editingReceptionist.setGender(Gender.MALE);
		} else {
			editingReceptionist.setGender(Gender.FEMALE);
		}
		updateStaffView.updateReceptionistSuccessful();
	}
}
