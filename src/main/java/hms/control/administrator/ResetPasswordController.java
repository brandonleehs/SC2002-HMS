package hms.control.administrator;

import java.util.HashMap;
import java.util.Map;

import hms.boundary.administrator.ResetPasswordView;
import hms.control.Controller;
import hms.entity.user.User;

public class ResetPasswordController extends Controller {
    private final ResetPasswordView resetPasswordView;

    public ResetPasswordController() {
        this.resetPasswordView = new ResetPasswordView();
    }

    @Override
    public void navigate() {
        resetPasswordView.displayHeader();
        Map<String, User> userMap = new HashMap<String, User>();
        userMap.putAll(administratorRepository.getMap());
        userMap.putAll(doctorRepository.getMap());
        userMap.putAll(patientRepository.getMap());
        userMap.putAll(pharmacistRepository.getMap());
        userMap.putAll(receptionistRepository.getMap());

        String id = resetPasswordView.chooseUser();
        User user = null;
        do {
            id = resetPasswordView.chooseUser();
            user = userMap.get(id);
        } while (user == null);
        user.resetPassword();
        resetPasswordView.successMessage(user);
    }
}
