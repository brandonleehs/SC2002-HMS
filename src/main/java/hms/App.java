package hms;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.boundary.PatientMenu;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.entity.user.attributes.Gender;

public class App {

	public static void main(String[] args) {
		// No doctor controller or view classes implemented yet, this is just a very
		// barebones test for
		// functionality
		PatientMenu patientMenu = new PatientMenu();
		patientMenu.displayLogo();
		patientMenu.displayMenu("John Doe");
		Scanner scanner = new Scanner(System.in);
		PatientController patientController = PatientController.getInstance();
		patientController.loadPatientMap("Patient_List.xlsx");
		Doctor doctor = new Doctor("D001", "password", "John Smith", Gender.MALE, 45);
		System.out.println("Enter id:");
		String id = scanner.nextLine();
		System.out.println("Enter password:");
		String password = scanner.nextLine();
		Patient patient = patientController.getPatientMap().get("P1001");
		Appointment appointment = new Appointment(patient.getId(), doctor.getId(), LocalDate.now(),
				LocalTime.of(13, 0));
		patient.scheduleAppointment(doctor, appointment);
		if (id.equals(doctor.getId()) && BCrypt.checkpw(password, doctor.getPasswordHash())) {
			System.out.printf("Welcome, %s.\n", doctor.getName());
			System.out.println("Current date: " + LocalDate.now());
			System.out.println("Current appointments today:");
			Appointment[] appointments = doctor.getSchedule().getScheduleMap().get(LocalDate.now());
			if (appointments == null) {
				System.out.println("You have no appointments today.");
			} else {
				for (Appointment appt : appointments) {
					if (appt != null) {
						System.out.printf("Patient id: %s\nDoctor id: %s\n", appt.getPatientId(), appt.getDoctorId());
						System.out.println(appt.getTime());
					}
				}
			}

		} else {
			System.out.printf("Authentication failed. Please try again.", doctor.getName());
		}
		scanner.close();
	}
}
