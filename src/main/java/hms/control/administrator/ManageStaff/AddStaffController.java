package hms.control.administrator.ManageStaff;

import java.util.List;

import hms.boundary.administrator.ManageStaff.AddStaffView;
import hms.control.Controller;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;
import hms.entity.user.attributes.Gender;

public class AddStaffController extends Controller{
    private final AddStaffView addStaffView;
    private Doctor newDoctor;
    private Pharmacist newPharmacist;

    public AddStaffController(){
        this.addStaffView = new AddStaffView();
    }
    
    @Override
    public void navigate(){
        addStaffView.displayHeader();
        int choice = addStaffView.getRoleChoice();
        if(choice==3) return;
        List<String> details = addStaffView.getDetails();
        DoctorPharmacistBuilder(details, choice);
    }

    private void DoctorPharmacistBuilder(List<String> details, int choice){
        // TODO: Verify that id is valid format (D001/P001 for example)
        String password, name, id;
        Gender gender;
        id = details.get(0);
        name = details.get(1);
        if (doctorRepository.getById(id)==null){
            if(details.get(2).equals("M")){
                gender=Gender.MALE;
            }
            else{
                gender=Gender.FEMALE;
            }
            password = "password";
            int age = this.addStaffView.getAge();
            if (choice==1){
                this.newDoctor = new Doctor(id, password, name, gender, age);
                doctorRepository.addDoctor(id, this.newDoctor);
                addStaffView.addDoctorSuccessful();
                
            }
            else{
                this.newPharmacist = new Pharmacist(id, password, name, gender, age);
                pharmacistRepository.addPharmacist(id, this.newPharmacist);
                addStaffView.addPharmacistSuccessful();
            }
        }
        else{
            addStaffView.userExists();
        }
    }
}
