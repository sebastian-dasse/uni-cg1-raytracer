package raytracer.clustering.client.states;

import java.io.PrintWriter;

import raytracer.clustering.Client;
import raytracer.clustering.IState;
import raytracer.clustering.enums.States;
import raytracer.model.DataStore;

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
			Client.setState(new ExecutingRender(dataStore));
		// 	
			
	    // if (error) {
			Client.setState(new Error(States.DATA_ERROR.toString()));
			
	}
	
	

}
