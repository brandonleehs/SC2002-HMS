package hms;

public enum MedicineStatus {
	PENDING, DISPENSED;

	// Toggles the status between the two
	public MedicineStatus toggle() {
        return this == PENDING ? DISPENSED : PENDING;
    }
}
