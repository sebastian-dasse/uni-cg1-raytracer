package raytracer.multiserver.server.states;

import java.io.PrintWriter;

import raytracer.model.DataStore;
import raytracer.multiserver.Client;
import raytracer.multiserver.IState;
import raytracer.multiserver.enums.States;

public class ReadingData implements IState {
	
	@Override
	public void talk(PrintWriter out) {
		out.println(States.READING_DATA.toString());
	}

	
	@Override
	public void execute(String serverResponse) {
		/*
		 * Deserialize String here.
		 * 
		 */
		// DataStore dataStore = ... deserializedData
		// if (success) {
		
		// dummy ->
			DataStore dataStore = new DataStore();
	    
		// if (success) {
			Client.setState(new ClosingConnection(dataStore));
		// 	
			
	    // if (error) {
			Client.setState(new Error(States.DATA_ERROR.toString()));
			
	}
	
	

}
