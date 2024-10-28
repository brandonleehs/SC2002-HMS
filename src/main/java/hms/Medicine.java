package hms;

public class Medicine {
	private String name;
	private int quantity;
	private MedicineStatus medicineStatus;

	public Medicine(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
		this.medicineStatus = MedicineStatus.PENDING;
	}

	public String getName() {
		return this.name;
	}

	public int getQuantity(){
		return this.quantity;
	}

	public MedicineStatus getMedicineStatus() {
		return this.medicineStatus;
	}

	public void setMedicineStatus(MedicineStatus medicineStatus) {
		this.medicineStatus = medicineStatus;
	}
}
