package hms.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.boundary.InputHandler;
import hms.boundary.LoginView;
import hms.boundary.Prompt;
import hms.boundary.View;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;
import hms.entity.user.User;

public class LoginController extends Controller {
	private final LoginView loginView;

	public LoginController() {
		this.loginView = new LoginView();
	}

	@Override
	public void navigate() {
		View.displayLogo();
		this.loginView.displayHeader();
		boolean login = false;
		User user = null;
		while (true) {
			do {
				Prompt.displayIdPrompt();
				String id = InputHandler.getString();
				Prompt.displayPasswordPrompt();
				String password = InputHandler.getString();
				user = getUser(id, password);
				login = authenticate(user, id, password);
			} while (!login);

			if (user instanceof Patient) {
				PatientMenuController patientMenuController = new PatientMenuController((Patient) user);
				patientMenuController.navigate();

			} else if (user instanceof Doctor) {

			} else if (user instanceof Pharmacist) {

			} else {

			}
		}
	}

	private User getUser(String id, String password) {
		Map<String, User> userMap = new HashMap<String, User>();
		userMap.putAll(administratorRepository.getMap());
		userMap.putAll(doctorRepository.getMap());
		userMap.putAll(patientRepository.getMap());
		userMap.putAll(pharmacistRepository.getMap());
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
