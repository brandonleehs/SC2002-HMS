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
	private HashMap<Medicine, Integer> prescribedMedicineList;
	private String consultationNotes;

	public AppointmentOutcomeRecord(LocalDate date, String serviceType, String consultationNotes, UUID uuid) {
		this.uuid = uuid;
		this.date = date;
		this.serviceType = serviceType;
		this.consultationNotes = consultationNotes;
		this.prescribedMedicineList = new HashMap<>();
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
		this.prescribedMedicineList.putAll(medicineList);;
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

	public Boolean CheckIfUnprescribedMedicineExists(){
		// Sees if all medicine in this record has been dispensed
		if (prescribedMedicineList.isEmpty()) {
			return false;
		}
		else{
			for (Map.Entry<Medicine, Integer> entry : prescribedMedicineList.entrySet()) {
				if(entry.getKey().getMedicineStatus()==MedicineStatus.PENDING) return true;
			}
			return false;
		}
	}
}
