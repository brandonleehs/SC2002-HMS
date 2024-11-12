package hms.boundary.administrator;

import hms.boundary.View;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.attributes.Gender;

import java.util.Map;
import java.util.Scanner;

public class ManageStaffView extends View {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Manage Hospital Staff");
    }

    public void displayOptions() {
        System.out.println("1. Add Doctor/Pharmacist");
        System.out.println("2. Update Doctor/Pharmacist");
        System.out.println("3. Remove Doctor/Pharmacist");
        System.out.println("4. Display All Doctors and Pharmacists");
    }

    public String getRoleChoice() {
        System.out.print("Enter role (Doctor/Pharmacist): ");
        return scanner.nextLine();
    }

    public String getString() {
        return scanner.nextLine();
    }

    public Gender getGender() {
        System.out.print("Enter Gender (M/F): ");
        String genderInput = scanner.nextLine().toUpperCase();
        return genderInput.equals("M") ? Gender.MALE : Gender.FEMALE;
    }

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

    public void displayRemoveSuccess() {
        System.out.println("User successfully removed.");
    }

    public void displayUserNotFound() {
        System.out.println("User not found.");
    }

    public void displayDoctorList(Map<String, Doctor> doctors) {
        System.out.println("Doctors:");
        doctors.values().forEach(System.out::println);
    }

    public void displayPharmacistList(Map<String, Pharmacist> pharmacists) {
        System.out.println("Pharmacists:");
        pharmacists.values().forEach(System.out::println);
    }
}
