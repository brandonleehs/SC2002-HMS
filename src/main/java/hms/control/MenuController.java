package hms.control;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.boundary.Prompt;
import hms.entity.user.User;

public abstract class MenuController extends Controller {
    protected void checkNewUser(User user) {
        if (BCrypt.checkpw("password", user.getPasswordHash())) {
            boolean reset = false;
            while (!reset) {
                reset = user.setPassword(Prompt.displayNewPasswordPrompt());
            }
        }
    }
}
