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
import raytracer.texture.TextureCoord;

/**
 * 
 * @author Simon Lischka
 * @author Sebastian Dass&ecaute;
 *
 */
public class ObjLoader {
	public final String VERTICE = "v";
	public final String TEXTURE = "vt";
	public final String NORMAL = "vn";
	public final String FACE = "f";
	public final String COMMENT = "#";
	public final String SLASH = "/";
	public final String BLANK = " ";
	public final String EMPTY = "";
	public final int FACELENGTH = 9;
	public final int TUPELSIZE = 3;

	private LinkedList<String> lines;
	private Collection<Point3> vertices;
	private Collection<TextureCoord> textures;
	private Collection<Normal3> normals;
	private Collection<String> facesSourceLine;
	private int[][] faces;

	public ObjLoader() {
		vertices = new ArrayList<Point3>();
		vertices.add(new Point3(0, 0, 0));
		textures = new ArrayList<TextureCoord>();
		normals = new ArrayList<Normal3>();
		facesSourceLine = new ArrayList<String>();
		lines = new LinkedList<String>();
		faces = new int[0][0];
	}

	/**
	 * Loads a model from an OBJ file and returns it as <code>TriangleMesh</code>.
	 * 
	 * @param filename	The name of the model file.
	 * @param material	The material of the of the shape to be loaded.
	 * @return			The loaded model as <code>TriangleMesh</code>.
	 */
	public TriangleMesh load(final String filename, final Material material) {
		read(filename);
		try {
			parse();
		} catch (DataFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed loading defective *.obj file.");
		}
//		listAll(); // DEBUG
		return createTriangleMesh(material);
	}
    /**
     * Reads the basic data required to build objects for <code>TriangleMesh</code>
     * Fills the <code>lines</list> list with data.
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
				textures.toArray(new TextureCoord[textures.size()]), 
				normals.toArray(new Normal3[normals.size()]), 
				faces);
	}
	
	/**
	 * Main parser loop.
	 * Fills the collections with the basic sections that can be found in an obj file.
	 * Calls the <code>fillOutputLists</code> method to extract all the sections of the file
	 * minus the faces list, which depends on the fillOutputList's data.
	 * 
	 */
	private void parse() throws DataFormatException {
		String previousType = "";
		System.out.println(lines.size());
		for (int lno = 0; lno < lines.size(); lno++) {
			String line = lines.get(lno);
			
			line = line.replaceAll("\\s+", " ");
			line = line.replaceAll("//", "0");
			System.out.println(line);
			String[] slots = line.split(" ");
			String type = slots[0];
			if (type.equals(COMMENT) || type.equals(EMPTY)) {
				continue;
			}
			if (type.equals(FACE) && (slots.length - 1) < 3) {
				throw new DataFormatException();
			}
			if (lno > 0) {
				previousType = lines.get(lno - 1).split(" ")[0];
			}
			fillOutputLists(line, slots, type);
			
			if (((previousType.equals(FACE) && !type
					.equals(FACE)) || lno == lines.size() - 1)) {
				
				faces = buildFacesArray();
//				calibrateFacesArray(faces);
			}

		}
	}

	/**
	 *  Interprets a line in the obj file - acts according to found type:
	 *  <code> VERTICE </code> - add new Point3 object to vertices list
	 *  <code> TEXTURE </code> - add new TextureCoord object to textures list
	 *  <code> NORMAL </code> - add new Normal to normals list
	 *  <code> FACE </code> - add new face to facesSourcesList - this list is later used by buildFacesArray() to 
	 *  generate the faces.
	 */
	private void fillOutputLists(String line, String[] slots, String type)
			throws DataFormatException {
		switch (type) {
		case VERTICE:
				vertices.add(new Point3(Double.parseDouble(slots[1]), Double.parseDouble(slots[2]), Double.parseDouble(slots[3])));
			break;
		case TEXTURE:
			textures.add(new TextureCoord(Double.parseDouble(slots[1]), Double.parseDouble(slots[2])));
			break;
		case NORMAL:
			normals.add(new Normal3(Double.parseDouble(slots[1]), Double.parseDouble(slots[2]), Double.parseDouble(slots[3])));
			break;
		case FACE:
			// Gleich 'n Slot rein!
			facesSourceLine.add(line);
			break;
		default:
			return;
		}
	}

	/**
	 * Current under development. Should calibrate the indexes of the faces array.
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
	 *  Constructs the faces array - processes a single line element from the facesSourceLine-Collection.
	 *  First trims the "f" prefix away, then adds zeros (fillZeros method) to the blocks if a block form the source file 
	 *  doesn't contain 3 numbers (e.g. 1 2 3 instead of 1/1/1 2/2/2 3/3/3). 
	 *  Then builds splits the filtered string into a new Array (still String) which is later converted
	 *  into the [][] two dimensional required for output.
	 *  
	 *  @return result - A two dimensional array (TODO: describe Array here.)
	 *  
	 */
	private int[][] buildFacesArray() {
		int[][] result = new int[facesSourceLine.size()][9];
		int count = 0;
		for (String face : facesSourceLine) {
			String[] line = face.replaceAll(FACE, "").trim()
					.split(BLANK);
			
			int[] numbers = parseIntArray(line);
			numbers = fillZeros(numbers);
			
			for (int i = 0; i < numbers.length; i++) {
				result[count][i] = numbers[i];
			}
			count++;
		}
		return result;
	}

	private int [] parseIntArray(String[] strs) {
		int[] numbers = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			numbers[i] = Integer.parseInt(strs[i]);
		}
		return numbers;
	}
	
	/**
	 * Format conversion: Fills up zeros of given block-Array
	 * if single numbers are found (used for faces).
	 * E.g. 1 2 3 to 100 200 300.
	 * @return tupelsAsString A stringBuilder object containing the newly formated line.
	 */
	
	private int[] fillZeros(int[] numbers) {
		int [] filledNumbers = new int[9];
		if (numbers.length == 3) {
			for (int i = 0; i < 9; i++) {
				filledNumbers[i] = i / 3;
			}
			return filledNumbers;
		}
			return numbers;
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
