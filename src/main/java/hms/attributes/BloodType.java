package hms.attributes;

public enum BloodType {
	O_POS, O_NEG, A_POS, A_NEG, B_POS, B_NEG, AB_POS, AB_NEG;

	public static BloodType getBloodType(String bloodType) {
		switch (bloodType) {
		case "O+":
			return BloodType.O_POS;
		case "O-":
			return BloodType.O_NEG;
		case "A+":
			return BloodType.A_POS;
		case "A-":
			return BloodType.A_NEG;
		case "B+":
			return BloodType.B_POS;
		case "B-":
			return BloodType.B_NEG;
		case "AB+":
			return BloodType.AB_POS;
		default:
			return BloodType.AB_NEG;
		}
	}
}