package hms.entity.medicine;

public enum MedicineStatus {
	PENDING, DISPENSED;

	// Toggles the status between the two
	public MedicineStatus toggle() {
		return this == PENDING ? DISPENSED : PENDING;
	}

	@Override
	public String toString() {
		switch (this.ordinal()) {
		case 0:
			return "\u2717 Pending";
		case 1:
			return "\u2713 Dispensed";
		default:
			return null;
		}
	}
}
