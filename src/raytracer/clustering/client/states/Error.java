package raytracer.clustering.client.states;

import java.io.PrintWriter;

import raytracer.clustering.IState;

public class Error implements IState {
	private String error;
	
	public Error (String error) {
		this.error = error;
	}
	
	@Override
	public void talk(PrintWriter out) {
		out.println(error);
	}

	@Override
	public void execute(String serverResponse) {
		;
	}

}
