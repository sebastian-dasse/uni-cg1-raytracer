package raytracer.math;

import raytracer.Ray;

public class Transform {
		
	public final Mat4x4 m;
	
	public final Mat4x4 i;
	
	public Transform(){
		m = new Mat4x4(1, 0, 0, 0, 
					   0, 1, 0, 0, 
					   0, 0, 1, 0, 
					   0, 0, 0, 1);
		i = new Mat4x4(1, 0, 0, 0, 
					   0, 1, 0, 0, 
					   0, 0, 1, 0, 
					   0, 0, 0, 1);
	}
	
	private Transform(final Mat4x4 m, final Mat4x4 i){
		this.m = m;
		this.i = i;
	}
	
	public Transform translate(Point3 point) {
		return translate (point.x, point.y, point.z);
	}
	
 	public Transform translate(final double x, final double y, final double z) {
		return new Transform(new Mat4x4(1, 0, 0, x, 
							            0, 1, 0, y, 
							            0, 0, 1, z, 
							            0, 0, 0, 1).mul(m), 
							            
							 new Mat4x4(1, 0, 0, -x, 
									    0, 1, 0, -y, 
									    0, 0, 1, -z, 
									    0, 0, 0, 1).mul(i));
	}
	
	public Transform scale(final double x, final double y, final double z) {
		return new Transform(new Mat4x4(x, 0, 0, 0, 
										0, y, 0, 0, 
										0, 0, z, 0, 
										0, 0, 0, 1).mul(m), 
										
							 new Mat4x4(1/x, 0, 0, 0, 
										0, 1/y, 0, 0, 
										0, 0, 1/z, 0, 
										0, 0, 0, 1).mul(i));
	}
	
	public Transform rotateX(final double angle) {
		final Mat4x4 tm = new Mat4x4( 1, 0,                0,              0,
									  0, Math.cos(angle),-Math.sin(angle), 0,
									  0, Math.sin(angle), Math.cos(angle), 0,
									  0, 0,                0,              1);
		final Mat4x4 ti = new Mat4x4( 1, 0,                0,              0,
									  0, Math.cos(angle), Math.sin(angle), 0,
									  0,-Math.sin(angle), Math.cos(angle), 0,
									  0, 0,                0,              1);
		return new Transform(tm.mul(m), ti.mul(i));
	  }
	
	public Transform rotateY(final double angle) {
		final Mat4x4 tm = new Mat4x4( Math.cos(angle) , 0,-Math.sin(angle), 0,
									  0, 				1, 0,               0,
									  Math.sin(angle),  0, Math.cos(angle), 0,
									  0,                0, 0,               1);
		final Mat4x4 ti = new Mat4x4( Math.cos(angle) , 0,Math.sin(angle),  0,
									  0, 				1, 0,               0,
									-Math.sin(angle),   0, Math.cos(angle), 0,
									  0,                0, 0,               1);
		return new Transform(tm.mul(m), ti.mul(i));
	  }
	
	public Transform rotateZ(final double angle) {
		final Mat4x4 tm = new Mat4x4( Math.cos(angle),-Math.sin(angle), 0, 0,
									  Math.sin(angle), Math.cos(angle), 0, 0,
									  0, 				  0,              1, 0,
									  0,                0,              0, 1);
		final Mat4x4 ti = new Mat4x4( Math.cos(angle), Math.sin(angle), 0, 0,
									 -Math.sin(angle), Math.cos(angle), 0, 0,
									  0, 				  0,              1, 0,
									  0,                0,              0, 1);
		return new Transform(tm.mul(m), ti.mul(i));
	  }
	
	public Ray mul(final Ray ray) {
	    return new Ray(i.mul(ray.o), i.mul(ray.d));
	  }
	
	public Normal3 mul(final Normal3 normal) {
		final Vector3 temp = (i.transposed().mul(normal.asVector())).normalized();
	    return new Normal3(temp.x, temp.y, temp.z);
	  }
}
