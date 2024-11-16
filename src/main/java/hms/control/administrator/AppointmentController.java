package hms.control.administrator;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.administrator.ViewAppointmentsView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.user.Patient;

public class AppointmentController extends Controller {
    private final ViewAppointmentsView appointmentsView;
    private Patient patient;

    public AppointmentController(Patient patient) {
        this.appointmentsView = new ViewAppointmentsView();
        this.patient = patient;
    }

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
                case AppointmentStatus.PENDING:
                    pendingAppointments.add(appointment);
                    break;
                case AppointmentStatus.CONFIRMED:
                    confirmedAppointments.add(appointment);
                    break;
                case AppointmentStatus.CANCELLED:
                    cancelledAppointments.add(appointment);
                    break;
                case AppointmentStatus.COMPLETED:
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