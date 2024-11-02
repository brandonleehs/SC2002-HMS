package hms.entity.medicine;

public class ReplenishRequest {
	private final String medicineName;
	private final int stockToAdd;

	public ReplenishRequest(String medicineName, int stockToAdd) {
		this.medicineName = medicineName;
		this.stockToAdd = stockToAdd;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public int getStockToAdd() {
		return stockToAdd;
	}
}
