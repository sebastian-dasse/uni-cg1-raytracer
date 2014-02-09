package raytracer.multiserver.enums;

public enum Error {
    HOST_UNKNOWN("ERROR: Host unknown."),
    IO("IO ERROR.");
	
	String value;
	
	Error(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getIntValue() {
		return Integer.parseInt(value);
	}
}