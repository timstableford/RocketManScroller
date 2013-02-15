package uk.co.tstableford.sidescroller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	private BufferedImage i;
	public Texture(String path){
		try {
			i = ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			i = null;
		}
	}
	public BufferedImage get(){
		return i;
	}
}
