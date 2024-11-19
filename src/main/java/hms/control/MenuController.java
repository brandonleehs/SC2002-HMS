package hms.control;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hms.boundary.Prompt;
import hms.entity.user.User;

/**
 * Abstract class that extends the `Controller` class, providing additional functionality
 * for managing menu navigation and user password checks in the Hospital Management System (HMS).
 * This class serves as a base for all menu-related controllers in the application.
 */
public abstract class MenuController extends Controller {
    /**
     * Checks if the user is using the default password ("password") and prompts them
     * to change it if necessary.
     * If the user is using the default password, this method will display a prompt
     * for the user to reset their password and ensure that the password is successfully changed.
     *
     * @param user The user object whose password is being checked.
     */
    protected void checkNewUser(User user) {
        if (BCrypt.checkpw("password", user.getPasswordHash())) {
            boolean reset = false;
            while (!reset) {
                reset = user.setPassword(Prompt.displayNewPasswordPrompt());
            }
        }
    }
}
