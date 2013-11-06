package raytracer.image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImageCanvas extends Canvas {
	private BufferedImage image;
	
	public ImageCanvas(BufferedImage image) {
		this.image = image;
	}
	
	public void paint(final Graphics g) {
		super.paint(g);
		image = new BufferedImage(640,480,BufferedImage.TYPE_INT_ARGB);
	    final WritableRaster raster = image.getRaster();
	    final ColorModel model = image.getColorModel();
	    Object colorData = model.getDataElements(Color.blue.getRGB(), null);
	    int width = raster.getWidth();
	    int height = raster.getHeight();
	    for (int i = 0; i < width; i++) {
	    	for (int j = 0; j < height; j++) {
	    		raster.setDataElements(i,j, colorData);
	    	}
	    }
	    g.drawImage(image,0,0,null);
	}
}
