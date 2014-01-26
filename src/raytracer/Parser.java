package raytracer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.texture.TextCoord;

public class Parser {
	public final String VERTICE = "v";
	public final String TEXTURE = "vt";
	public final String NORMAL = "vn";
	public final String FACE = "f";
	public final String COMMENT = "#";
	public final String SLASH = "/";
	public final String BLANK = " ";
	public final int FACELENGTH = 9;
	public final int TUPELSIZE = 3;
	
	LinkedList <String> lines;
	private Collection<Point3> vertices;
	private Collection<TextCoord> textures;
	private Collection<Normal3> normals;
	private Collection<String> facesSourceLine;
	private int faces[][];
	
	public Parser() {
		vertices = new LinkedList<Point3>();
		textures = new LinkedList<TextCoord>();
		normals = new LinkedList<Normal3>();
		facesSourceLine = new LinkedList<String>();
		lines = new LinkedList<String>();
	}
	
	public void readFile(File f) {
		try {
			Scanner in = new Scanner(f);
			while (in.hasNext()) {
				lines.add(in.nextLine());
			}
		}
		catch (IOException e) {
			System.err.println("Error reading file. Please check that you're not drunk.");
		}
	}
	
	
	public void parseBasicData() throws DataFormatException {
		String previousType = "";
		for (int lno = 0; lno < lines.size(); lno++) {
			/*
			 * Line processing
			 */
			String line = lines.get(lno);
			String[] slots = line.split(" ");

			/*
			 * Indicators
			 */
			final String type = slots[0];
			if (lno > 0) {
				previousType = lines.get(lno - 1).split(" ")[0];
			}

			/*
			 * Special cases
			 */
			if (line.startsWith(COMMENT)) {
				continue;
			}

			if (type.equals(FACE) && (slots.length - 1) < 3) {
				throw new DataFormatException();
			}

			/*
			 * Process faces to int array
			 */
			final boolean objectEnd = ((previousType.equals(FACE) && !type
					.equals(FACE)) || lno == lines.size() - 1);

			if (type.equals(FACE)) {
				facesSourceLine.add(line);
			} else {
				double slotsAsDouble[] = new double[slots.length - 1];
				for (int i = 1; i < slots.length; i++) {
					slotsAsDouble[i - 1] = slots[i].isEmpty() ? Double.NaN
							: Double.parseDouble(slots[i]);
				}
				if (type.equals(VERTICE)) {
					vertices.add(new Point3(Double.parseDouble(slots[1]),
							Double.parseDouble(slots[2]), Double
									.parseDouble(slots[3])));
				}

				if (type.equals(TEXTURE)) {
					textures.add(new TextCoord(Double.parseDouble(slots[1]),
							Double.parseDouble(slots[2])));
				}

				if (type.equals(NORMAL)) {
					normals.add(new Normal3(Double.parseDouble(slots[1]),
							Double.parseDouble(slots[2]), Double
									.parseDouble(slots[3])));
				}
			}
			if (objectEnd) {
				/*
				 * Done reading non-face data from file
				 */
				int[][] result = new int[facesSourceLine.size()][9];
				int count = 0;
				/*
				 * Iterate linewise
				 */
				for (String face : facesSourceLine) {
					/*
					 * Split line into blocks
					 */
					String[] blocks = face.replaceAll(FACE, "").trim()
							.split(BLANK);
					StringBuilder tupelsAsString = new StringBuilder();
					/*
					 * Retrieve blocks: Fill them up with 0s if necessary
					 */
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
					/*
					 * Build String array with one entry per desired NINER - tupel
					 */
					String[] ninerTupelsAsString = tupelsAsString.toString()
							.split(BLANK);
					
					/*
					 * Convert string array into 2-Dimensional int array
					 */
					for (int i = 0; i < ninerTupelsAsString.length; i++) {
						result[count][i] = Integer
								.parseInt(ninerTupelsAsString[i]);
					}
					
					count++;
				}
				
				/*
				 * Remap
				 */
				
				int minValue = Integer.MAX_VALUE;
				// FIND MINIMUM
				for (int i = 0; i < result.length; i++) {
					for (int value : result[i]) {
						if (Math.signum(value - minValue) == -1) {
							minValue = value;
						}
					}
				}
			    // THROWS ERROR ----> 
				int compensation = (1 - minValue);
				//COMPENSATE
				for (int i = 0; i < result.length; i++) {
					for (int i2 = 0; i2 < result[i].length; i2++) {
						faces [i][i2] = result [i][i2] + compensation;
					}
				}
				faces = result;
			}

		}
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
