package hms.control.administrator.ManageStaff;

import java.util.Map;

import hms.boundary.administrator.ManageStaff.DisplayStaffView;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;

public class DisplayStaffController extends Controller {
    private final DisplayStaffView displayStaffView = new DisplayStaffView();
    private Map<String, Doctor> doctors;
    private Map<String, Pharmacist> pharmacists;
    
    @Override
    public void navigate(){
        doctors = doctorRepository.getMap();
        pharmacists = pharmacistRepository.getMap();
        displayStaffView.displayDoctorList(doctors); // Display all doctors
        displayStaffView.displayPharmacistList(pharmacists); // Display all pharmacists
    }
}
