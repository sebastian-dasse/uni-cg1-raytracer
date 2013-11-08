package raytracer.image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImageCanvas extends Canvas {
	private BufferedImage image;
	protected Dimension size;
	
	public ImageCanvas(BufferedImage image) {
		this.image = image;
		size = new Dimension(640,480);
	}
	
	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public void paint(final Graphics g) {
		super.paint(g);
	    final WritableRaster raster = image.getRaster();
	    final ColorModel model = image.getColorModel();
	    Object dataRed = model.getDataElements(Color.red.getRGB(), null);
	    Object dataBlack = model.getDataElements(Color.black.getRGB(), null);
	    System.out.println(size.height+ "U"+size.width);
	    for (int x = 0; x < size.width; x++ ) {
	    	for (int y = 0; y < size.height; y++) {
	    		raster.setDataElements(x,y, dataBlack);
	    	}
	    }
	    int x = 0;
	    for (int y = 0; y < size.height; y++) {
	    	x++;
	    	raster.setDataElements(x,y, dataRed);
	    	
	    }
	    g.drawImage(image,0,0,null);
	}
}
