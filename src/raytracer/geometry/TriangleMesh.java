package raytracer.geometry;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.texture.TextureCoord;

/**
 * This immutable class represents a triangle mesh, that is a polygon that comprises a set of triangles that are 
 * defined through a number of shared vertices. The triangular faces are further defined through a number of normals 
 * and coordinates for their texture.
 * 
 * TODO add comments on bounding box
 * 
 * @author Sebastian Dass&ecaute;
 *
 */
public class TriangleMesh extends Geometry {
	/**
	 * The vertices of this triangle mesh.
	 */
	private final Point3[] vertices;
	/**
	 * The texture coordinates for this triangle mesh.
	 */
	private final TextureCoord[] textCoords;
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
	
	public final AxisAlignedBoundingBox bbox;
	
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
	public TriangleMesh(final Material material, final Point3[] vertices, final TextureCoord[] textCoords, 
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
	
//	@Override
	public Hit closestHit(final Ray ray) {
		Hit closestHit = null;
		double closestT = Double.POSITIVE_INFINITY;
		for (int[] face : faces) {
			final Point3 a = vertices[ face[0] ];
			final Point3 b = vertices[ face[3] ];
			final Point3 c = vertices[ face[6] ];
			
			/* //-- texture -- not yet used
			final TextureCoord at;
			final TextureCoord bt;
			final TextureCoord ct;
			if (face[1] != 0) {
				at = textCoords[ face[1] ];
				bt = textCoords[ face[4] ];
				ct = textCoords[ face[7] ];
			} else {
				at = bt = ct = new TextureCoord(0, 0);
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
}
