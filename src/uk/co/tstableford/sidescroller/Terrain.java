package uk.co.tstableford.sidescroller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Terrain {
	private Random rand;
	private int[] terrain;
	private int ter_size_pixels;
	private int max_height_squares;
	private int width_squares;
	private int height_pixels;
	private Texture top, ground;
	private BufferedImage built;
	public Terrain(long seed, int width_squares, int height_pixels, int max_height_squares, int ter_size_pixels){
		this.rand = new Random(seed);
		this.max_height_squares = max_height_squares;
		this.ter_size_pixels = ter_size_pixels;
		this.width_squares = width_squares;
		this.height_pixels = height_pixels;
		terrain = new int[width_squares];
		int last = rand.nextInt(this.max_height_squares);
		for(int i=0; i<this.width_squares; i++){
			terrain[i] = rand.nextInt(this.max_height_squares);
			while(Math.abs(terrain[i]-last)>1){
				terrain[i] = rand.nextInt(max_height_squares);
			}
			last = terrain[i];
		}
	}
	public Terrain(int width_squares, int height_pixels, int max_height_squares, int ter_size_pixels){
		this(new Random().nextLong(), width_squares, height_pixels, max_height_squares, ter_size_pixels);
	}
	public BufferedImage getTerrain(Texture top, Texture ground){
		if(this.top==null||this.top!=top||this.ground==null||this.ground!=ground||built==null){
			this.top = top;
			this.ground= ground;
			built = generateTerrain();
		}
		return built;
	}
	private BufferedImage generateTerrain(){
		BufferedImage b = new BufferedImage(this.width_squares*ter_size_pixels, height_pixels, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D)b.getGraphics();
		g.rotate(Math.PI, this.getWidth()*ter_size_pixels/2, height_pixels/2);
		for(int x=0; x<this.getWidth(); x++){
			int h = this.getHeight(x);
			for(int y=h; y+1>0; y--){
				Texture t = ground;
				if(y==h){
					t = top;
				}
				Square s = new Square(x*ter_size_pixels, y*ter_size_pixels, ter_size_pixels, ter_size_pixels, t, Math.PI);
				s.paint(g);
			}
		}
		return b;
	}
	public int getWidth(){
		return terrain.length;
	}
	public int getHeight(int location){
		return terrain[location];
	}
}
