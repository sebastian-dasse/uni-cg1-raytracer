package raytracer.clustering.enums;

public enum ErrorMessage {
    HOST_UNKNOWN ("ERROR: Host unknown."),
    IO ("IO ERROR."),
    STARTING_SERVER  ("Error starting Server. Using Portnumber " + Config.PORTNUMBER.getIntValue()
					+ ".\nMake sure no other service is using this port.");
	
	String value;
	
	ErrorMessage(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getIntValue() {
		return Integer.parseInt(value);
	}
}