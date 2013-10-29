package raytracer.math;

public class Point3 {
	public final double x;
	public final double y;
	public final double z;
	
	public Point3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
//	TODO: kann man delegieren?  -  lieber nicht!
	public Vector3 sub(final Point3 p) {
		return new Vector3(x - p.x, 
						   y - p.y, 
						   z - p.z);
	}
	
//	TODO: kann man delegieren?  -  lieber nicht!
	public Point3 sub(final Vector3 v) {
		return new Point3(x - v.x, 
						  y - v.y, 
						  z - v.z);
	}
	
	public Point3 add(final Vector3 v) {
		return new Point3(x + v.x, 
						  y + v.y, 
						  z + v.z);
	}
}
