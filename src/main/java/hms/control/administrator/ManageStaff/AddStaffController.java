package hms.control.administrator.ManageStaff;

import java.util.List;

import hms.boundary.administrator.ManageStaff.AddStaffView;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.Receptionist;
import hms.entity.user.attributes.Gender;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class AddStaffController extends Controller {
	private final AddStaffView addStaffView;

	public AddStaffController() {
		this.addStaffView = new AddStaffView();
	}

	@Override
	public void navigate() {
		addStaffView.displayHeader();
		int choice = addStaffView.getRoleChoice();
		if (choice == 4)
			return;
		List<String> details;
		try {
			details = addStaffView.getDetails();
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return;
		}
		DoctorPharmacistBuilder(details, choice);
	}

	private void DoctorPharmacistBuilder(List<String> details, int choice) {
		// TODO: Verify that id is valid format (D001/P001 for example)
		String password, name, id;
		Gender gender;
		id = details.get(0);
		name = details.get(1);
		if (doctorRepository.getById(id) == null) {
			if (details.get(2).equals("M")) {
				gender = Gender.MALE;
			} else {
				gender = Gender.FEMALE;
			}
			password = "password";
			int age = this.addStaffView.getAge();
			switch (choice) {
				case 1:
					Doctor newDoctor = new Doctor(id, password, name, gender, age);
					doctorRepository.addDoctor(id, newDoctor);
					addStaffView.addDoctorSuccessful();
					break;
				
				case 2:
					Pharmacist newPharmacist = new Pharmacist(id, password, name, gender, age);
					pharmacistRepository.addPharmacist(id, newPharmacist);
					addStaffView.addPharmacistSuccessful();
					break;
			
				case 3:
					Receptionist newReceptionist = new Receptionist(id, password, name, gender, age);
					receptionistRepository.addReceptionist(id, newReceptionist);
					addStaffView.addReceptionistSuccessful();
					break;
			}
		} else {
			addStaffView.userExists();
		}
	}
}
