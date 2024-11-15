package hms.control.administrator;

import hms.boundary.administrator.ViewAppointmentsView;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentController {
    private final ViewAppointmentsView appointmentsView;
    private final List<Appointment> appointmentList;

    public AppointmentController() {
        this.appointmentsView = new ViewAppointmentsView();
        this.appointmentList = new ArrayList<>();
    }

    public void viewAppointments() {
        appointmentsView.displayHeader();
        appointmentsView.displayOptions();
        int choice = appointmentsView.getUserInput();

        switch (choice) {
            case 1:
                displayAllAppointments();
                break;
            case 2:
                filterAppointmentsByStatus();
                break;
            case 3:
                searchAppointmentsByPatientId();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void displayAllAppointments() {
        appointmentsView.displayAppointments(appointmentList);
    }

    private void filterAppointmentsByStatus() {
        AppointmentStatus status = appointmentsView.getStatusChoice();
        if (status == null) {
            System.out.println("Invalid status choice.");
            return;
        }

        List<Appointment> filteredAppointments = appointmentList.stream()
                .filter(appointment -> appointment.getAppointmentStatus() == status)
                .collect(Collectors.toList());

        appointmentsView.displayAppointments(filteredAppointments);
    }

    private void searchAppointmentsByPatientId() {
        String patientId = appointmentsView.getPatientId();
        List<Appointment> patientAppointments = appointmentList.stream()
                .filter(appointment -> appointment.getPatientId().equals(patientId))
                .collect(Collectors.toList());

        if (patientAppointments.isEmpty()) {
            System.out.println("No appointments found for Patient ID: " + patientId);
        } else {
            appointmentsView.displayAppointments(patientAppointments);
        }
    }
}