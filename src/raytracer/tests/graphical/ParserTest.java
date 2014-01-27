package raytracer.tests.graphical;

import java.util.zip.DataFormatException;

import raytracer.Color;
import raytracer.ObjLoader;
import raytracer.material.SingleColorMaterial;


public class ParserTest {
	public static void main (String[] args) {
		ObjLoader parser = new ObjLoader();
//		parser.readFile(new File("/Users/funkjaymatada/Beuth/CGI/szenengraph/models/cube-v-vt-vn.obj"));
//		parser.readFile(new File("/Users/funkjaymatada/Beuth/CGI/szenengraph/models/cube-v.obj"));
		
		parser.load("models/cube-v.obj", new SingleColorMaterial(new Color(0, 0, 0)));
		
//		try {
//			parser.parseBasicData();
//			parser.listAll();
//		}
//		catch (DataFormatException e) {
//			System.err.println("You suck");
//		}
	}
}
