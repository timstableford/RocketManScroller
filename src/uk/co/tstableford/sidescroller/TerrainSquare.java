package uk.co.tstableford.sidescroller;

import java.awt.Graphics2D;

public class TerrainSquare {
	int x,y,w,h;
	private Texture t;
	private double rotation;
	public TerrainSquare(int x, int y, int w, int h, Texture texture, double rotation){
		this.x = x; this.y = y; this.w = w; this.h = h;
		this.t = texture;
		this.rotation = rotation;
	}
	public void paint(Graphics2D g){
		g.rotate(rotation, x+w/2, y+h/2);
		g.drawImage(t.get(),x,y,w,h,null);
		g.rotate(-rotation, x+w/2, y+h/2);
	}
}
