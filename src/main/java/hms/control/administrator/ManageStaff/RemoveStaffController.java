package hms.control.administrator.ManageStaff;

import java.util.ArrayList;
import java.util.List;

import hms.boundary.administrator.ManageStaff.RemoveStaffView;
import hms.control.Controller;
import hms.entity.appointment.Appointment;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.entity.user.Pharmacist;
import hms.entity.user.Receptionist;

public class RemoveStaffController extends Controller {
	private final RemoveStaffView removeStaffView;
	private Doctor removingDoctor;
	private Pharmacist removingPharmacist;
	private Receptionist removingReceptionist;

	public RemoveStaffController() {
		removeStaffView = new RemoveStaffView();
	}

	@Override
	public void navigate() {
		removeStaffView.displayHeader();
		int choice = removeStaffView.getRoleChoice();
		if (choice == 4) {
			return;
		}
		String tempID = removeStaffView.getID();
		switch (choice) {
			case 1:
				removingDoctor = doctorRepository.getById(tempID);
				if (removingDoctor == null) {
					removeStaffView.staffDoesNotExist();
					return;
				}
				List<Appointment> apptList = new ArrayList<Appointment>();
				apptList.addAll(removingDoctor.getPendingAppointmentList());
				apptList.addAll(removingDoctor.getConfirmedAppointmentList());
				for (Appointment appt : apptList) {
					Patient patient = patientRepository.getById(appt.getPatientId());
					patient.cancelAppointment(removingDoctor, appt);
				}
				doctorRepository.removeById(tempID);
				break;
			
			case 2:
				removingPharmacist = pharmacistRepository.getById(tempID);
				if (removingPharmacist == null) {
					removeStaffView.staffDoesNotExist();
					return;
				}
				pharmacistRepository.removeById(tempID);
				break;
			
			case 3:
				removingReceptionist = receptionistRepository.getById(tempID);
				if (removingReceptionist == null) {
					removeStaffView.staffDoesNotExist();
					return;
				}
				receptionistRepository.removeById(tempID);
				break;
		}
		removeStaffView.staffRemoveSuccessful();
	}
}
