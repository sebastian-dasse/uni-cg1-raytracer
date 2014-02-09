package raytracer.supercalc.protocol.client.states;

import raytracer.supercalc.Phrases;
import raytracer.supercalc.protocol.IState;

public class Error implements IState {
	private String error;
	
	public Error (String error) {
		this.error = error;
	}
	
	@Override
	public String talk() {
		return error;
	}

	@Override
	public void execute(String serverResponse) {
		;
	}

}
