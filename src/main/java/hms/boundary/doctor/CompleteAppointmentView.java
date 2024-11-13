package hms.boundary.doctor;

public class CompleteAppointmentView extends UpdatePatientMedicalRecordView {
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Complete Appointment");
	}

	public void displayNoAppointments() {
		System.out.println("No Appointments. Returning to main menu.");
	}

	public void displayApptOptionPrompt() {
		System.out.println("Please enter the appointment index:");
	}

	public void displaySetConsultationNotesPrompt() {
		System.out.println("Enter consultation notes:");
	}

	public void displayServiceTypePrompt() {
		System.out.println("Enter service type:");
	}

	public void displayPrescriptionChoicePrompt() {
        System.out.print("Enter numer of prescriptions to give (1-10): ");
    }

	public void displayAddPrescriptionNamePrompt() {
        System.out.print("Enter index of Medicine to be added: ");
    }

	public void displayAddPrescriptionAmountPrompt() {
        System.out.print("Enter amount of Medicine to be prescribed: ");
    }
}