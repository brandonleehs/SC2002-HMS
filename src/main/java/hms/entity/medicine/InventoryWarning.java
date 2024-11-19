package hms.entity.medicine;

public enum InventoryWarning {
	OK, WARNING;

	// Toggles the status between the two
	public InventoryWarning toggle() {
		return this == OK ? WARNING : OK;
	}

	@Override
	public String toString() {
		switch (this.ordinal()) {
		case 0:
			return "OK";
		case 1:
			return "WARNING";
		default:
			return null;
		}
	}
}
