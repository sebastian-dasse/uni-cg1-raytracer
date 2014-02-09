package raytracer.multiserver.server.states;

import java.io.PrintWriter;

import raytracer.model.DataStore;
import raytracer.multiserver.IState;
import raytracer.multiserver.enums.States;


public class ClosingConnection implements IState {
	public DataStore dataStore;
	
	public ClosingConnection(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	@Override
	public void talk(PrintWriter out) {
		out.println(States.CLOSING_CONNECTION.toString());
	}

	@Override
	public void execute(String serverResponse) {
	}

	public DataStore getDataStore() {
		return dataStore;
	}
	
}
