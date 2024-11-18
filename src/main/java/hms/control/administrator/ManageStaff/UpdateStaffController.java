package hms.control.administrator.ManageStaff;

import java.util.List;

import hms.boundary.administrator.ManageStaff.UpdateStaffView;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.Receptionist;
import hms.entity.user.attributes.Gender;

public class UpdateStaffController extends Controller {
	private final UpdateStaffView updateStaffView;

	public UpdateStaffController() {
		this.updateStaffView = new UpdateStaffView();
	}

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
