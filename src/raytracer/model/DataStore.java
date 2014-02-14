package raytracer.model;

import java.awt.image.DataBuffer;
import java.awt.image.PixelGrabber;
import java.util.Collection;
import java.util.LinkedList;

public class DataStore {
	Collection<DataBuffer> data;
	
	public DataStore() {
		data = new LinkedList<>();
	}
	
	public void add(DataBuffer buffer) {
		data.add(buffer);
	}
	
	public void merge() {
	}
}
