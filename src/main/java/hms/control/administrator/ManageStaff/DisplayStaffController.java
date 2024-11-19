package hms.control.administrator.ManageStaff;

import java.util.Map;

import hms.boundary.administrator.ManageStaff.DisplayStaffView;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.Receptionist;

/**
 * Controller class for displaying the staff members in the hospital management system.
 * Handles the retrieval and presentation of doctors, pharmacists, and receptionists.
 */
public class DisplayStaffController extends Controller {
	private final DisplayStaffView displayStaffView = new DisplayStaffView();
	private Map<String, Doctor> doctors;
	private Map<String, Pharmacist> pharmacists;
	private Map<String, Receptionist> receptionists;

	/**
	 * Handles the workflow for displaying staff details.
	 */
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
