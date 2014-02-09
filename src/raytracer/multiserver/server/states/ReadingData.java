package raytracer.multiserver.server.states;

import java.io.PrintWriter;

import raytracer.model.DataStore;
import raytracer.multiserver.Client;
import raytracer.multiserver.enums.Phrases;

public class ReadingData implements IState {
	
	@Override
	public void talk(PrintWriter out) {
		out.println(Phrases.READING_DATA.toString());
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
			Client.setState(new StartedRendering(dataStore));
		// 	
			
	    // if (error) {
			Client.setState(new Error(Phrases.DATA_ERROR.toString()));
			
	}
	
	

}
