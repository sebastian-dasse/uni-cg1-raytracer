package raytracer.material;

import raytracer.Color;
import raytracer.Ray;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class implements the color of a transparent material.
 * 
 * @author Sebastian Dass&eacute;
 */
public class TransparentMaterial extends Material{
	/**
	 *  The index of refraction for this material.
	 */
	private final double indexOfRefraction;
	
	/**
	 * Constructs a new <code>TransparentMaterial</code> object with the specified index of refraction.
	 * 
	 * @param indexOfRefraction The refraction index of the material. Must not be <code>null</code>.
	 */
	public TransparentMaterial(final double indexOfRefraction){
		this.indexOfRefraction = indexOfRefraction;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		/*
		 * Formulas:
		 * 		cos phi1 = <-d, n>
		 * 		cos phi2 = sqrt(1 - (eta1 / eta2)² * (1 - cos² phi1))
		 * 		rd = d + 2(cos phi1) * n
		 * 		rd = (eta1 / eta2) * d - (cos phi2 - (eta1 / eta2) * cos phi1) * n
		 * 		c = R * fr[pr, rd] + T * fr[pr, rt], R: = reflection, T := transmission
		 */
		Normal3 n = hit.normal;
		final Ray ray = hit.ray;
		final Vector3 d = ray.d;
		final Point3 p = ray.at(hit.t);
		double eta1 = world.indexOfRefraction;
		double eta2 = indexOfRefraction;
		double cosPhi1 = d.mul(-1).dot(n);
		if (cosPhi1 <= 0) { // <d, n> <= 
			cosPhi1 = -cosPhi1;
			n = n.mul(-1);
			final double temp = eta1;
			eta1 = eta2;
			eta2 = temp;
		}
		final double quotient = eta1 / eta2;
		final double cosPhi2 = Math.sqrt(1 - quotient * quotient * (1 - cosPhi1 * cosPhi1));
		final Vector3 rd = d.add(n.mul(2 * cosPhi1));
		final Vector3 rt = d.mul(quotient).sub(n.mul(cosPhi2 - quotient * cosPhi1));
		final double r0 = Math.pow((eta1 - eta2) / (eta1 + eta2), 2);
		final double r = r0 + (1 - r0) * Math.pow(1 - cosPhi1, 5);
		final double t = 1 - r;
		if (t < 0) { 
			System.err.println(t);
			return world.backgroundColor;
		}
		final Color cr = tracer.trace(new Ray(p, rd), world).mul(r);
		final Color ct = tracer.trace(new Ray(p, rt), world).mul(t);
		return cr.add(ct);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(indexOfRefraction);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TransparentMaterial other = (TransparentMaterial) obj;
		if (Double.doubleToLongBits(indexOfRefraction) != Double
				.doubleToLongBits(other.indexOfRefraction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[indexOfRefraction = " + indexOfRefraction + "]";
	}
}
