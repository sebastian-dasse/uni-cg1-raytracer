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
	LinkedList <String> lines;
	private Collection<Point3> vertices;
	private Collection<TextCoord> textures;
	private Collection<Normal3> normals;
	private Collection<String> faces;
	
	public Parser() {
		vertices = new LinkedList<Point3>();
		textures = new LinkedList<TextCoord>();
		normals = new LinkedList<Normal3>();
		faces = new LinkedList<String>();
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
			String [] slots = line.split(" ");
			
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
			
			final boolean objectEnd = ((previousType.equals(FACE) && !type.equals(FACE) || lno == lines.size() - 1));
			if (objectEnd) {
				System.out.println("Vertices");
				System.out.println("=======================");
				listAll(vertices);
				System.out.println("Textures");
				System.out.println("=======================");
				listAll(textures);
				System.out.println("Normals");
				System.out.println("=======================");
				listAll(normals);
				System.out.println("Faces");
				System.out.println("=======================");
				listAll(faces);
			}
			
			
			if (type.equals(FACE)) {
				for (int i = 1; i < slots.length; i++) {
					faces.add(slots[i]);
				}
				
			} else {
				double slotsAsDouble [] = new double [slots.length - 1];
				for (int i = 1; i < slots.length; i++)  {
					slotsAsDouble[i - 1] = slots[i].isEmpty() ? Double.NaN : Double.parseDouble(slots[i]);
				}
				if (type.equals(VERTICE)) {
					vertices.add(
							new Point3(
									Double.parseDouble(slots[1]),
									Double.parseDouble(slots[2]), 
									Double.parseDouble(slots[3])
									)
					);
				}

				if (type.equals(TEXTURE)) {
					textures.add(
							new TextCoord(
									Double.parseDouble(slots[1]),
									Double.parseDouble(slots[2])
									)
					);
				}

				if (type.equals(NORMAL)) {
					normals.add(
							new Normal3(
									Double.parseDouble(slots[1]),
									Double.parseDouble(slots[2]), 
									Double.parseDouble(slots[3])
									)
					);
				}
			}
		}
	}
	
	public void listAll(Collection<?> c) {
		for (Object o : c) {
			System.out.println(o);
		}
	}
	public void buildFaces() {
		
	}
}
