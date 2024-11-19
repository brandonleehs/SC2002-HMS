package hms.entity.appointment;

/**
 * Enum representing the status of an appointment in the system.
 * Possible statuses are PENDING, CONFIRMED, CANCELLED, and COMPLETED.
 */
public enum AppointmentStatus {
	PENDING, CONFIRMED, CANCELLED, COMPLETED;


	/**
     * Returns a string representation of the appointment status with symbols
     * indicating its state. For example, "[?] Pending" for the PENDING status.
     *
     * @return a symbolic string representation of the appointment status.
     */
	@Override
	public String toString() {
		switch (this.ordinal()) {
		case 0:
			return "[?] Pending";
		case 1:
			return "[+] Confirmed";
		case 2:
			return "[!] Cancelled";
		case 3:
			return "[X] Completed";
		default:
			return null;
		}
	}

	/**
     * Converts a text representation of the status into the corresponding
     * {@code AppointmentStatus} enum value.
     *
     * @param text the text representation of the status. Supported values are:
     *             "Pending", "Confirmed", "Cancelled", and "Completed".
     * @return the corresponding {@code AppointmentStatus} value, or {@code null}
     *         if the input does not match any valid status.
     */
	public static AppointmentStatus fromString(String text) {
		switch (text) {
		case "Pending":
			return AppointmentStatus.PENDING;
		case "Confirmed":
			return AppointmentStatus.CONFIRMED;
		case "Cancelled":
			return AppointmentStatus.CANCELLED;
		case "Completed":
			return AppointmentStatus.COMPLETED;
		default:
			return null;
		}
	}

	/**
     * Returns a plain string representation of the appointment status without
     * any symbolic prefixes. For example, "Pending" for the PENDING status.
     *
     * @return a plain string representation of the appointment status.
     */
	public String toString2() {
		switch (this.ordinal()) {
		case 0:
			return "Pending";
		case 1:
			return "Confirmed";
		case 2:
			return "Cancelled";
		case 3:
			return "Completed";
		default:
			return null;
		}
	}
}
