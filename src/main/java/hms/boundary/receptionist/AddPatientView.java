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

/**
 * This class represents the view responsible for handling the process of adding a new patient to the system.
 * It collects patient details such as name, date of birth, gender, blood type, phone number, and email address.
 * The details are then returned in a list format for use in other parts of the application.
 */
public class AddPatientView extends View {

    /**
     * Displays the header for the "Register New Patient" view.
     * This method prints a bordered title to indicate the beginning of the patient registration process.
     */
    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Register New Patient");
    }

    /**
     * Collects the details of the new patient, including name, date of birth, gender, blood type, phone number,
     * and email address. The method prompts the user for input and validates the responses.
     * 
     * @return a list of objects containing the patient's details. If any input is invalid, returns null.
     */
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

        System.out.println("Enter Gender:\r\n1: Male\r\n2: Female");
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

    /**
     * Displays an error message to the user indicating that an invalid input was provided.
     * This method is called when an exception occurs during input collection.
     */
    private void errorMessage() {
        System.out.println("Error! Try again.");
    }

    /**
     * Displays a success message to the user indicating that the new patient has been successfully registered.
     * 
     * @param id the ID of the newly registered patient
     */
    public void successMessage(String id) {
        System.out.println("New patient successfully registered.\nPatient id is " + id);
    }
}
