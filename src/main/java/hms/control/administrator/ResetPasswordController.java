package hms.control.administrator;

import java.util.HashMap;
import java.util.Map;

import hms.boundary.administrator.ResetPasswordView;
import hms.control.Controller;
import hms.entity.user.User;

/**
 * Controller class for handling password reset functionality in the hospital management system.
 * This class allows an administrator to reset the password for any user (Doctor, Pharmacist, Receptionist, or Patient) in the system.
 */
public class ResetPasswordController extends Controller {
    private final ResetPasswordView resetPasswordView;

    /**
     * Constructs a new instance of ResetPasswordController and initializes the resetPasswordView.
     */
    public ResetPasswordController() {
        this.resetPasswordView = new ResetPasswordView();
    }

    /**
     * Navigates through the process of resetting a user's password.
     * This method displays a header, prompts the administrator to select a user, and then resets the user's password.
     * Once the password is reset, a success message is displayed.
     */
    @Override
    public void navigate() {
        resetPasswordView.displayHeader();
        Map<String, User> userMap = new HashMap<String, User>();
        userMap.putAll(administratorRepository.getMap());
        userMap.putAll(doctorRepository.getMap());
        userMap.putAll(patientRepository.getMap());
        userMap.putAll(pharmacistRepository.getMap());
        userMap.putAll(receptionistRepository.getMap());

        String id;
        User user = null;
        do {
            id = resetPasswordView.chooseUser();
            user = userMap.get(id);
        } while (user == null);
        user.resetPassword();
        resetPasswordView.successMessage(user);
    }
}
