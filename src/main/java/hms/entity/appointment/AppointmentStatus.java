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
