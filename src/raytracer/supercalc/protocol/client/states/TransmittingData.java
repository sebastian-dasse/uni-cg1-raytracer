package raytracer.supercalc.protocol.client.states;

import raytracer.model.DataStore;
import raytracer.supercalc.protocol.IState;

public class TransmittingData implements IState {
	private DataStore dataStore;
	
	public TransmittingData(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public String talk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(String serverResponse) {
		// TODO Auto-generated method stub
		
	}

}
