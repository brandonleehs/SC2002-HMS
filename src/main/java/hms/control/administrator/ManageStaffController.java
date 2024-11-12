package hms.control.administrator;

import hms.boundary.administrator.ManageStaffView;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;

import java.util.HashMap;
import java.util.Map;

public class ManageStaffController {
    private final ManageStaffView view;
    private final Map<String, Doctor> doctors;
    private final Map<String, Pharmacist> pharmacists;

    public ManageStaffController() {
        this.view = new ManageStaffView();
        this.doctors = new HashMap<>();
        this.pharmacists = new HashMap<>();
    }

    public void manageStaff() {
        int choice;
        do {
            view.displayHeader();
            view.displayOptions();
            choice = Integer.parseInt(view.getString());

            switch (choice) {
                case 1 -> addStaff();
                case 2 -> updateStaff();
                case 3 -> removeStaff();
                case 4 -> displayStaffList();
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);
    }

    private void addStaff() {
        String role = view.getRoleChoice();
        if ("Doctor".equalsIgnoreCase(role)) {
            Doctor doctor = view.getDoctorDetails();
            doctors.put(doctor.getId(), doctor); // Save doctor to storage
            System.out.println("Doctor added successfully.");
        } else if ("Pharmacist".equalsIgnoreCase(role)) {
            Pharmacist pharmacist = view.getPharmacistDetails();
            pharmacists.put(pharmacist.getId(), pharmacist); // Save pharmacist to storage
            System.out.println("Pharmacist added successfully.");
        } else {
            System.out.println("Invalid role.");
        }
    }

    private void updateStaff() {
        String role = view.getRoleChoice();
        String id = view.getString();

        if ("Doctor".equalsIgnoreCase(role)) {
            Doctor doctor = doctors.get(id);
            if (doctor != null) {
                Doctor updatedDoctor = view.getDoctorDetails(); // Collect updated details
                doctors.put(id, updatedDoctor); // Update the doctor in storage
                System.out.println("Doctor updated successfully.");
            } else {
                view.displayUserNotFound();
            }
        } else if ("Pharmacist".equalsIgnoreCase(role)) {
            Pharmacist pharmacist = pharmacists.get(id);
            if (pharmacist != null) {
                Pharmacist updatedPharmacist = view.getPharmacistDetails(); // Collect updated details
                pharmacists.put(id, updatedPharmacist); // Update the pharmacist in storage
                System.out.println("Pharmacist updated successfully.");
            } else {
                view.displayUserNotFound();
            }
        } else {
            System.out.println("Invalid role.");
        }
    }

    private void removeStaff() {
        String role = view.getRoleChoice();
        String id = view.getString();

        if ("Doctor".equalsIgnoreCase(role)) {
            if (doctors.remove(id) != null) {
                view.displayRemoveSuccess();
            } else {
                view.displayUserNotFound();
            }
        } else if ("Pharmacist".equalsIgnoreCase(role)) {
            if (pharmacists.remove(id) != null) {
                view.displayRemoveSuccess();
            } else {
                view.displayUserNotFound();
            }
        } else {
            System.out.println("Invalid role.");
        }
    }

    private void displayStaffList() {
        view.displayDoctorList(doctors); // Display all doctors
        view.displayPharmacistList(pharmacists); // Display all pharmacists
    }
}
