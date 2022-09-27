package constants;

public enum Types {
	LITE("LITE"), CLASSIC("CLASSIC"), PREMIUM("PREMIUM");

	private String value;

	private Types(String value) {
		this.setValue(value);
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
