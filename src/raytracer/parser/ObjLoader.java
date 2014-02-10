package raytracer.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import raytracer.geometry.TriangleMesh;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.texture.TexCoord2;

/**
 * TODO weird indices are not working yet.
 * 
 * This class is a loader for OBJ files. It can parse <code>TriangleMesh</code>es from such a file.
 * <p>
 * Vertices, texture coordinates, normals and faces can be loaded.
 * <p>
 * For further information on the OBJ format 
 * <a href="http://www.martinreddy.net/gfx/3d/OBJ.spec">check out the documentation</a>.
 *  
 * @author Simon Lischka
 * @author Sebastian Dass&ecaute;
 *
 */
public class ObjLoader {
	/**
	 * A regex for a double number like this: <code>-0.123E-3</code>
	 */
	public static final String DOUBLE_NUM = "([+-]?\\d+(\\.\\d+([eE][+-]\\d+)?)?)";
	/**
	 * A regex for a face like this: <code>f 1 2 3</code>
	 */
	public static final String V = "(\\s+[+-]?\\d+){3}\\s*";
	/**
	 * A regex for a face like this: <code>f 1/1 2/2 3/3</code>
	 */
	public static final String V_VT = "(\\s+[+-]?\\d+/[+-]?\\d+){3}\\s*";
	/**
	 * A regex for a face like this:<code>f 1//1 2//2 3//3</code>
	 */
	public static final String V_VN = "(\\s+[+-]?\\d+//[+-]?\\d+){3}\\s*";
	/**
	 * A regex for a face like this: <code>f 1/1/1 2/2/2 3/ 3/3/3</code>
	 */
	public static final String V_VT_VN = "(\\s+[+-]?\\d+/[+-]?\\d+/[+-]?\\d+){3}\\s*";
	/**
	 * A regex for a valid face.
	 */
	public static final String FACE = "^f(" + V + "|" + V_VT + "|" + V_VN + "|" + V_VT_VN + ")";

	private Collection<String> lines;
	private Collection<Point3> vertices;
	private Collection<TexCoord2> textures;
	private Collection<Normal3> normals;
	private Collection<int[]> faces;

	public ObjLoader() {
		vertices = new ArrayList<Point3>();
		vertices.add(new Point3(0, 0, 0));		// unused point at first position for easier array indexing 
		textures = new ArrayList<TexCoord2>();
		textures.add(new TexCoord2(0, 0));		// unused normal at first position for easier array indexing
		normals = new ArrayList<Normal3>();
		normals.add(new Normal3(1, 1, 1));		// unused normal at first position for easier array indexing
		lines = new LinkedList<String>();
		faces = new LinkedList<int[]>();
	}

	/**
	 * Loads a model from an OBJ file and returns it as <code>TriangleMesh</code>.
	 * 
	 * @param filename	The name of the model file.
	 * @param material	The material of the of the shape to be loaded.
	 * @return			The loaded model as <code>TriangleMesh</code>.
	 */
	public TriangleMesh load(final String filename, final Material material) {
		System.out.println("Loading...");
		read(filename);
		try {
			parse();
		} catch (DataFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed loading defective *.obj file.");
		}
		System.out.println("Done loading.");
		return createTriangleMesh(material);
	}
	
    /**
     * Reads the basic data required to build objects for <code>TriangleMesh</code>
     * Fills the <code>lines</list> list with data.
     * 
     * @param filename The name of the model file
     * 
     */
	private void read(final String filename) {
		Scanner in = null;
		try {
			if (!filename.endsWith(".obj")) {
				throw new IOException("not an OBJ file");
			}
			in = new Scanner(new File(filename));
			while (in.hasNext()) {
				lines.add(in.nextLine());
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.err.println("Error reading file. Please check that you're not drunk.");
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * Converts the Collection structures into arrays and creates a <code> triangle mesh</code>.
	 * @return TriangleMesh
	 */
	private TriangleMesh createTriangleMesh(final Material material) {
		return new TriangleMesh(
				material, 
				vertices.toArray(new Point3[vertices.size()]), 
				textures.toArray(new TexCoord2[textures.size()]), 
				normals.toArray(new Normal3[normals.size()]), 
				faces.toArray(new int[][]{}));
	}
	
	private void parse() throws DataFormatException {
		for (String line : lines) {
			if (isNoValidLine(line)) {
				throw new DataFormatException();
			}
			final String[] token = line.split("\\s+|/");
			if (isVertice(line)) {
//				System.out.println("vertice: " + line);
				vertices.add(new Point3(Double.parseDouble(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[3])));
			} else if (isTexture(line)) {
//				System.out.println("texture: " + line);
				textures.add(new TexCoord2(Double.parseDouble(token[1]), Double.parseDouble(token[2])));
			} else if (isNormal(line)) {
//				System.out.println("normal: " + line);
				normals.add(new Normal3(Double.parseDouble(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[3])));
			} else if (isFace(line)) {
//				System.out.println("face: " + line);
				faces.add(parseFace(line, token));
			} else {
//				System.out.println("comment: " + line); // -- for debugging
			}
			// if line is comment or blank ignore and continue
		}

		printListSizes(); // -- for debugging
	}

	private int[] parseFace(final String line, final String[] tokens) throws DataFormatException {
//		printTokens(tokens); // -- for debugging
		
		if (line.matches("^f" + V)) {
			return new int[]{
					Integer.parseInt(tokens[1]), 0, 0, 
					Integer.parseInt(tokens[2]), 0, 0, 
					Integer.parseInt(tokens[3]), 0, 0
					
			};
		}
		if (line.matches("^f" + V_VT)) {
			return new int[]{
					Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 0, 
					Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), 0, 
					Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), 0
					
			};
		}
		if (line.matches("^f" + V_VN)) {
			return new int[]{
					Integer.parseInt(tokens[1]), 0, Integer.parseInt(tokens[3]), 
					Integer.parseInt(tokens[4]), 0, Integer.parseInt(tokens[6]), 
					Integer.parseInt(tokens[7]), 0, Integer.parseInt(tokens[9])
			};
		}
		if (line.matches("^f" + V_VT_VN)) {
			return new int[]{
					Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), 
					Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), 
					Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9])
			};
		}
		throw new DataFormatException(); // should never happen
	}
	
	private boolean isNoValidLine(final String line) {
		return !line.matches("(^\\s*$)|(^v[nt]?|f|#).*");
	}	
	
	private boolean isVertice(final String line) {
		return line.matches("^v(\\s+" + DOUBLE_NUM + "){3}\\s*");
	}

	private boolean isTexture(final String line) {
		return line.matches("^vt(\\s+" + DOUBLE_NUM + "){3}\\s*");
	}
	
	private boolean isNormal(final String line) {
		return line.matches("^vn(\\s+" + DOUBLE_NUM + "){3}\\s*");
	}
	
	private boolean isFace(final String line) {
		return line.matches(FACE);
	}
	
	/** 
	 * Lists all tokens from an array for debugging.
	 */
	private void printTokens(final String[] tokens) {
		for (String s : tokens) {
			System.out.printf("%s ", s);
		}
		System.out.println();
	}
	
	/** 
	 * Prints all list sizes for development and debugging.
	 */
	private void printListSizes() {
		System.out.println("number of vertices:   " + vertices.size());
		System.out.println("number of txtrcoords: " + textures.size());
		System.out.println("number of normals:    " + normals.size());
		System.out.println("number of faces:      " + faces.size());
	}

	/**
	 * Currently under development. Should calibrate the indexes of the faces array.
	 * @param faces
	 */
	private void calibrateFacesArray(int[][] faces) {
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < faces.length; i++) {
			for (int value : faces[i]) {
				if (Math.signum(value - minValue) == -1) {
					minValue = value;
				}
			}
		}
		int compensation = (1 - minValue);
		for (int i = 0; i < faces.length; i++) {
			for (int i2 = 0; i2 < faces[i].length; i2++) {
				faces [i][i2] = faces [i][i2] + compensation;
			}
		 }
	}

	/**
	 * Lists the internal data structures for debugging purposes.
	 * Prints: vertices, textures, normals and faces.
	 */
	public void listAll() {
		System.out.println("Vertices");
		System.out.println("--------------------------");
		System.out.println(vertices.toString());
		System.out.println();
		System.out.println("Textures");
		System.out.println("--------------------------");
		System.out.println(textures.toString());
		System.out.println();
		System.out.println("Normals");
		System.out.println("--------------------------");
		System.out.println(normals.toString());
		System.out.println();
		for (int[] i1 : faces) {
			System.out.println("--");
			for (int i2 : i1) {
				System.out.println(i2);
			}
		}
	}
}
