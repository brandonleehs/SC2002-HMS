package hms.entity.appointment;

public enum AppointmentStatus {
	PENDING, CONFIRMED, CANCELLED, COMPLETED;

	@Override
	public String toString() {
		switch (this.ordinal()) {
		case 0:
			return "\u29D6 Pending";
		case 1:
			return "\u2B24 Confirmed";
		case 2:
			return "\u2717 Cancelled";
		case 3:
			return "\u2713 Completed";
		default:
			return null;
		}
	}
}
