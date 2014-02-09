package raytracer.multiserver.enums;

public enum Config {
	PORTNUMBER("4442"),
	HOSTNAME("localhost");
	
	String value;
	
	Config(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getIntValue() {
		return Integer.parseInt(value);
	}
	
}
