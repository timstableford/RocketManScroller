package uk.co.tstableford.rocketman;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;

import uk.co.tstableford.sidescroller.Player;
import uk.co.tstableford.sidescroller.SideScrollerPanel;
import uk.co.tstableford.sidescroller.Texture;
import uk.co.tstableford.sidescroller.Vector2D;
import uk.co.tstableford.sidescroller.World;

public class Launch extends JFrame{
	private static final long serialVersionUID = 1L;
	private SideScrollerPanel scroller;
	private Player player;
	private HashMap<String, Texture> textures;
	public static void main(String[] args){
		new Launch();
	}
	public Launch(){
		this.setSize(800,600);
		this.textures = this.loadTextures();
		scroller = new SideScrollerPanel(30);
		World w = new World(1000, this.getHeight(), this.textures);
		scroller.addItem(w);
		player = new Player(new Vector2D(100,100), textures.get("rocketman"), textures.get("rocketfire"), 0.2f);
		this.addKeyListener(player);
		w.addSprite(player);
		
		this.add(scroller, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	private HashMap<String, Texture> loadTextures(){
		HashMap<String, Texture> t = new HashMap<String, Texture>();
		t.put("ground", new Texture("/tile_ground.png"));
		t.put("top", new Texture("/grasstile.png"));
		t.put("rocketman", new Texture("/rocketman.png"));
		t.put("rocketfire", new Texture("/rocketmanfire.png"));
		return t;
	}

}
