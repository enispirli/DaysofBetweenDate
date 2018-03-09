
public enum Days {
	PAZAR("pazar"), PAZARTESI("pazartesi"), SALI("salı"), CARSAMBA("çarşamba"), PERSEMBE("perşembe"), CUMA(
			"cuma"), CUMARTESI("cumartesi");


	private String value;

	private Days(String value) {
		this.value = value;
	}
	public String getValue() {

		return value;
	}
	public static Days getDaysByValue(String value) {
		for (Days days : Days.values()) {
			if (days.getValue().equals(value)) {
				return days;
			}
		}
		return null;
	}

}
