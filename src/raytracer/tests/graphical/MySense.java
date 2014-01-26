package raytracer.tests.graphical;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.texture.TextCoord;

public class MySense {
	public final String VERTICE = "v";
	public final String TEXTURE = "vt";
	public final String NORMAL = "vn";
	public final String FACE = "f";
	
	public Collection<String> readFile(File f) {
		Collection<String> lines = new LinkedList<String>();
		
		try {
			Scanner in = new Scanner(f);
			while (in.hasNext()) {
				lines.add(in.nextLine());
			}
		}
		catch (IOException e) {
			System.err.println("Error reading file. Please check that you're not drunk.");
		}
		return lines;
	}
	
	
	public Collection<Point3> getVertices(Collection<String> lines) throws DataFormatException {
		Collection<Point3> vertices = new LinkedList<Point3>();
		Collection<TextCoord> textures = new LinkedList<TextCoord>();
		Collection<Normal3> normals = new LinkedList<Normal3>();
		Collection<String[]> faces = new LinkedList<String[]>();
		for (String line : lines) {
			String [] slots = line.split(" ");
			final String type = slots[0];
			if (type == FACE) {
				if ((slots.length - 1) < 3) {
					throw new DataFormatException();
				}
				faces.add(new String[] {
						slots[1],
						slots[2],
						slots[3],
				});
			}
			if (type != FACE) {
				double slotsAsDouble [] = new double [slots.length - 1];
				for (int i = 1; i < slots.length; i++)  {
					slotsAsDouble[i] = slots[i].isEmpty() ? Double.NaN : Double.parseDouble(slots[i]);
				}

				if (type == VERTICE) {
					vertices.add(
							new Point3(
									Double.parseDouble(slots[1]),
									Double.parseDouble(slots[2]), 
									Double.parseDouble(slots[3])
									)
					);
				}

				if (type == TEXTURE) {
					textures.add(
							new TextCoord(
									Double.parseDouble(slots[1]),
									Double.parseDouble(slots[2])
									)
					);
				}

				if (type == NORMAL) {
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

		return vertices;
	}
	
	public static void main (String[] args) {
//		
//		int [] values = new int[3];
		System.out.println(Double.parseDouble(""));
		
	}
}
