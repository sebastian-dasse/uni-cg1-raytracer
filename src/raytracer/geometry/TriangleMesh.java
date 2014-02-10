package raytracer.geometry;

import java.util.Arrays;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.texture.TexCoord2;

/**
 * This immutable class represents a triangle mesh, that is a polygon that comprises a set of triangles that are 
 * defined through a number of shared vertices. The triangular faces are further defined through a number of normals 
 * and coordinates for their texture.
 * <p>
 * A triangle mesh is automatically placed inside a bounding box for faster rendering.
 * 
 * @author Sebastian Dass&ecaute;
 *
 */
public class TriangleMesh extends Geometry {
	/**
	 * The bounding box enclosing this triangle mesh.
	 */
	public final AxisAlignedBoundingBox bbox;
	/**
	 * The vertices of this triangle mesh.
	 */
	private final Point3[] vertices;
	/**
	 * The texture coordinates for this triangle mesh.
	 */
	private final TexCoord2[] textCoords;
	/**
	 * The normals used in this triangle mesh.
	 */
	private final Normal3[] normals;
	/**
	 * The data for the faces of this triangle mesh. Address a single face by the first index and the data of each face 
	 * as follows:
	 * <pre>
	 * index: 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 
	 * type:  v1|vt1|vn1| v2|vt2|vn2| v3|vt3|vn3
	 * </pre>
	 */
	private final int[][] faces;
	
	/**
	 * Constructs a new <code>TriangleMesh</code> with the specified material, vertices, texture coordinates, normals 
	 * and faces.
	 * 
	 * @param material
	 * @param vertices
	 * @param textCoords
	 * @param normals
	 * @param faces
	 */
	public TriangleMesh(final Material material, final Point3[] vertices, final TexCoord2[] textCoords, 
			final Normal3[] normals, final int[][] faces) {
		super(material);
		this.vertices = vertices;
		this.textCoords = textCoords;
		this.normals = normals;
		this.faces = faces;
		bbox = new AxisAlignedBoundingBox(getMins(), getMaxs());
	}

	
	@Override
	public Hit hit(final Ray ray) {
		return bbox.isHit(ray) ? closestHit(ray) : null;
	}
	
	private Hit closestHit(final Ray ray) {
		Hit closestHit = null;
		double closestT = Double.POSITIVE_INFINITY;
		for (int[] face : faces) {
			final Point3 a = vertices[ face[0] ];
			final Point3 b = vertices[ face[3] ];
			final Point3 c = vertices[ face[6] ];
			
			final TexCoord2 at;
			final TexCoord2 bt;
			final TexCoord2 ct;
			if (face[1] != 0) {
				at = textCoords[ face[1] ];
				bt = textCoords[ face[4] ];
				ct = textCoords[ face[7] ];
			} else {
				at = bt = ct = new TexCoord2(0, 0);
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
			
			final Triangle tri = new Triangle(a, b, c, an, bn, cn, material, at, bt, ct);
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
				new TexCoord2[0], 
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

	/**
	 * Calculates the minimum x, y, z coordinates, which can be used as <em>low bottom far point</em> (lbf) of an axis 
	 * aligned box.
	 * 
	 * @return	The minimum x, y, z coordinates as <code>Point3</code>.
	 */
	public Point3 getMins() {
		double minX = Double.POSITIVE_INFINITY;
		double minY = Double.POSITIVE_INFINITY;
		double minZ = Double.POSITIVE_INFINITY;
		for (Point3 v : vertices) {
			if (v.x < minX) {
				minX = v.x;
			}
			if (v.y < minY) {
				minY = v.y;
			}
			if (v.z < minZ) {
				minZ = v.z;
			}
		}
		return new Point3(minX, minY, minZ);
	}

	/**
	 * Calculates the maximum x, y, z coordinates, which can be used as <em>right upper near point</em> (run) of an 
	 * axis aligned box.
	 * 
	 * @return	The maximum x, y, z coordinates as <code>Point3</code>.
	 */
	public Point3 getMaxs() {
		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;
		double maxZ = Double.NEGATIVE_INFINITY;
		for (Point3 v : vertices) {
			if (maxX < v.x) {
				maxX = v.x;
			}
			if (maxY < v.y) {
				maxY = v.y;
			}
			if (maxZ < v.z) {
				maxZ = v.z;
			}
		}
		return new Point3(maxX, maxY, maxZ);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bbox == null) ? 0 : bbox.hashCode());
		result = prime * result + Arrays.hashCode(faces);
		result = prime * result + Arrays.hashCode(normals);
		result = prime * result + Arrays.hashCode(textCoords);
		result = prime * result + Arrays.hashCode(vertices);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TriangleMesh other = (TriangleMesh) obj;
		if (bbox == null) {
			if (other.bbox != null)
				return false;
		} else if (!bbox.equals(other.bbox))
			return false;
		if (!Arrays.deepEquals(faces, other.faces))
			return false;
		if (!Arrays.equals(normals, other.normals))
			return false;
		if (!Arrays.equals(textCoords, other.textCoords))
			return false;
		if (!Arrays.equals(vertices, other.vertices))
			return false;
		return true;
	}	
}
