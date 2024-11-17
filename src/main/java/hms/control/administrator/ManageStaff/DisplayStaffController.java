package hms.control.administrator.ManageStaff;

import java.util.Map;

import hms.boundary.administrator.ManageStaff.DisplayStaffView;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.Receptionist;

public class DisplayStaffController extends Controller {
	private final DisplayStaffView displayStaffView = new DisplayStaffView();
	private Map<String, Doctor> doctors;
	private Map<String, Pharmacist> pharmacists;
	private Map<String, Receptionist> receptionists;

	@Override
	public void navigate() {
		doctors = doctorRepository.getMap();
		pharmacists = pharmacistRepository.getMap();
		receptionists = receptionistRepository.getMap();
		displayStaffView.displayHeader();
		displayStaffView.displayDoctorList(doctors);
		displayStaffView.displayPharmacistList(pharmacists);
		displayStaffView.displayReceptionistList(receptionists);
	}
}
