package hms.entity.appointment;

public enum AppointmentStatus {
	PENDING, CONFIRMED, CANCELLED, COMPLETED;

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
}
