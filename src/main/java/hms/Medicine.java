package hms;

public class Medicine {
	private String name;
	private MedicineStatus medicineStatus;

	public Medicine(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public MedicineStatus getMedicineStatus() {
		return this.medicineStatus;
	}

	public void setMedicineStatus(MedicineStatus medicineStatus) {
		this.medicineStatus = medicineStatus;
	}
}
