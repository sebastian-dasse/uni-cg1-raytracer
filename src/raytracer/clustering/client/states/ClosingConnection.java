package raytracer.clustering.client.states;

import java.io.PrintWriter;

import raytracer.clustering.IState;
import raytracer.clustering.enums.States;


public class ClosingConnection implements IState {

	@Override
	public void talk(PrintWriter out) {
		out.println(States.CLOSING_CONNECTION.toString());
	}

	@Override
	public void execute(String serverResponse) {
		// TODO Auto-generated method stub
	}

}
