package raytracer.multiserver.client.states;

import java.io.PrintWriter;

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
