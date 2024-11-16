package hms.boundary.receptionist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.entity.user.attributes.BloodType;
import hms.entity.user.attributes.Gender;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.exceptions.InvalidDateException;

public class AddPatientView extends View {
    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Register New Patient");
    }

    public List<Object> getDetails() {
        List<Object> patientDetails = new ArrayList<Object>();

        System.out.print("Enter Patient's name: ");
        patientDetails.add(InputHandler.getString());

        System.out.print("Enter Patient's date of birth (YYYY-MM-DD): ");
		LocalDate birthdate;
		try{
			birthdate = InputHandler.getDate();
		} catch (InvalidDateException e) {
            errorMessage();
			return null;
		}
        patientDetails.add(birthdate);

        System.out.println("Enter Gender:\n1: Male\n 2: Female");
        int choice;
        try {
            choice = InputHandler.getChoice(1, 2);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
            errorMessage();
            return null;
        }
        switch (choice) {
            case 1:
                patientDetails.add(Gender.MALE);
                break;
            case 2:
                patientDetails.add(Gender.FEMALE);
                break;
            default:
                patientDetails.add(null);
                break;
        }

        System.out.print("Enter Patient blood type: ");
        BloodType bloodtype = BloodType.getBloodType(InputHandler.getString());
        if (bloodtype == null) {
            errorMessage();
            return null;
        }
        patientDetails.add(bloodtype);

        System.out.print("Enter Patient's phone number: ");
        patientDetails.add(InputHandler.getString());

        System.out.print("Enter Patient's email address: ");
        patientDetails.add(InputHandler.getString());

        return patientDetails;
    }

    private void errorMessage() {
        System.out.println("Error! Try again.");
    }

    public void successMessage(String id) {
        System.out.println("New patient successfully registered.\nPatient id is " + id);
    }
}
