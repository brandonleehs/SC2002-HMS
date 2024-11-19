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

/**
 * Controller class responsible for handling the user login process in the hospital management system.
 * This controller displays the login screen, authenticates the user, and navigates to the appropriate
 * menu based on the user's role (Patient, Doctor, Pharmacist, Administrator, Receptionist).
 */
public class LoginController extends Controller {
	private final LoginView loginView;
	private final StartView startView;

	/**
	 * Constructs a LoginController to manage the login process.
	 */
	public LoginController() {
		this.loginView = new LoginView();
		this.startView = new StartView();
	}

	/**
	 * Navigates the user through the login process. Displays the login screen, authenticates the user,
	 * and redirects them to the appropriate menu based on their role (Patient, Doctor, Pharmacist,
	 * Administrator, Receptionist).
	 * <p>
	 * If authentication fails, the user is prompted to try again.
	 * If the user chooses to exit, the system data is saved and the application closes.
	 */
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

	/**
	 * Retrieves the user with the given ID from the repositories of all user types (Administrator, Doctor, Patient,
	 * Pharmacist, Receptionist).
	 *
	 * @param id the user ID to search for.
	 * @param password the password for authentication.
	 * @return the user corresponding to the given ID, or null if not found.
	 */
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

	/**
	 * Authenticates the user by checking if the provided password matches the stored hashed password.
	 *
	 * @param user the user to authenticate.
	 * @param id the user ID.
	 * @param password the password provided by the user.
	 * @return true if authentication is successful, false otherwise.
	 */
	private boolean authenticate(User user, String id, String password) {
		if (user == null || !(id.equals(user.getId()) && BCrypt.checkpw(password, user.getPasswordHash()))) {
			System.out.println("Authentication failed. Please try again.");
			return false;
		}
		return true;
	}
}
