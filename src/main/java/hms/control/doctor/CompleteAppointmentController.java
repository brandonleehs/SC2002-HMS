package hms.control.doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hms.boundary.doctor.CompleteAppointmentView;
import hms.boundary.doctor.ScheduleView;
import hms.control.Controller;
import hms.control.pharmacist.ShowMedicationInventoryController;
import hms.entity.appointment.Appointment;
import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineStatus;
import hms.entity.user.Doctor;

public class CompleteAppointmentController extends Controller {
	private ScheduleView scheduleView;
	private CompleteAppointmentView completeAppointmentView;
	private ShowMedicationInventoryController showMedicationInventoryController = new ShowMedicationInventoryController();
	private Doctor doctor;
	private Map<String, List<Integer>> medicines = new HashMap<>();

	public CompleteAppointmentController(Doctor doctor) {
		this.scheduleView = new ScheduleView();
		this.completeAppointmentView = new CompleteAppointmentView();
		this.doctor = doctor;
	}

	public void navigate() {
		completeAppointmentView.displayHeader();

		if (doctor.getConfirmedAppointmentList().isEmpty()) {
			completeAppointmentView.displayNoAppointments();
			return;
		}
		scheduleView.displayUpcomingAppointments(doctor, patientRepository);
		int choice = completeAppointmentView.displayApptOptionPrompt(doctor.getConfirmedAppointmentList().size());
		if (choice == -1)
			return;

		Appointment appointment = doctor.getConfirmedAppointmentList().get(choice);

		String serviceType = completeAppointmentView.displayServiceTypePrompt();
		String consultationNotes = completeAppointmentView.displaySetConsultationNotesPrompt();

		int numPre = completeAppointmentView.displayPrescriptionChoicePrompt();
		if (numPre == -1)
			return;

		// Add list for medicine set
		medicines = medicineInventory.getFullMedicine();
		List<String> medicineNames = medicineInventory.getMedicineNames();
		HashMap<Medicine, Integer> prescribed_medicines = new HashMap<>();
		// Get choice with amount from doctor
		for (int i = 0; i < numPre; i++) {
			int medicineChoice, medicineAmount = 0;
			showMedicationInventoryController.navigate();
			medicineChoice = completeAppointmentView.displayAddPrescriptionNamePrompt(medicines.keySet().size());
			if (medicineChoice == -1)
				return;

			medicineAmount = completeAppointmentView.displayAddPrescriptionAmountPrompt();
			if (medicineAmount == -1)
				return;

			Medicine prescribed_medicine = new Medicine(medicineNames.get(medicineChoice));
			prescribed_medicine.setMedicineStatus(MedicineStatus.PENDING);
			prescribed_medicines.put(prescribed_medicine, medicineAmount);

			
		completeAppointmentView.SuccessfulPrescription();
		}

		doctor.completeAppointment(patientRepository.getById(appointment.getPatientId()), appointment, serviceType,
				consultationNotes, prescribed_medicines);
	}
}