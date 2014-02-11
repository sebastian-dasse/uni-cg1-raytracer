package raytracer.clustering.server.states;

import java.io.PrintWriter;

import raytracer.clustering.IState;
import raytracer.clustering.enums.States;
import raytracer.model.DataStore;


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
