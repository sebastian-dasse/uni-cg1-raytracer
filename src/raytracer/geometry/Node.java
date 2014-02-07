package raytracer.geometry;

import java.util.Collection;
import java.util.LinkedList;

import raytracer.Color;
import raytracer.Ray;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Transform;
import raytracer.texture.SingleColorTexture;

/**
 * This class represents a node in a scene graph. A node can have several other geometries. It applies a transformation 
 * on those geometries.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class Node extends Geometry {
	/**
	 * The transformation of this node.
	 */
	private final Transform transform;
	/**
	 * The geometries of this node.
	 */
	private final Collection <Geometry> geos;

	/**
	 * Constructs a new <code>Node</code> comprising the specified geometries with the specified transformation.
	 * 
	 * @param geos		The geometries which the node comprises.
	 * @param transform	The transformation of the node.
	 */
	public Node(final Collection <Geometry> geos, final Transform transform) {
		super(new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));
		this.geos = geos;
		this.transform = transform;
	}
	
	/**
	 * Constructs a new <code>Node</code> of a specified single geometry with the specified transformation.
	 * 
	 * @param geo		The sole geometry of the node.
	 * @param transform	The transformation of the node.
	 */
	public Node(final Geometry geo, final Transform transform) {
		this(transform);
		geos.add(geo);
	}
	
	/**
	 * Constructs a new empty <code>Node</code> with the specified transformation.
	 * 
	 * @param transform	The transformation of the node.
	 */ 
	public Node(final Transform transform) {
		this(new LinkedList<Geometry>(), transform);
	}
	
	/**
	 * Adds a geometry to this node.
	 * 
	 * @param geo	The geometry to be added.
	 */
	public void add(final Geometry geo) {
		geos.add(geo);
	}
	
	/**
	 * Adds a collection of geometries to this node.
	 * 
	 * @param geos	The collection of geometries to be added.
	 */
	public void addAll(final Collection<Geometry> geos) {
		geos.addAll(geos);
	}
	
	/**
	 * Removes a geometry from this node.
	 * 
	 * @param geo	The geometry to be removed.
	 */
	public void remove(final Geometry geo) {
		geos.remove(geo);
	}
	
	/**
	 * Removes all geometries of the specified collection from this node.
	 * 
	 * @param geos	The collection of geometries to be removed.
	 */
	public void removeAll(final Collection<Geometry> geos) {
		geos.removeAll(geos);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		final Ray processedRay = transform.mul(ray);
		double t = Double.POSITIVE_INFINITY;
		Hit nearestHit = null;
		for (Geometry geo : geos) {
			final Hit hit = geo.hit(processedRay);
			if (hit != null && hit.t < t) {
				nearestHit = hit;
				t = hit.t;
			}
		}
		if (nearestHit == null) {
			return null;
		}
		return new Hit(t, ray, nearestHit.geo, transform.mul(nearestHit.normal), nearestHit.texcoord);
	}
}
