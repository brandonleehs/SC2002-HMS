package hms.control.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.boundary.user.LoginView;
import hms.boundary.user.StartView;
import hms.control.Controller;
import hms.control.administrator.AdministratorMenuController;
import hms.control.doctor.DoctorMenuController;
import hms.control.patient.PatientMenuController;
import hms.control.pharmacist.PharmacistMenuController;
import hms.control.receptionist.ReceptionistMenuController;
import hms.entity.user.Administrator;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;
import hms.entity.user.Receptionist;
import hms.entity.user.User;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class LoginController extends Controller {
	private final LoginView loginView;
	private final StartView startView;

	public LoginController() {
		this.loginView = new LoginView();
		this.startView = new StartView();
	}

	@Override
	public void navigate() {
		boolean login = false;
		User user = null;
		while (true) {
			startView.displayHeader();
			startView.displayOptions();
			int choice = 0;
			try {
				choice = InputHandler.getChoice(1, 2);
			} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				choice = 2;
			}

			if (choice == 1) {
				do {
					View.displayLogo();
					this.loginView.displayHeader();
					String id = this.loginView.displayIdPrompt();
					String password = this.loginView.displayPasswordPrompt();
					user = getUser(id, password);
					login = authenticate(user, id, password);
				} while (!login);

				if (user instanceof Patient) {
					PatientMenuController patientMenuController = new PatientMenuController((Patient) user);
					patientMenuController.navigate();

				} else if (user instanceof Doctor) {
					DoctorMenuController doctorMenuController = new DoctorMenuController((Doctor) user);
					doctorMenuController.navigate();

				} else if (user instanceof Pharmacist) {
					PharmacistMenuController pharmacistMenuController = new PharmacistMenuController((Pharmacist) user);
					pharmacistMenuController.navigate();

				} else if (user instanceof Administrator) {
					AdministratorMenuController administratorMenuController = new AdministratorMenuController(
							(Administrator) user);
					administratorMenuController.navigate();

				} else if (user instanceof Receptionist) {
					ReceptionistMenuController receptionistMenuController = new ReceptionistMenuController(
							(Receptionist) user);
					receptionistMenuController.navigate();
				}

			} else {
				// save and close
				patientRepository.deserialize();
				doctorRepository.deserialize(); // must deserialize doctor first since it writes header!
				pharmacistRepository.deserialize();
				administratorRepository.deserialize();
				receptionistRepository.deserialize();
				medicineInventory.deserialize();
				appointmentRepository.deserialize();
				appointmentOutcomeRecordRepository.deserialize();
				break;
			}
		}
	}

	private User getUser(String id, String password) {
		Map<String, User> userMap = new HashMap<String, User>();
		userMap.putAll(administratorRepository.getMap());
		userMap.putAll(doctorRepository.getMap());
		userMap.putAll(patientRepository.getMap());
		userMap.putAll(pharmacistRepository.getMap());
		userMap.putAll(receptionistRepository.getMap());
		User user = userMap.get(id);
		return user;
	}

	private boolean authenticate(User user, String id, String password) {
		if (user == null || !(id.equals(user.getId()) && BCrypt.checkpw(password, user.getPasswordHash()))) {
			System.out.println("Authentication failed. Please try again.");
			return false;
		}
		return true;
	}
}
