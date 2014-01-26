package raytracer.geometry;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.texture.TextCoord;

/*
 * -- Notiz --
 * Zugriff auf Arrays: 
 * f[0][3]
 * 
 *  0   1    2    3   4    5    6   7    8
 * [v1][vt1][vn1][v2][vt2][vn2][v3][vt3][vn3]
 * [v1][vt1][vn1][v2][vt2][vn2][v3][vt3][vn3]
 * [v1][vt1][vn1][v2][vt2][vn2][v3][vt3][vn3]
 */

public class TriangleMesh extends Geometry {
	public final Point3[] vertices;
	public final TextCoord[] textCoords;
	public final Normal3[] normals;
	public final int[][] faces;
	
	public TriangleMesh(final Material material, final Point3[] vertices, final TextCoord[] textCoords, 
			final Normal3[] normals, final int[][] faces) {
		super(material);
		this.vertices = vertices;
		this.textCoords = textCoords;
		this.normals = normals;
		this.faces = faces;
	}

	@Override
	public Hit hit(final Ray ray) {
		
		for (int[] face : faces) {
			final Point3 a = vertices[ face[0] ];
			final Point3 b = vertices[ face[3] ];
			final Point3 c = vertices[ face[6] ];
			
			/* //-- texture -- not yet used 
			*/
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
			
			Triangle tri = new Triangle(a, b, c, an, bn, cn, material);
			Hit hit = tri.hit(ray);
		}
		
		return null;
	}
}
