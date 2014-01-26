package raytracer.tests.graphical;

import java.io.File;
import java.util.zip.DataFormatException;

import raytracer.Parser;


public class ParserTest {
	public static void main (String[] args) {
		Parser parser = new Parser();
		parser.readFile(new File("/Users/funkjaymatada/Beuth/CGI/szenengraph/models/cube-v-vt-vn.obj"));
		try {
			parser.parseBasicData();
		}
		catch (DataFormatException e) {
			System.err.println("You suck");
		}
	}
}
