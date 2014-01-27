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
 * TODO comment everything
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
			parseBasicData();
		} catch (DataFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed loading defective *.obj file.");
		}
		return createTriangleMash(material);
	}

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

	private TriangleMesh createTriangleMash(final Material material) {
		listAll();
		return new TriangleMesh(
				material, 
				vertices.toArray(new Point3[vertices.size()]), 
				textures.toArray(new TextureCoord[textures.size()]), 
				normals.toArray(new Normal3[normals.size()]), 
				faces);
	}
	private void parseBasicData() throws DataFormatException {
		String previousType = "";
		for (int lno = 0; lno < lines.size(); lno++) {
			String line = lines.get(lno);
			line = line.replaceAll("\\s+", " ");
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

	private void fillOutputLists(String line, String[] slots, String type)
			throws DataFormatException {
		switch (type) {
		case VERTICE:
			if (slots[0].trim() == "" || slots[1].trim() == "" ||  slots[2].trim() == "" ) {
				break;
			}
				vertices.add(new Point3(Double.parseDouble(slots[1]), Double.parseDouble(slots[2]), Double.parseDouble(slots[3])));
			break;
		case TEXTURE:
			if (slots[1].trim() == "" || slots[2].trim() == "") {
				break;
			}
			textures.add(new TextureCoord(Double.parseDouble(slots[1]), Double.parseDouble(slots[2])));
			break;
		case NORMAL:
			if (slots[1].trim() == "" || slots[2].trim() == "" || slots[3].trim() == "") {
				break;
			}
			normals.add(new Normal3(Double.parseDouble(slots[1]), Double.parseDouble(slots[2]), Double.parseDouble(slots[3])));
			break;
		case FACE:
			if (slots.length < 4) {
				throw new DataFormatException();
			}
			facesSourceLine.add(line);
			break;
		default:
			return;
		}
	}

	private void calibrateFacesArray(int[][] faces) {
		int minValue = Integer.MAX_VALUE;
		// FIND MINIMUM
		for (int i = 0; i < faces.length; i++) {
			for (int value : faces[i]) {
				if (Math.signum(value - minValue) == -1) {
					minValue = value;
				}
			}
		}
		// THROWS ERROR ---->
		 int compensation = (1 - minValue);
		 //COMPENSATE
		 for (int i = 0; i < faces.length; i++) {
		 for (int i2 = 0; i2 < faces[i].length; i2++) {
		 faces [i][i2] = faces [i][i2] + compensation;
		 }
		 }
	}

	private int[][] buildFacesArray() {
		int[][] result = new int[facesSourceLine.size()][9];
		
		/*
		 * Iterate linewise
		 */
		int count = 0;
		for (String face : facesSourceLine) {
			String[] blocks = face.replaceAll(FACE, "").trim()
					.split(BLANK);
			
			StringBuilder tupelsAsString = fillZeros(blocks);

			String[] ninerTupelsAsString = tupelsAsString.toString()
					.split(BLANK);
			
			for (int i = 0; i < ninerTupelsAsString.length; i++) {
				result[count][i] = Integer
						.parseInt(ninerTupelsAsString[i]);
			}
			count++;
		}
		return result;
	}

	private StringBuilder fillZeros(String[] blocks) {
		StringBuilder tupelsAsString = new StringBuilder();
		for (int i = 0; i < blocks.length; i++) {
			String slashFiltered = blocks[i].replace(SLASH, " ");
			String[] atomicElements = slashFiltered.split(BLANK);
			StringBuilder tupelFilled = new StringBuilder();
			tupelFilled.append(atomicElements[0]);
			for (int i2 = 1; i2 <= TUPELSIZE
					- atomicElements.length; i2++) {
				tupelFilled.append(" 0");
			}
			if (TUPELSIZE - atomicElements.length == 0) {
				tupelsAsString.append(slashFiltered + BLANK);
			} else {
				tupelsAsString.append(tupelFilled + BLANK);
			}
		}
		return tupelsAsString;
	}

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

	public void buildFaces() {

	}
}
