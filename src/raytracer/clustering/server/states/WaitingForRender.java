package raytracer.clustering.server.states;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import raytracer.RenderTask;
import raytracer.clustering.Client;
import raytracer.clustering.IState;
import raytracer.clustering.enums.States;
import raytracer.model.DataStore;
import raytracer.model.RenderBlock;

public class WaitingForRender implements IState {
	
	public WaitingForRender() {
	}
	
	@Override
	public void talk(PrintWriter out) {

	}
	
	public WaitingForRender(DataStore dataStore) {
	}
	
	@Override
	public void execute(String serverResponse) {
		Client.setState(new ReadingData());
	}
}
