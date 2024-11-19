package hms.entity.user.attributes;


/**
 * Enum representing the different blood types.
 * <p>
 * The blood types include both positive and negative variations: O+, O-, A+, A-, B+, B-, AB+, AB-.
 */
public enum BloodType {
	O_POS, O_NEG, A_POS, A_NEG, B_POS, B_NEG, AB_POS, AB_NEG;


	/**
     * Returns the {@link BloodType} corresponding to the given string.
     * <p>
     * The input string should be in the format "O+", "O-", "A+", "A-", "B+", "B-", "AB+", or "AB-".
     * If the string doesn't match any of these, {@code null} is returned.
     *
     * @param bloodType a string representing the blood type (e.g., "O+", "A-", etc.)
     * @return the corresponding {@link BloodType} value, or {@code null} if no match is found.
     */
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
		case "AB-":
			return BloodType.AB_NEG;
		default:
			return null;
		}
	}

	/**
     * Returns a string representation of the blood type in the format "O+", "O-", "A+", "A-", "B+", "B-", "AB+", or "AB-".
     *
     * @return a string representing the blood type.
     */
	@Override
	public String toString() {
		switch (this.ordinal()) {
		case 0:
			return "O+";
		case 1:
			return "O-";
		case 2:
			return "A+";
		case 3:
			return "A-";
		case 4:
			return "B+";
		case 5:
			return "B-";
		case 6:
			return "AB+";
		case 7:
			return "AB-";
		default:
			return null;
		}
	}
}