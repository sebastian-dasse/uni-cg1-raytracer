package raytracer.math;

/**
 * This immutable class represents a vector in three-dimensional space. Thus it has fields for its three components and 
 * an additional field in which its magnitude is stored.
 * 
 * <p>The class <code>Vector3</code> provides various methods for vector operations, which include addition and 
 * subtraction of two vectors as well as scalar multiplication, scalar (or dot) product (which are not to be confused!) 
 * and cross product (or vector product). There is a method that calculates the vector resulting from the reflection of 
 * a vector on a plane given through its normal. Finally there is also method a for normalization and another one for 
 * the conversion from <code>Vector3</code> to <code>Normal3</code>.
 * 
 * <p>Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> 
 * to be thrown.
 * 
 * @author Sebastian Dassé
 */
public class Vector3 {
	public final double x;
	public final double y;
	public final double z;
	public final double magnitude;
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	public Vector3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		magnitude = Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3 add(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Vector3(x + v.x, 
						   y + v.y, 
						   z + v.z);
	}
	
	public Vector3 add(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return new Vector3(x + n.x, 
						   y + n.y, 
						   z + n.z);
	}
	
	public Vector3 sub(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return new Vector3(x - n.x, 
						   y - n.y, 
						   z - n.z);
	}
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	public Vector3 mul(double c) {
		return new Vector3(x * c, 
						   y * c, 
						   z * c);
	}
	
	public double dot(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return x * v.x + y * v.y + z * v.z;
	}
	
	public double dot(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return x * n.x + y * n.y + z * n.z;
	}
	
	public Vector3 normalized() {
		if (magnitude == 0) {
			throw new ArithmeticException("Cannot normalize a vector with magnitude = 0.");
		}
		return new Vector3(x / magnitude, 
						   y / magnitude, 
						   z / magnitude);
	}
	
	public Normal3 asNormal() {
		if (magnitude == 0) {
			throw new RuntimeException("The null vector (0, 0, 0) is not a meaningful normal.");
		}
		return new Normal3(x, y, z);
	}
	
//	Anmerkung: evtl. Methode für das Skalarprodukt benutzen
	public Vector3 reflectedOn(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		// Ebene		<x - u, q> mit u = 0 und q := n
		// Gerade		g(r) = a + rq mit a := this. Die Gerade durch a und den Lotfußpunkt von a auf der Ebene
		// Parameter	r = -<a - u, q> /|q|², d.h. für den Nenner wird a in die negative Ebenengleichung eingesetzt
		// Spiegelpunkt	a' = g(2rp)
		final double r = -(x * n.x + y * n.y + z * n.z) 
						/ (n.x * n.x + n.y * n.y + n.z * n.z);
		return this.add(n.mul(2 * r));
	}
	
	public Vector3 x(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Vector3(y * v.z - z * v.y, 
						   z * v.x - x * v.z, 
						   x * v.y - y * v.x);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(magnitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3 other = (Vector3) obj;
		if (Double.doubleToLongBits(magnitude) != Double
				.doubleToLongBits(other.magnitude))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[x = " + x + ", y = " + y + ", z = " + z + ", magnitude = " + magnitude + "]";
	}
	
	public static void main(String[] args) {
		Vector3 v = new Vector3(0, 0, 0);
		System.out.println(v.magnitude);
//		System.out.println(v.normalized());
		System.out.println(v.asNormal());
	}
}
