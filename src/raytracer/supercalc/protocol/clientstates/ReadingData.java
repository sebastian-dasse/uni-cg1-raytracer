package raytracer.supercalc.protocol.clientstates;

import raytracer.supercalc.Client;
import raytracer.supercalc.DataStore;
import raytracer.supercalc.Phrases;
import raytracer.supercalc.protocol.IState;

public class ReadingData implements IState {
	
	@Override
	public String talk() {
		return Phrases.READING_DATA.toString();
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
