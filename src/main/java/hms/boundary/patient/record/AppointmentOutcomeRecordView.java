package hms.boundary.patient.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hms.boundary.View;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Patient;

/**
 * The AppointmentOutcomeRecordView class provides functionalities to display
 * details of appointment records for a patient, including appointment details,
 * prescribed medicines, and unprescribed appointments.
 */
public class AppointmentOutcomeRecordView extends View {
	/**
	 * Displays the header for the "Appointment Records" view.
	 */
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Appointment Records");
	}

	/**
	 * Displays a list of all records for a given patient. If no records are found,
	 * an appropriate message is displayed.
	 *
	 * @param patient the patient whose appointment records are to be displayed.
	 */
	public void displayRecords(Patient patient) {
		displayHeader();
		List<AppointmentOutcomeRecord> records = patient.getAppointmentOutcomeRecordList();
		if (records.isEmpty()) {
			System.out.println("No records found.");
		}
		for (int i = 0; i < records.size(); i++) {
			AppointmentOutcomeRecord appointmentOutcomeRecord = records.get(i);
			displayAppointmentHeader(i);
			System.out.println(String.format("UUID: %s", appointmentOutcomeRecord.getUUID().toString()));
			System.out.println(String.format("Date: %s", appointmentOutcomeRecord.getDate()));
			System.out.println(String.format("Service Type: %s", appointmentOutcomeRecord.getServiceType()));
			System.out.println(String.format("Diagnosis: %s", appointmentOutcomeRecord.getConsultationNotes()));

			displayPrescriptionTable(appointmentOutcomeRecord);
		}
	}

	private void displayAppointmentHeader(int i) {
		String text = String.format("Appointment %d", i + 1);
		int leftPadding = (WIDTH - text.length() - 2) / 2;
		int rightPadding = leftPadding;
		if ((WIDTH - text.length()) % 2 != 0) {
			rightPadding++;
		}

		System.out.println("-".repeat(WIDTH));
		System.out.println("|" + " ".repeat(leftPadding) + text + " ".repeat(rightPadding) + "|");
		System.out.println("-".repeat(WIDTH));
	}

	/**
	 * Displays a table of prescribed medicines for a given appointment outcome
	 * record. If no medicines are prescribed, an appropriate message is displayed.
	 *
	 * @param appointmentOutcomeRecord the appointment outcome record containing
	 *                                 prescribed medicines.
	 */
	public void displayPrescriptionTable(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		String title = "Medicine Prescribed";
		int leftPadding = (WIDTH - title.length()) / 2;
		int rightPadding = leftPadding;
		if ((WIDTH - title.length()) % 2 != 0) {
			rightPadding++;
		}
		String format = "| %-" + 5 + "s | %-" + (WIDTH - 37) + "s | %-" + 6 + "s | %-" + 13 + "s |\n";

		System.out.println("-".repeat(WIDTH));
		System.out.println(" ".repeat(leftPadding) + title + " ".repeat(rightPadding));
		System.out.println("-".repeat(WIDTH));

		Map<Medicine, Integer> prescribedMedicineList = appointmentOutcomeRecord.getPrescribedMedicineMap();
		if (prescribedMedicineList.isEmpty()) {
			System.out.println("No medicine prescribed.");
		} else {
			System.out.printf(format, "Index", "Medicine Name", "Amount", "Status");
			System.out.printf(format, "-".repeat(5), "-".repeat(WIDTH - 37), "-".repeat(6), "-".repeat(13));
			int index = 1;
			for (Map.Entry<Medicine, Integer> entry : prescribedMedicineList.entrySet()) {
				System.out.printf(format, index, entry.getKey().getName(), entry.getValue(),
						entry.getKey().getMedicineStatus());
				index++;
			}
		}

	}

	/**
	 * Displays a list of unprescribed appointment outcome records for a given
	 * patient. Only appointments with undispensed medicines are shown.
	 *
	 * @param patient the patient whose unprescribed appointment records are to be
	 *                displayed.
	 * @return a list of unprescribed appointment outcome records.
	 */
	public List<AppointmentOutcomeRecord> displayUnprescribedAppointmentOutcomeRecord(Patient patient) {
		List<AppointmentOutcomeRecord> records = patient.getAppointmentOutcomeRecordList();
		List<AppointmentOutcomeRecord> return_records = new ArrayList<>();
		displayHeader();
		if (records.isEmpty()) {
			System.out.println("No records found.");
		}
		int index = 1;
		for (AppointmentOutcomeRecord appointmentOutcomeRecord : records) {
			// Check if the medicine for this has appointment been dispensed
			// If all dispensed, do not print the appointment anymore
			if (appointmentOutcomeRecord.CheckIfUnprescribedMedicineExists()) {
				System.out.println("Appointment Index: " + index++);
				System.out.println(String.format("Date: %s", appointmentOutcomeRecord.getDate()));
				System.out.println(String.format("Service Type: %s", appointmentOutcomeRecord.getServiceType()));
				System.out.println(String.format("Diagnosis: %s", appointmentOutcomeRecord.getConsultationNotes()));
				System.out.println("+" + "=".repeat(WIDTH - 2) + "+");
				displayPrescriptionTable(appointmentOutcomeRecord);
				System.out.println("+" + "=".repeat(WIDTH - 2) + "+");
				return_records.add(appointmentOutcomeRecord);
			}
		}
		return return_records;
	}
}
