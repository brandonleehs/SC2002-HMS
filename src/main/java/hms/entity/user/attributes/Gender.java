package hms.entity.user.attributes;

/**
 * Enum representing the gender of a user.
 * <p>
 * The supported genders are Male and Female.
 */
public enum Gender {
	MALE, FEMALE;

	/**
     * Returns a string representation of the gender.
     * <p>
     * The gender is returned as "Male" for {@link Gender#MALE} and "Female" for {@link Gender#FEMALE}.
     *
     * @return a string representing the gender ("Male" or "Female").
     */
	@Override
	public String toString() {
		switch (this.ordinal()) {
		case 0:
			return "Male";
		case 1:
			return "Female";
		default:
			return null;
		}
	}
}