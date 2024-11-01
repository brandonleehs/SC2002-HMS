package hms.record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hms.Medicine;

public class AppointmentOutcomeRecord {
	private final LocalDate date;
	private final String serviceType;
	private final List<Medicine> prescribedMedicineList;
	private final String consultationNotes;

	public AppointmentOutcomeRecord(LocalDate date, String serviceType, String consultationNotes) {
		this.date = date;
		this.serviceType = serviceType;
		this.prescribedMedicineList = new ArrayList<Medicine>();
		this.consultationNotes = consultationNotes;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public List<Medicine> getPrescribedMedicineList() {
		return this.prescribedMedicineList;
	}

	public void addPrescribedMedicine(Medicine medicine) {
		this.prescribedMedicineList.add(medicine);
	}

	public String getConsultationNotes() {
		return this.consultationNotes;
	}
}
