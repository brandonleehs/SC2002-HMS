package hms.entity.user.attributes;

public enum Gender {
	MALE, FEMALE;

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