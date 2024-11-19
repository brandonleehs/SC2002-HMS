package hms.entity.medicine;


/**
 * Represents a medicine entity with a name and status.
 */
public class Medicine {
	private String name;
	private MedicineStatus medicineStatus;

	/**
     * Constructs a new {@code Medicine} instance with the specified name.
     * The default status of the medicine is set to {@code PENDING}.
     *
     * @param name the name of the medicine.
     */
	public Medicine(String name) {
		this.name = name;
		this.medicineStatus = MedicineStatus.PENDING;
	}

	/**
     * Retrieves the name of the medicine.
     *
     * @return the name of the medicine.
     */
	public String getName() {
		return this.name;
	}

	/**
     * Retrieves the current status of the medicine.
     *
     * @return the current {@code MedicineStatus} of the medicine.
     */
	public MedicineStatus getMedicineStatus() {
		return this.medicineStatus;
	}

	/**
     * Updates the status of the medicine to the specified value.
     *
     * @param medicineStatus the new {@code MedicineStatus} to set for the medicine.
     */
	public void setMedicineStatus(MedicineStatus medicineStatus) {
		this.medicineStatus = medicineStatus;
	}
}
