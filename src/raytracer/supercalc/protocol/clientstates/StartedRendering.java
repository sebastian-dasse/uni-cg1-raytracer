package raytracer.supercalc.protocol.clientstates;

import raytracer.supercalc.DataStore;
import raytracer.supercalc.Phrases;
import raytracer.supercalc.protocol.IState;

public class StartedRendering implements IState {
	private DataStore dataStore;
	
	public StartedRendering() {
	}
	
	@Override
	public String talk() {
		return Phrases.STARTED_RENDERING.toString();
	}
	
	public StartedRendering(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public void execute(String serverResponse) {
		
	}
	
	


}
