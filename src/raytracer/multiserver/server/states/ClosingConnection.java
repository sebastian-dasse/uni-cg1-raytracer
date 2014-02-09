package raytracer.multiserver.server.states;

import java.io.PrintWriter;

import raytracer.multiserver.enums.Phrases;


public class ClosingConnection implements IState {

	@Override
	public void talk(PrintWriter out) {
		out.println(Phrases.CLOSING_CONNECTION.toString());
	}

	@Override
	public void execute(String serverResponse) {
		// TODO Auto-generated method stub
	}

}
