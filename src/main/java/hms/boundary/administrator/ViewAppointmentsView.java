package hms.boundary.administrator;

import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;

import java.util.List;
import java.util.Scanner;

public class ViewAppointmentsView extends View {
    public void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("1. View All Appointments");
        System.out.println("2. Filter by Status");
        System.out.println("3. Search by Patient ID");
    }

    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Appointment Management");
    }

    public AppointmentStatus getStatusChoice() {
        System.out.println("Enter status to filter (1. Pending, 2. Confirmed, 3. Cancelled, 4. Completed): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: return AppointmentStatus.PENDING;
            case 2: return AppointmentStatus.CONFIRMED;
            case 3: return AppointmentStatus.CANCELLED;
            case 4: return AppointmentStatus.COMPLETED;
            default: return null;
        }
    }

    public String getPatientId() {
        System.out.print("Enter Patient ID: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getUserInput() {
        System.out.print("Enter choice: ");
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1; // Return an invalid option to handle gracefully
        }
    }

    public void displayAppointments(List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            System.out.println("ID: " + appointment.getUUID());
            System.out.println("Patient ID: " + appointment.getPatientId());
            System.out.println("Doctor ID: " + appointment.getDoctorId());
            System.out.println("Date: " + appointment.getDate());
            System.out.println("Time: " + appointment.getTime());
            System.out.println("Status: " + appointment.getAppointmentStatus());
            System.out.println("-----");
        }
    }
}
