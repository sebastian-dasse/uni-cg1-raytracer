package raytracer.multiserver.server.states;

import java.io.PrintWriter;

import raytracer.model.DataStore;
import raytracer.multiserver.Client;
import raytracer.multiserver.IState;

public class TransmittingData implements IState {
	private DataStore dataStore;
	
	public TransmittingData(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public void talk(PrintWriter out) {
		// serialize Data store
//		 out.println(serializedDataStore)
	}

	@Override
	public void execute(String serverResponse) {
		Client.setState(new WaitingForRender());
	}

}
