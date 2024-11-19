package hms.boundary.administrator;

import java.util.Map;
import java.util.Scanner;

import hms.boundary.View;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.attributes.Gender;

/**
 * The ManageStaffView class provides the user interface for managing hospital
 * staff, including adding, updating, removing, and displaying doctors and
 * pharmacists.
 */
public class ManageStaffView extends View {

	private final Scanner scanner = new Scanner(System.in);

	/**
	 * Displays the header for the "Manage Hospital Staff" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Manage Hospital Staff");
	}

	/**
	 * Displays the options for managing hospital staff.
	 */
	public void displayOptions() {
		System.out.println("1. Add Doctor/Pharmacist");
		System.out.println("2. Update Doctor/Pharmacist");
		System.out.println("3. Remove Doctor/Pharmacist");
		System.out.println("4. Display All Doctors and Pharmacists");
	}

	/**
	 * Prompts the user to enter the role (Doctor/Pharmacist) and returns the input.
	 *
	 * @return the role choice entered by the user (Doctor/Pharmacist).
	 */
	public String getRoleChoice() {
		System.out.print("Enter role (Doctor/Pharmacist): ");
		return scanner.nextLine();
	}

	/**
	 * Reads and returns a string input from the user.
	 *
	 * @return the string input entered by the user.
	 */
	public String getString() {
		return scanner.nextLine();
	}

	/**
	 * Prompts the user to enter a gender (M/F) and returns the corresponding Gender
	 * enum.
	 *
	 * @return the gender as a Gender enum (MALE or FEMALE).
	 */
	public Gender getGender() {
		System.out.print("Enter Gender (M/F): ");
		String genderInput = scanner.nextLine().toUpperCase();
		return genderInput.equals("M") ? Gender.MALE : Gender.FEMALE;
	}

	/**
	 * Prompts the user to enter details for a doctor and returns a Doctor object
	 * with the entered information.
	 *
	 * @return a Doctor object with the details provided by the user.
	 */
	public Doctor getDoctorDetails() {
		System.out.print("Enter ID: ");
		String id = getString();
		System.out.print("Enter Password: ");
		String password = getString();
		System.out.print("Enter Name: ");
		String name = getString();
		System.out.print("Enter Gender (M/F): ");
		Gender gender = getGender();
		System.out.print("Enter Age: ");
		int age = Integer.parseInt(getString());

		return new Doctor(id, password, name, gender, age);
	}

	/**
	 * Prompts the user to enter details for a pharmacist and returns a Pharmacist
	 * object with the entered information.
	 *
	 * @return a Pharmacist object with the details provided by the user.
	 */
	public Pharmacist getPharmacistDetails() {
		System.out.print("Enter ID: ");
		String id = getString();
		System.out.print("Enter Password: ");
		String password = getString();
		System.out.print("Enter Name: ");
		String name = getString();
		System.out.print("Enter Gender (M/F): ");
		Gender gender = getGender();
		System.out.print("Enter Age: ");
		int age = Integer.parseInt(getString());

		return new Pharmacist(id, password, name, gender, age);
	}

	/**
	 * Displays a message indicating that the user was successfully removed.
	 */
	public void displayRemoveSuccess() {
		System.out.println("User successfully removed.");
	}

	/**
	 * Displays a message indicating that the user was not found.
	 */
	public void displayUserNotFound() {
		System.out.println("User not found.");
	}

	/**
	 * Displays a list of all doctors.
	 *
	 * @param doctors a map of doctor IDs to Doctor objects.
	 */
	public void displayDoctorList(Map<String, Doctor> doctors) {
		System.out.println("Doctors:");
		doctors.values().forEach(System.out::println);
	}

	/**
	 * Displays a list of all pharmacists.
	 *
	 * @param pharmacists a map of pharmacist IDs to Pharmacist objects.
	 */
	public void displayPharmacistList(Map<String, Pharmacist> pharmacists) {
		System.out.println("Pharmacists:");
		pharmacists.values().forEach(System.out::println);
	}
}
