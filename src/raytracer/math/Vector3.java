package raytracer.math;

public class Vector3 {
	public final double x;
	public final double y;
	public final double z;
	public final double magnitude;
	
	public Vector3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		magnitude = Math.sqrt(x * x + y * y + z * z);
	}
	
	// Konvertierkonstruktor
	public Vector3(Normal3 n)  {
		this(n.x, n.y, n.z);
	}
	
	public Vector3 add(final Vector3 v) {
		return new Vector3(x + v.x, 
						   y + v.y, 
						   z + v.z);
	}
	
//	TODO: FRAGE: ok so?
	public Vector3 add(final Normal3 n) {
//		return new Vector3(x + n.x, 
//						   y + n.y, 
//						   z + n.z);
		// delegiert:
//		return add(new Vector3(n.x, n.y, n.z));
		return add(new Vector3(n));
	}
	
//	TODO: FRAGE: ok so?
	public Vector3 sub(final Normal3 n) {
//		return new Vector3(x - n.x, 
//						   y - n.y, 
//						   z - n.z);
		// delegiert:
		return add(new Vector3(-n.x, -n.y, -n.z));
	}
	
//	TODO
	public Vector3 mul(double c) {
		return new Vector3(x * c, 
						   y * c, 
						   z * c);
	}
	
//	TODO: FRAGE: was soll diese Methode tun?
	public double dot(final Vector3 v) {
		return x * v.x + y * v.y + z * v.z; // ???
	}
	
//	TODO: FRAGE: was soll diese Methode tun?
	public double dot(final Normal3 n) {
		return x * n.x + y * n.y + z * n.z; // ???
	}
	
	public Vector3 normalized() {
		return new Vector3(x / magnitude, 
						   y / magnitude, 
						   z / magnitude);
	}
	
//	TODO: FRAGE: sooo?
	public Normal3 asNormal() {
		return new Normal3(x, y, z);
	}
	
//	TODO: FRAGE: was soll hier passieren?
//	zwei Anmerkungen: Skalarprodukt benutzen; evtl. nicht magnitude^2 sondern direkt ausrechnen für höhere Genauigkeit
	public Vector3 reflectedOn(final Normal3 n) {
		final double r = -(x * n.x 
						 + y * n.y 
						 + z * n.z) / (magnitude * magnitude); 
		return new Vector3(x + r * n.x, 
						   y + r * n.y, 
						   z + r * n.z);
	}
	
//	TODO: check ob ok
	public Vector3 x(final Vector3 v) {
		return new Vector3(y * v.z - z * v.y, 
						   z * v.x - x * v.z, 
						   x * v.y - y * v.x);
	}
}
