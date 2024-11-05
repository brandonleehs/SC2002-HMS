package hms.boundary;

public class LoginView extends View {

//	@Override
//	public void show() {
//		displayLogo();
//		displayBorderedText(WIDTH, "Login");
//		boolean login = false;
//		User user = null;
//		while (true) {
//			do {
//				System.out.print("Enter id: ");
//				String id = scanner.nextLine();
//				System.out.print("Enter password: ");
//				String password = scanner.nextLine();
//				user = getUser(id, password);
//				login = authenticate(user, id, password);
//			} while (!login);
//
//			if (user instanceof Patient) {
//				PatientMenuView patientMenu = new PatientMenuView((Patient) user);
//				patientMenu.show();
//			} else if (user instanceof Doctor) {
//				DoctorMenuView doctorMenu = new DoctorMenuView((Doctor) user);
//				doctorMenu.show();
//			} else if (user instanceof Pharmacist) {
//				PharmacistMenuView pharmacistMenu = new PharmacistMenuView((Pharmacist) user);
//				pharmacistMenu.show();
//			} else {
//				AdministratorMenuView administratorMenu = new AdministratorMenuView((Administrator) user);
//				administratorMenu.show();
//			}
//		}
//	}

//	public String getId() {
//		return scanner.nextLine();
//	}
//
//	public String getPassword() {
//		return scanner.nextLine();
//	}

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Login");
	}

//	public void displayIdPrompt() {
//		System.out.print("Enter id: ");
//	}
//
//	public void displayPasswordPrompt() {
//		System.out.print("Enter password: ");
//	}

//	private User getUser(String id, String password) {
//		Map<String, User> userMap = new HashMap<String, User>();
//		userMap.putAll(administratorRepository.getMap());
//		userMap.putAll(doctorRepository.getMap());
//		userMap.putAll(patientRepository.getMap());
//		userMap.putAll(pharmacistRepository.getMap());
//		User user = userMap.get(id);
//		return user;
//	}
//
//	private boolean authenticate(User user, String id, String password) {
//		if (user == null || !(id.equals(user.getId()) && BCrypt.checkpw(password, user.getPasswordHash()))) {
//			System.out.println("Authentication failed. Please try again.");
//			return false;
//		}
//		return true;
//	}
}