package raytracer.supercalc.protocol.client.states;

import java.io.PrintWriter;

import raytracer.supercalc.Phrases;


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
