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
			final int a = face[0];
			final int b = face[3];
			final int c = face[6];
			
			final int at = face[1];
			final int bt = face[4];
			final int ct = face[7];
			
			final int an = face[2];
			final int bn;
			final int cn;
			if (an != 0) {
				bn = 0;
				cn = 0;
			} else {
				bn = face[5];
				cn = face[8];
			}
			
		}
		
		return null;
	}
}
