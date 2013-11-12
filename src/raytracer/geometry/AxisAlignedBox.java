package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Point3;

/**
 * @author 
 *
 */
public class AxisAlignedBox extends Geometry {
	/**
	 * 
	 */
	public final Point3 lbf;
	/**
	 * 
	 */
	public final Point3 run;
	
	/**
	 * @param lbf
	 * @param run
	 * @param color
	 */
	public AxisAlignedBox(final Point3 lbf, final Point3 run, final Color color) {
		super(color);
		this.lbf = lbf;
		this.run = run;
	}

	@Override
	public Hit hit(final Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
