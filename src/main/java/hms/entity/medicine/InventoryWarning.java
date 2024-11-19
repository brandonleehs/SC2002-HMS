package hms.entity.medicine;

/**
 * Enum representing the inventory warning status.
 * The status can be either {@code OK} (indicating sufficient inventory)
 * or {@code WARNING} (indicating low inventory).
 */
public enum InventoryWarning {
	OK, WARNING;

	/**
     * Toggles the inventory warning status between {@code OK} and {@code WARNING}.
     *
     * @return the toggled {@code InventoryWarning} status.
     */
	public InventoryWarning toggle() {
		return this == OK ? WARNING : OK;
	}

	/**
     * Returns a string representation of the inventory warning status.
     * For {@code OK}, it returns "OK", and for {@code WARNING}, it returns "WARNING".
     *
     * @return a string representation of the inventory warning status.
     */
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
