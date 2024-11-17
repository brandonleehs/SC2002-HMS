package hms.boundary.administrator;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.user.User;

public class ResetPasswordView extends View {
    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Reset Password");
    }
    
    public String chooseUser() {
        System.out.print("Enter ID of user to reset: ");
        return InputHandler.getString();
    }
    
    public void successMessage(User user) {
        System.out.println("Password for user " + user.getId() + " " + user.getName() + " successfully reset.");
    }
}
