package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.material.ReflectiveMaterial;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.texture.TextureCoord;

/*
 * -- Notiz --
 * Zugriff auf Arrays: 
 * f[n][9]
 * 
 *  0   1    2    3   4    5    6   7    8
 * [v1][vt1][vn1][v2][vt2][vn2][v3][vt3][vn3]
 * [v1][vt1][vn1][v2][vt2][vn2][v3][vt3][vn3]
 * [v1][vt1][vn1][v2][vt2][vn2][v3][vt3][vn3]
 */

/**
 * TODO comment everything
 * 
 * @author Sebastian Dass&ecaute;
 *
 */
public class TriangleMesh extends Geometry {
	public final Point3[] vertices;
	public final TextureCoord[] textCoords;
	public final Normal3[] normals;
	public final int[][] faces;
	
	public TriangleMesh(final Material material, final Point3[] vertices, final TextureCoord[] textCoords, 
			final Normal3[] normals, final int[][] faces) {
		super(material);
		this.vertices = vertices;
		this.textCoords = textCoords;
		this.normals = normals;
		this.faces = faces;
	}

	@Override
	public Hit hit(final Ray ray) {
		
		Hit closestHit = null;
		double closestT = Double.POSITIVE_INFINITY;
		for (int[] face : faces) {
			final Point3 a = vertices[ face[0] ];
			final Point3 b = vertices[ face[3] ];
			final Point3 c = vertices[ face[6] ];
			
			/* //-- texture -- not yet used
			final TextCoord at;
			final TextCoord bt;
			final TextCoord ct;
			if (face[1] != 0) {
				at = textCoords[ face[1] ];
				bt = textCoords[ face[4] ];
				ct = textCoords[ face[7] ];
			} else {
				at = bt = ct = new TextCoord(0, 0);
			}
			*/
			
			final Normal3 an;
			final Normal3 bn;
			final Normal3 cn;
			if (face[2] != 0) {
				an = normals[ face[2] ];
				bn = normals[ face[5] ];
				cn = normals[ face[8] ];
			} else {
				an = bn = cn = b.sub(a).x(c.sub(a)).asNormal();
			}
			
			final Triangle tri = new Triangle(a, b, c, an, bn, cn, material);
			final Hit hit = tri.hit(ray);
			
			if (hit != null && hit.t < closestT) {
				closestHit = hit;
				closestT = hit.t;
			}
		}
		
		return closestHit;
	}
	
	// TODO -- for testing --> remove later
	public static TriangleMesh createTestTriangleMesh(final Material material) {
		return new TriangleMesh(
				material, 
				new Point3[]{
					new Point3(0, 0, 0), 
					new Point3(-0.5, -0.5, 0.5), 
					new Point3(0.5, -0.5, 0.5), 
					new Point3(0.5, 0.5, 0.5), 
					new Point3(-0.5, 0.5, 0.5), 
					new Point3(-0.5, -0.5, -0.5), 
					new Point3(0.5, -0.5, -0.5), 
					new Point3(0.5, 0.5, -0.5), 
					new Point3(-0.5, 0.5, -0.5)
				}, 
				new TextureCoord[0], 
				new Normal3[]{
					
				}, new int[][]{
					{1, 0, 0, 
					 2, 0, 0, 
					 3, 0, 0}, 
					{3, 0, 0, 
					 4, 0, 0, 
					 1, 0, 0}, 
					{2, 0, 0, 
					 6, 0, 0, 
					 7, 0, 0}, 
					{7, 0, 0, 
					 3, 0, 0, 
					 2, 0, 0}, 
					{6, 0, 0, 
					 5, 0, 0, 
					 8, 0, 0}, 
					{8, 0, 0, 
					 7, 0, 0, 
					 6, 0, 0}, 
					{5, 0, 0, 
					 1, 0, 0, 
					 4, 0, 0}, 
					{4, 0, 0, 
					 8, 0, 0, 
					 5, 0, 0}, 
					{4, 0, 0, 
					 3, 0, 0, 
					 7, 0, 0}, 
					{7, 0, 0, 
					 8, 0, 0, 
					 4, 0, 0}, 
					{5, 0, 0, 
					 6, 0, 0, 
					 2, 0, 0}, 
					{2, 0, 0, 
					 1, 0, 0, 
					 5, 0, 0}
				}
			);
	}
}
