package raytracer.clustering.client.states;

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

public class ExecutingRender implements IState {
	private DataStore dataStore;
	
	public ExecutingRender() {
	}
	
	@Override
	public void talk(PrintWriter out) {
		out.println(States.EXECUTING_RENDER.toString());
	}
	
	public ExecutingRender(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public void execute(String serverResponse) {
		final int nThreads = Runtime.getRuntime().availableProcessors();
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		// Write datastore (incl. RenderBlock), add RenderBlock compatibility to RenderTask
		// - A renderBlock carries its results -
		Set <Future<RenderBlock>> set = new HashSet<Future<RenderBlock>>();
		
		// for (RenderBlock renderBlock : dataStore.getRenderBlocks) {
		// 
			//RenderTask must always return RenderBlock with results.
			Future<RenderBlock> future = threadPool.submit(new RenderTask(renderBlock)); 
			set.add(future);
	    // }
		
		//implement getSize, add, clearInputData
		DataStore result = new DataStore();
		for (Future<RenderBlock> future : set) {
			//dataStore.clearInputData();
			//dataStore.add(future.get())
		}
		
	    if (result.getSize() > 0) {
	    	 Client.setState(new TransmittingData(result));
	    } else {
	    	Client.setState(new Error(States.NO_RENDER_RESULT.toString()));
	    }
	}
	
	


}
