package raytracer.model;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.LinkedList;

import raytracer.World;
import raytracer.camera.Camera;

public class RenderTaskParameter {
	public int yStartOffset;
	public int yEndOffset;
	public Dimension screenSize;
	public World world;
	public Camera cam;
	public BufferedImage image;
	public int recursion;
	public LinkedList<RenderTaskParameter> children;
	private int index;

	public RenderTaskParameter(int yStartOffset, int yEndOffset, Dimension screenSize,
			World world, Camera cam, BufferedImage image, int recursion) {
		this.yStartOffset = yStartOffset;
		this.yEndOffset = yEndOffset;
		this.screenSize = screenSize;
		this.world = world;
		this.cam = cam;
		this.image = image;
		this.recursion = recursion;
		this.children = new LinkedList<RenderTaskParameter>();
		this.index = 0;
	}
	
	public void split() {
		splitBy(2);
	}
	
	public void splitBy(int frags) {
	    int fragmentSize = yEndOffset / frags;
		final int remainder = yEndOffset % frags;
		int compensation = 0;
		for (int i = 0; i < frags; i++) {
			if (i == frags - 1) {
				compensation = remainder;
			}
			children.add(new RenderTaskParameter(yStartOffset
					+ (fragmentSize * i), yStartOffset
					+ (fragmentSize * (i + 1) + compensation), screenSize,
					world, cam, image, recursion));
		}
	}
	
	public RenderTaskParameter getNextChild() {
		return children.get(index++);
	}
	
	public RenderTaskParameter getFirstChild() {
		return children.getFirst();	
	}
	
	public Collection<RenderTaskParameter> getAllChildren() {
		LinkedList<RenderTaskParameter> retrievedChildren = new LinkedList<RenderTaskParameter>();
		while (hasNextChild()) {
			RenderTaskParameter child = getNextChild();
			if (child.hasChild()) {
				retrievedChildren.addAll(child.getAllChildren());
			} else {
				retrievedChildren.add(child);
			}
		}
		return retrievedChildren;
	}
	
	public boolean hasChild() {
		return children.size() > 0;
	}
	
	public boolean hasNextChild() {
		final boolean hasNextChild = children.size() > index;
		if (!hasNextChild) {
			index = 0;
		}
		return hasNextChild;
	}
}