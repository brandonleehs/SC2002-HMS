package hms.boundary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.entity.user.Administrator;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;
import hms.entity.user.User;

public class LoginView extends View {

	@Override
	public void show() {
		displayLogo();
		displayBorderedText(WIDTH, "Login");
		boolean login = false;
		User user = null;
		do {
			System.out.print("Enter id: ");
			String id = scanner.nextLine();
			System.out.print("Enter password: ");
			String password = scanner.nextLine();
			user = getUser(id, password);
			login = authenticate(user, id, password);
		} while (!login);

		if (user instanceof Patient) {
			PatientMenuView patientMenu = new PatientMenuView((Patient) user);
			patientMenu.show();
		} else if (user instanceof Doctor) {
			DoctorMenuView doctorMenu = new DoctorMenuView((Doctor) user);
			doctorMenu.show();
		} else if (user instanceof Pharmacist) {
			PharmacistMenuView pharmacistMenu = new PharmacistMenuView((Pharmacist) user);
			pharmacistMenu.show();
		} else {
			AdministratorMenuView administratorMenu = new AdministratorMenuView((Administrator) user);
			administratorMenu.show();
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