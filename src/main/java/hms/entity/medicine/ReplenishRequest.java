package hms.entity.medicine;

/**
 * Represents a request to replenish the stock of a specific medicine.
 */
public class ReplenishRequest {
	private final String medicineName;
	private final int stockToAdd;

	/**
     * Constructs a new {@code ReplenishRequest} instance with the specified medicine name and stock quantity to add.
     *
     * @param medicineName the name of the medicine to be replenished.
     * @param stockToAdd   the quantity of stock to be added.
     */
	public ReplenishRequest(String medicineName, int stockToAdd) {
		this.medicineName = medicineName;
		this.stockToAdd = stockToAdd;
	}

	/**
     * Retrieves the name of the medicine for which the stock is to be replenished.
     *
     * @return the name of the medicine.
     */
	public String getMedicineName() {
		return medicineName;
	}

	/**
     * Retrieves the quantity of stock to be added for the specified medicine.
     *
     * @return the quantity of stock to add.
     */
	public int getStockToAdd() {
		return stockToAdd;
	}
}
