package raytracer.tests.graphical;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.Scanner;

import raytracer.math.Point3;

public class MySense {
	public final String VERTICE = "v";
	
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
	
	
	public Collection<Point3> getVertices(Collection<String> lines) {
		Collection<Point3> vertices = new LinkedList<Point3>();
		for (String line : lines) {
			String [] slots = line.split(" ");
			if (slots[0] == VERTICE) {
				vertices.add(
						new Point3(
								Double.parseDouble(slots[1]),
								Double.parseDouble(slots[2]), 
								Double.parseDouble(slots[3])
						)
				);
			}
		}
		return vertices;
	}
	
	public static void main (String[] args) {
//		
//		int [] values = new int[3];
		System.out.println(values[2]);
		
		int [] values = {
				1,
				2,
				3
		};
		
		int [][] values2 = {
				{1}{2}
				}; 
				
	}
}
