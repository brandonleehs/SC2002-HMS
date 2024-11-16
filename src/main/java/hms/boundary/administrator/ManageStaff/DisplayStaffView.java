package hms.boundary.administrator.ManageStaff;

import java.util.Map;

import hms.boundary.View;
import hms.entity.user.Doctor;
import hms.entity.user.Pharmacist;

public class DisplayStaffView extends View {
	private final String format = "| %-" + 5 + "s | %-" + 5 + "s | %-" + (WIDTH - 20) + "s |\n";

	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Hospital Staff");
	}

	public void displayDoctorList(Map<String, Doctor> doctors) {
		System.out.println("Doctors:");
		if (doctors.isEmpty()) {
			System.out.println("No doctors in records.");
		} else {
			System.out.printf(format, "Index", "ID", "Doctor Name");
			System.out.printf(format, "-".repeat(5), "-".repeat(5), "-".repeat(WIDTH - 20));
			int u = 0;
			for (Map.Entry<String, Doctor> entry : doctors.entrySet()) {
				System.out.printf(format, u + 1, entry.getKey(), entry.getValue().getName());
				u++;
			}
		}
	}

	public void displayPharmacistList(Map<String, Pharmacist> pharmacists) {
		System.out.println("Pharmacists:");
		if (pharmacists.isEmpty()) {
			System.out.println("No doctors in records.");
		} else {
			System.out.printf(format, "Index", "ID", "Pharmacist Name");
			System.out.printf(format, "-".repeat(5), "-".repeat(5), "-".repeat(WIDTH - 20));
			int u = 0;
			for (Map.Entry<String, Pharmacist> entry : pharmacists.entrySet()) {
				System.out.printf(format, u + 1, entry.getKey(), entry.getValue().getName());
				u++;
			}
		}
	}
}
