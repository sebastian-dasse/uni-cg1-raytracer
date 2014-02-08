package raytracer.texture;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import raytracer.Color;

public class InterpolatedImageTexture implements Texture{
	private final Raster imageRaster;
	private final int widthMinus1;
	private final int heightMinus1;
	
	public InterpolatedImageTexture(final String path){
		if (path == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		try {
			BufferedImage image = ImageIO.read(new File(path));
			imageRaster = image.getData();
			heightMinus1 = imageRaster.getHeight() - 1;
			widthMinus1 = image.getWidth() - 1;
		} catch (IOException e) {
			System.err.println("Problem reading file.");
			throw new RuntimeException("Could not construct image texture from the specified path.");
		}
	}
	
	@Override
	public Color getColor(final double u, final double v) {
		final double x = u * widthMinus1;
	    final double y = heightMinus1 - v * heightMinus1;
	    
	    final int x1 = (int) Math.floor(x);
	    final int x2 = (int) Math.ceil(x);
	    final int y1 = (int) Math.floor(y);
	    final int y2 = (int) Math.ceil(y);
	    
	    final double nx = x - x1;
	    final double ny = y - y1;
	    
//	    val a = image.getRGB(math.floor( x ).asInstanceOf[Int], math.floor( y ).asInstanceOf[Int])
//	    val b = image.getRGB(math.ceil( x ).asInstanceOf[Int], math.floor( y ).asInstanceOf[Int])
//	    val c = image.getRGB(math.floor( x ).asInstanceOf[Int], math.ceil( y ).asInstanceOf[Int])
//	    val d = image.getRGB(math.ceil( x ).asInstanceOf[Int], math.ceil( y ).asInstanceOf[Int])
	    
	    final Color x1y1 = new Color(imageRaster.getPixel(x1, y1, new double[3]));
	    final Color x2y1 = new Color(imageRaster.getPixel(x2, y1, new double[3]));
	    final Color x1y2 = new Color(imageRaster.getPixel(x1, y2, new double[3]));
	    final Color x2y2 = new Color(imageRaster.getPixel(x2, y2, new double[3]));
	    
//	    val (redA,greenA,blueA) = extract( a ) extract( argb : Int ) = ((argb & 0xff0000) >> 16,(argb & 0xff00) >> 8, argb & 0xff)
//	    val (redB,greenB,blueB) = extract( b )
//	    val (redC,greenC,blueC) = extract( c )
//	    val (redD,greenD,blueD) = extract( d )
	    
//	    Color a1 = new Color(x1y1[0], x1y1[1], x1y1[2]);
//	    Color b1 = new Color(x2y1[0], x2y1[1], x2y1[2]);
//	    Color c1 = new Color(x1y2[0], x1y2[1], x1y2[2]);
//	    Color d1 = new Color(x2y2[0], x2y2[1], x2y2[2]);
	    
//	    val (redE,greenE,blueE) = (redA*(1.0-xa) + (redB * xa), greenA*(1.0-xa) + (greenB * xa), blueA*(1.0-xa) + (blueB * xa) )
//	    val (redF,greenF,blueF) = (redC*(1.0-xa) + (redD * xa), greenC*(1.0-xa) + (greenD * xa), blueC*(1.0-xa) + (blueD * xa) )
	    
//	    Color a2 = new Color (a1.r * (1.0-nx) + b1.r * nx, a1.g * (1.0-nx) + b1.g * nx, a1.b * (1.0-nx) + b1.b * nx);
//	    Color b2 = new Color (c1.r * (1.0-nx) + d1.r * nx, c1.g * (1.0-nx) + d1.g * nx, c1.b * (1.0-nx) + d1.b * nx);
	    
	    final Color a = x1y1.mul(1.0 - nx).add(x2y1.mul(nx));
	    final Color b = x1y2.mul(1.0 - nx).add(x2y2.mul(nx));
	    
//	    val (red,green,blue) = (redE*(1.0-ya) + redF *ya , greenE* (1.0-ya) + greenF * ya, blueE*(1.0-ya) + blueF * ya )
	    
//	    Color fin = new Color(a2.r*(1.0-ny) + b2.r *ny , a2.g* (1.0-ny) + b2.g * ny, a2.b*(1.0-ny) + b2.b * ny);
	    
	    final Color c = a.mul(1.0 - ny).mul(b.mul(ny));

//	    return new Color(fin.r/255, fin.g/255, fin.b/255);
	    
	    return c;
	    
	}

	@Override
	public Color getColor(final TexCoord2 textcoord) {
		return getColor(textcoord.u, textcoord.v);
	}
}
