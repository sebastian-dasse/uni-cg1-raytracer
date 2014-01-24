package raytracer.math;

import raytracer.Ray;

public class Transform {
		
	public final Mat4x4 m;
	
	public final Mat4x4 i;
	
	public Transform(){
		this.m = new Mat4x4(1.0, 0, 0, 0, 
							0, 1.0, 0, 0, 
							0, 0, 1.0, 0, 
							0, 0, 0, 1.0);
		this.i = new Mat4x4(1.0, 0, 0, 0, 
							0, 1.0, 0, 0, 
							0, 0, 1.0, 0, 
							0, 0, 0, 1.0);
	}
	
	private Transform(final Mat4x4 m, final Mat4x4 i){
		this.m = m;
		this.i = i;
	}
	
	public Transform translate(final double x, final double y, final double z) {
		    return null;
		  }
	
	public Transform scale(final double x, final double y, final double z) {
	    return null;
	  }
	
	public Transform rotateX(final double angle) {
	    return null;
	  }
	
	public Transform rotateY(final double angle) {
	    return null;
	  }
	
	public Transform rotateZ(final double angle) {
	    return null;
	  }
	
	public Ray mul(final Ray ray) {
	    return null;
	  }
	
	public Normal3 mul(final Normal3 normal) {
	    return null;
	  }
}
