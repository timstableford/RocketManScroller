package uk.co.tstableford.sidescroller;
import java.awt.Graphics;
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
	private int height_pixels, width_pixels, desired_width_pixels;
	private Sprite focus;
	public World(int desired_width_pixels, int width_pixels, int height_pixels, HashMap<String, Texture> textures){
		this.textures = textures;
		terrain = new Terrain(desired_width_pixels/TER_SIZE, height_pixels, MAX_TER_HEIGHT, TER_SIZE);
		sprites = new ArrayList<Sprite>();
		this.height_pixels = height_pixels;
		this.width_pixels = width_pixels;
		this.desired_width_pixels = desired_width_pixels;
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
		//make sure window never goes too low
		if(dy<focus.getHeight()/2-TER_SIZE*2){
			dy = focus.getHeight()/2-TER_SIZE*2;
		}
		//make sure not too far left
		if(dx>0){
			dx = 0;
		}
		//make sure not too far right
		//TODO this is off by a few pixels
		if(dx<-(desired_width_pixels-width_pixels+focus.getWidth()/2)){
			dx = -(desired_width_pixels-width_pixels+focus.getWidth()/2);
		}
		System.out.println(focus.getPos());
		g.fillRect(0,0,desired_width_pixels,height_pixels);
		g.translate(dx, dy);
		g.drawImage(terrain.getTerrain(textures.get("top"), textures.get("ground")),0,0,null);
		for(Sprite s: sprites){
			if(s.isVisible()){
				s.paint(g);
			}
		}
		g.translate(-dx,-dy);
	}
}
