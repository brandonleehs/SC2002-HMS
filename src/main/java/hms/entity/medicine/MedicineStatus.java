package hms.entity.medicine;

/**
 * Enum representing the status of a medicine.
 * The status can be either {@code PENDING} or {@code DISPENSED}.
 */
public enum MedicineStatus {
	PENDING, DISPENSED;

	/**
     * Toggles the status of the medicine between {@code PENDING} and {@code DISPENSED}.
     *
     * @return the toggled {@code MedicineStatus}.
     */
	public MedicineStatus toggle() {
		return this == PENDING ? DISPENSED : PENDING;
	}

	/**
     * Returns a string representation of the medicine status.
     * For {@code PENDING}, it returns "[?] Pending".
     * For {@code DISPENSED}, it returns "[X] Dispensed".
     *
     * @return a string representation of the medicine status.
     */
	@Override
	public String toString() {
		switch (this.ordinal()) {
		case 0:
			return "[?] Pending";
		case 1:
			return "[X] Dispensed";
		default:
			return null;
		}
	}

	/**
     * Converts a string representation of the status to its corresponding {@code MedicineStatus} enum value.
     *
     * @param text the string representation of the medicine status.
     *             Valid values are "Pending" or "Dispensed".
     * @return the corresponding {@code MedicineStatus}, or {@code null} if the input is invalid.
     */
	public static MedicineStatus fromString(String text) {
		switch (text) {
		case "Pending":
			return MedicineStatus.PENDING;
		case "Dispensed":
			return MedicineStatus.DISPENSED;
		default:
			return null;
		}
	}

	/**
     * Returns a simple string representation of the medicine status.
     * For {@code PENDING}, it returns "Pending".
     * For {@code DISPENSED}, it returns "Dispensed".
     *
     * @return a simple string representation of the medicine status.
     */
	public String toString2() {
		switch (this.ordinal()) {
		case 0:
			return "Pending";
		case 1:
			return "Dispensed";
		default:
			return null;
		}
	}
}
