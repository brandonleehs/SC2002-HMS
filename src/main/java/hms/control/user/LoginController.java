package hms.control.user;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.boundary.user.LoginView;
import hms.control.Controller;
import hms.control.doctor.DoctorMenuController;
import hms.control.patient.PatientMenuController;
import hms.entity.appointment.Appointment;
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
		Patient patient = patientRepository.getById("P1001");
		Doctor doctor = doctorRepository.getById("D001");
		Appointment appt = new Appointment(patient.getId(), doctor.getId(), LocalDate.of(2024, 11, 5),
				LocalTime.of(9, 30));
		patient.scheduleAppointment(doctor, appt);
//		doctor.acceptAppointment(appt);
//
////		doctor.completeAppointment(patient, appt, "Consultation", "Hypertension. Lifestyle change recommended.");
////		doctor.prescribeMedicine(new Medicine("Paracetamol"), appt.getAppointmentOutcomeRecord());
////		doctor.prescribeMedicine(new Medicine("Ibuprofen"), appt.getAppointmentOutcomeRecord());
//
		Appointment appt1 = new Appointment(patient.getId(), doctor.getId(), LocalDate.of(2024, 11, 5),
				LocalTime.of(10, 30));
		patient.scheduleAppointment(doctor, appt1);

		Appointment appt2 = new Appointment(patient.getId(), doctor.getId(), LocalDate.of(2024, 12, 5),
				LocalTime.of(11, 30));
		patient.scheduleAppointment(doctor, appt2);
		doctor.completeAppointment(patient, appt2, "Consultation", "Fever. Rest recommended. ");

		Appointment appt3 = new Appointment(patient.getId(), doctor.getId(), LocalDate.of(2024, 11, 6),
				LocalTime.of(11, 30));
		patient.scheduleAppointment(doctor, appt3);

//		doctor.acceptAppointment(appt1);

//		doctor.completeAppointment(patient, appt1, "Consultation", "Fever. Rest recommended. ");
//		doctor.prescribeMedicine(new Medicine("Paracetamol"), appt1.getAppointmentOutcomeRecord());
//		doctor.prescribeMedicine(new Medicine("Ibuprofen"), appt1.getAppointmentOutcomeRecord());

		boolean login = false;
		User user = null;
		while (true) {
			do {
				View.displayLogo();
				this.loginView.displayHeader();
				this.loginView.displayIdPrompt();
				String id = InputHandler.getString();
				this.loginView.displayPasswordPrompt();
				String password = InputHandler.getString();
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
