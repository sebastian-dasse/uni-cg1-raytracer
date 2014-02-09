package raytracer.supercalc.protocol;

public interface IState {
	
	public String talk();
	public void execute(String serverResponse);
}
