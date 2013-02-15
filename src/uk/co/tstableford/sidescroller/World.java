package uk.co.tstableford.sidescroller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.tstableford.sidescroller.interfaces.Item;

public class World implements Item{
	public static final int TER_SIZE = 40;
	private static final int MAX_TER_HEIGHT = 6;
	private Terrain terrain;
	private List<Sprite> sprites;
	private HashMap<String, Texture> textures;
	private int height_pixels, width_pixels;
	private Sprite focus;
	public World(int width_pixels, int height_pixels, HashMap<String, Texture> textures){
		this.textures = textures;
		terrain = new Terrain(width_pixels/TER_SIZE, height_pixels, MAX_TER_HEIGHT, TER_SIZE);
		sprites = new ArrayList<Sprite>();
		this.height_pixels = height_pixels;
		this.width_pixels = width_pixels;
	}
	public void addSprite(Sprite s){
		sprites.add(s);
	}
	@Override
	public void update(long dT) {
		for(Sprite s: sprites){
			if(s.isActive()&&s instanceof MassSprite){
				MassSprite ms = (MassSprite)s;
				ms.update(dT);
			}
		}
	}
	public void setFocus(Sprite s){
		this.focus = s;
	}
	@Override
	public void paint(Graphics g) {
		int dx=0,dy=0;
		if(focus!=null){
			dx = -(int)focus.getX()+width_pixels/2;
			dy = -(int)focus.getY()+height_pixels/2;
		}
		BufferedImage i = terrain.getTerrain(textures.get("top"), textures.get("ground"));
		BufferedImage b = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D gb = b.createGraphics();
		gb.fillRect(0, 0, i.getWidth(), i.getHeight());
		gb.drawImage(i, 0, 0, null);
		for(Sprite s: sprites){
			if(s.isVisible()){
				s.paint(gb);
			}
		}
		g.fillRect(0,0,width_pixels, height_pixels);
		g.translate(dx, dy);
		g.drawImage(b, 0, 0, null);
		g.translate(-dx,-dy);
	}

}
