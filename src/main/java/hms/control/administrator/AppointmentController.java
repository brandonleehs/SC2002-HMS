package hms.control.administrator;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.administrator.ViewAppointmentsView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.user.Patient;

/**
 * Controller class for managing and displaying a patient's appointments.
 * This class handles the logic of categorizing appointments by status and displaying them through the associated view.
 */
public class AppointmentController extends Controller {
    private final ViewAppointmentsView appointmentsView;
    private Patient patient;

    /**
     * Constructs a new instance of AppointmentController for the given patient.
     *
     * @param patient The patient whose appointments are to be managed and displayed.
     */
    public AppointmentController(Patient patient) {
        this.appointmentsView = new ViewAppointmentsView();
        this.patient = patient;
    }

    /**
     * Navigates through the process of displaying a patient's appointments.
     * It retrieves all appointments for the patient, categorizes them by status, and displays them using the view.
     * If no appointments are found, a message is displayed.
     */
    public void navigate() {
        appointmentsView.displayHeader();
        List<Appointment> allAppointments = patient.getAllAppointmentList();
        if (allAppointments == null) {
            appointmentsView.displayNoAppointments();
            return;
        }

        List<Appointment> pendingAppointments = new ArrayList<Appointment>();
        List<Appointment> confirmedAppointments = new ArrayList<Appointment>();
        List<Appointment> cancelledAppointments = new ArrayList<Appointment>();
        List<Appointment> completedAppointments = new ArrayList<Appointment>();
        for (Appointment appointment : allAppointments) {
            AppointmentStatus status = appointment.getAppointmentStatus();
            switch (status) {
                case PENDING:
                    pendingAppointments.add(appointment);
                    break;
                case CONFIRMED:
                    confirmedAppointments.add(appointment);
                    break;
                case CANCELLED:
                    cancelledAppointments.add(appointment);
                    break;
                case COMPLETED:
                    completedAppointments.add(appointment);
                default:
                    break;
            }
        }

        appointmentsView.displayAppointmentsType(pendingAppointments, doctorRepository, AppointmentStatus.PENDING);
        appointmentsView.displayAppointmentsType(confirmedAppointments, doctorRepository, AppointmentStatus.CONFIRMED);
        appointmentsView.displayAppointmentsType(cancelledAppointments, doctorRepository, AppointmentStatus.CANCELLED);
        appointmentsView.displayAppointmentsType(completedAppointments, doctorRepository, AppointmentStatus.COMPLETED);

    }
}