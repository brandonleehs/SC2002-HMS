package hms.entity.record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hms.entity.medicine.Medicine;

public class AppointmentOutcomeRecord {
	private final UUID uuid;
	private final LocalDate date;
	private String serviceType;
	private final List<Medicine> prescribedMedicineList;
	private String consultationNotes;

	public AppointmentOutcomeRecord(LocalDate date, String serviceType, String consultationNotes, UUID uuid) {
		this.uuid = uuid;
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

	public List<Medicine> getPrescribedMedicineList() {
		return this.prescribedMedicineList;
	}

	public void addPrescribedMedicine(Medicine medicine) {
		this.prescribedMedicineList.add(medicine);
	}

	public void removePrescribedMedicine(Medicine medicine) {
		this.prescribedMedicineList.remove(medicine);
	}

	public String getConsultationNotes() {
		return this.consultationNotes;
	}

	public UUID getUUID() {
		return this.uuid;
	}
}
