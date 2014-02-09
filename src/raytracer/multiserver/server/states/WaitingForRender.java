package raytracer.multiserver.server.states;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import raytracer.RenderTask;
import raytracer.model.DataStore;
import raytracer.model.RenderBlock;
import raytracer.multiserver.Client;
import raytracer.multiserver.IState;
import raytracer.multiserver.enums.States;

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
