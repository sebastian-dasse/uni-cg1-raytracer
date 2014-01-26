package raytracer.tests.graphical;

import java.io.File;
import java.util.zip.DataFormatException;

import raytracer.Parser;


public class ParserTest {
	public static void main (String[] args) {
		Parser parser = new Parser();
		String path = "D:/UNI/2013-WiSe/CG1/UE/models";
//		parser.readFile(new File("/Users/funkjaymatada/Beuth/CGI/szenengraph/models/cube-v-vt-vn.obj"));
//		parser.readFile(new File("/Users/funkjaymatada/Beuth/CGI/szenengraph/models/cube-v.obj"));
		parser.readFile(new File(path + "/cube-v.obj"));
		
		try {
			parser.parseBasicData();
			parser.listAll();
		}
		catch (DataFormatException e) {
			System.err.println("You suck");
		}
	}
}
