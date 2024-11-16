package hms.control.administrator.ManageStaff;

import java.util.List;

import hms.boundary.administrator.ManageStaff.UpdateStaffView;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.attributes.Gender;

public class UpdateStaffController extends Controller{
    private final UpdateStaffView updateStaffView;
    private Doctor editingDoctor;
    private Pharmacist editingPharmacist;

    public UpdateStaffController(){
        this.updateStaffView = new UpdateStaffView();
    }
    
    @Override
    public void navigate(){
        updateStaffView.displayHeader();
        int choice = updateStaffView.getRoleChoice();
        if(choice==3) return;
        String tempID = updateStaffView.getID();
        // If doctor chosen
        if(choice==1){
            this.editingDoctor = doctorRepository.getById(tempID);
            if(this.editingDoctor==null){
                updateStaffView.staffDoesNotExist();
                return;
            }
            List<String> details = updateStaffView.getDetails();
            int age = this.updateStaffView.getAge();
            DoctorEditor(details, editingDoctor, age);           
        }
        // If pharmacist chosen
        else{
            this.editingPharmacist = pharmacistRepository.getById(tempID);
            if(this.editingPharmacist==null){
                updateStaffView.staffDoesNotExist();
                return;
            }
            List<String> details = updateStaffView.getDetails();
            int age = this.updateStaffView.getAge();
            PharmacistEditor(details, editingPharmacist, age);     
        }



        // DoctorPharmacistEditor(details, choice);
    }

    private void DoctorEditor(List<String> details, Doctor editingDoctor, int age){
        // TODO: Verify that id is valid format (D001/P001 for example)
        int choice = updateStaffView.printDetails(details, age);
        if (choice==2){
            return;
        }
        editingDoctor.setId(details.get(0));
        editingDoctor.setName(details.get(1));
        if(details.get(2).equals("M")){
            editingDoctor.setGender(Gender.MALE);
        }
        else{
            editingDoctor.setGender(Gender.FEMALE);
        }
        updateStaffView.updateDoctorSuccessful();
    }

    private void PharmacistEditor(List<String> details, Pharmacist editingPharmacist, int age){
        int choice = updateStaffView.printDetails(details, age);
        if (choice==2){
            return;
        }
        editingPharmacist.setId(details.get(0));
        editingPharmacist.setName(details.get(1));
        if(details.get(2).equals("M")){
            editingPharmacist.setGender(Gender.MALE);
        }
        else{
            editingPharmacist.setGender(Gender.FEMALE);
        }
        updateStaffView.updatePharmacistSuccessful();
    }
}
