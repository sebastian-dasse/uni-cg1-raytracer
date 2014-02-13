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
		this.index = -1;
	}
	
	public void split() {
		children.clear();
		children.add(new RenderTaskParameter(yStartOffset, yEndOffset / 2, screenSize, world, cam, image, recursion));
		children.add(new RenderTaskParameter((yEndOffset / 2) + 1, yEndOffset, screenSize, world, cam, image, recursion));
	}
	
	public static void main (String[] args) {
		System.out.println(321 / 4);
		System.out.println(321 % 4);
	}
	
	public void splitBy(int frags) {
		if (frags < 3) {
			throw new IllegalArgumentException("Specify a frag number bigger than 3 or refrain to the split() method.");
		}
		
	    int fragmentSize = yEndOffset / frags;
		final int remainder = yEndOffset % frags;
		
		for (int i = 0; i < frags; i++) {
			if (i == frags - 1) {
				fragmentSize += remainder * frags;
			}
			children.add(new RenderTaskParameter(yStartOffset + (fragmentSize * i), yStartOffset + (fragmentSize * (i + 1)),
					screenSize, world, cam, image, recursion));
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
		return children.size() > index;
	}
	
	
	
}