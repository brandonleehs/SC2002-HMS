package hms.entity.record;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import hms.entity.medicine.Medicine;
import hms.entity.medicine.MedicineStatus;


/**
 * Represents the outcome of an appointment, including prescribed medicines,
 * consultation notes, and other relevant details.
 */
public class AppointmentOutcomeRecord {
	private final UUID uuid;
	private final LocalDate date;
	private String serviceType;
	private Map<Medicine, Integer> prescribedMedicineMap;
	private String consultationNotes;

	/**
     * Constructs a new {@code AppointmentOutcomeRecord} with the specified details.
     *
     * @param date the date of the appointment.
     * @param serviceType the type of service provided during the appointment.
     * @param consultationNotes the consultation notes for the appointment.
     * @param uuid the unique identifier for the record.
     */
	public AppointmentOutcomeRecord(LocalDate date, String serviceType, String consultationNotes, UUID uuid) {
		this.uuid = uuid;
		this.date = date;
		this.serviceType = serviceType;
		this.consultationNotes = consultationNotes;
		this.prescribedMedicineMap = new HashMap<>();
	}

	/**
     * Retrieves the date of the appointment.
     *
     * @return the appointment date.
     */
	public LocalDate getDate() {
		return this.date;
	}

	/**
     * Retrieves the type of service provided during the appointment.
     *
     * @return the service type.
     */
	public String getServiceType() {
		return this.serviceType;
	}

	/**
     * Sets the type of service provided during the appointment.
     *
     * @param serviceType the service type to set.
     */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
     * Sets the consultation notes for the appointment.
     *
     * @param consultationNotes the consultation notes to set.
     */
	public void setConsultationNotes(String consultationNotes) {
		this.consultationNotes = consultationNotes;
	}

	/**
     * Appends additional notes to the existing consultation notes.
     *
     * @param consultationNotes the notes to append.
     */
	public void addConsultationNotes(String consultationNotes) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(this.consultationNotes);
		stringBuilder.append(" " + consultationNotes);
		this.consultationNotes = stringBuilder.toString();
	}

	/**
     * Retrieves the map of prescribed medicines and their quantities.
     *
     * @return the map of prescribed medicines.
     */
	public Map<Medicine, Integer> getPrescribedMedicineMap() {
		return this.prescribedMedicineMap;
	}

	/**
     * Adds medicines and their quantities to the prescribed medicines map.
     *
     * @param medicineMap a map of medicines and their quantities to add.
     */
	public void addPrescribedMedicine(Map<Medicine, Integer> medicineMap) {
		this.prescribedMedicineMap.putAll(medicineMap);
	}

	/**
     * Removes a prescribed medicine from the record.
     *
     * @param medicine the medicine to remove.
     */
	public void removePrescribedMedicine(Medicine medicine) {
		this.prescribedMedicineMap.remove(medicine);
	}

	/**
     * Retrieves the consultation notes for the appointment.
     *
     * @return the consultation notes.
     */
	public String getConsultationNotes() {
		return this.consultationNotes;
	}

	/**
     * Retrieves the unique identifier for this record.
     *
     * @return the UUID of the record.
     */
	public UUID getUUID() {
		return this.uuid;
	}

	/**
     * Checks if any prescribed medicines in the record are not yet dispensed.
     *
     * @return {@code true} if there are undispensed medicines, {@code false} otherwise.
     */
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
