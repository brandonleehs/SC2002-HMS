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
			return "[?] Pending";
		case 1:
			return "[X] Dispensed";
		default:
			return null;
		}
	}

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
