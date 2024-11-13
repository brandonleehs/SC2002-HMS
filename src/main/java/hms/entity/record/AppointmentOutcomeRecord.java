package hms.entity.record;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

import hms.entity.medicine.Medicine;

public class AppointmentOutcomeRecord {
	private final UUID uuid;
	private final LocalDate date;
	private String serviceType;
	private HashMap<Medicine, Integer> prescribedMedicineList;
	private String consultationNotes;

	public AppointmentOutcomeRecord(LocalDate date, String serviceType, String consultationNotes, UUID uuid) {
		this.uuid = uuid;
		this.date = date;
		this.serviceType = serviceType;
		this.consultationNotes = consultationNotes;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setConsultationNotes(String consultationNotes) {
		this.consultationNotes = consultationNotes;
	}

	public void addConsultationNotes(String consultationNotes) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(this.consultationNotes);
		stringBuilder.append(consultationNotes);
		this.consultationNotes = stringBuilder.toString();
	}

	public HashMap<Medicine, Integer> getPrescribedMedicineList() {
		return this.prescribedMedicineList;
	}

	public void addPrescribedMedicine(HashMap<Medicine, Integer> medicineList) {
		this.prescribedMedicineList = medicineList;
	}

	public void removePrescribedMedicine(Medicine medicine) {
		this.prescribedMedicineList.remove(medicine.getName());
	}

	public String getConsultationNotes() {
		return this.consultationNotes;
	}

	public UUID getUUID() {
		return this.uuid;
	}
}
