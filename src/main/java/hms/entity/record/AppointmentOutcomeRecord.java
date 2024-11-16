package hms.entity.record;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineStatus;

public class AppointmentOutcomeRecord {
	private final UUID uuid;
	private final LocalDate date;
	private String serviceType;
	private Map<Medicine, Integer> prescribedMedicineMap;
	private String consultationNotes;

	public AppointmentOutcomeRecord(LocalDate date, String serviceType, String consultationNotes, UUID uuid) {
		this.uuid = uuid;
		this.date = date;
		this.serviceType = serviceType;
		this.consultationNotes = consultationNotes;
		this.prescribedMedicineMap = new HashMap<>();
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

	public Map<Medicine, Integer> getPrescribedMedicineMap() {
		return this.prescribedMedicineMap;
	}

	public void addPrescribedMedicine(Map<Medicine, Integer> medicineMap) {
		this.prescribedMedicineMap.putAll(medicineMap);
	}

	public void removePrescribedMedicine(Medicine medicine) {
		this.prescribedMedicineMap.remove(medicine);
	}

	public String getConsultationNotes() {
		return this.consultationNotes;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public Boolean CheckIfUnprescribedMedicineExists() {
		// Sees if all medicine in this record has been dispensed
		if (prescribedMedicineMap.isEmpty()) {
			return false;
		} else {
			for (Map.Entry<Medicine, Integer> entry : prescribedMedicineMap.entrySet()) {
				if (entry.getKey().getMedicineStatus() == MedicineStatus.PENDING)
					return true;
			}
			return false;
		}
	}
}
