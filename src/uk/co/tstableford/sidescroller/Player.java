package uk.co.tstableford.sidescroller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Player extends CollidableMassSprite implements KeyListener{
	private static final float UP_F = 3400;
	private static final float L_F = 4000;
	private static final float R_F = 4000;
	private Texture normal, fire;
	private int width, height;
	private float rotation;
	private HashMap<Character, Boolean> pressed;
	public Player(Vector2D pos, Texture normal, Texture fire, float scale) {
		super(pos, new Vector2D(), new Vector2D(), 70);
		this.height = (int) (normal.get().getHeight()*scale);
		this.width = (int) (normal.get().getHeight()*scale);
		this.normal = normal;
		this.fire = fire;
		this.pressed = new HashMap<Character, Boolean>();
		this.setVisible(true);
		this.setActive(true);
		this.setGravity(new Vector2D(0, 20));
	}
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	@Override
	public void paint(Graphics g) {
		Texture texture = normal;
		float r = (float) (rotation-Math.PI/2);
		float rx = (float) (velocity.unit().getX()*Math.PI/2);
		r = r + rx;
		if(isPressed('w')||isPressed('a')||isPressed('d')){
			texture = fire;
		}
		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(r, pos.getX(), pos.getY());
		g2.drawImage(texture.get(), 
				(int)(pos.getX()-this.width/2), 
				(int)(pos.getY()-this.height/2), 
				this.width, 
				this.height, null);
		g2.rotate(-r, pos.getX(), pos.getY());
	}
	@Override
	public void update(long dT){
		super.update(dT);
		if(isPressed('w')){
			this.applyForce(new Vector2D(0, -UP_F));
		}
		if(isPressed('a')){
			this.applyForce(new Vector2D(-L_F, 0));
		}
		if(isPressed('d')){
			this.applyForce(new Vector2D(R_F, 0));
		}
	}
	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_W: case KeyEvent.VK_UP:
			this.setPressed('w', true);
			break;
		case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
			this.setPressed('a', true);
			break;
		case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
			this.setPressed('d', true);
			break;
		default: break;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_W: case KeyEvent.VK_UP:
			this.setPressed('w', false);
			break;
		case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
			this.setPressed('a', false);
			break;
		case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
			this.setPressed('d', false);
			break;
		default: break;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	private void setPressed(char key, boolean pressed){
		if(this.pressed.containsKey(key)){
			this.pressed.remove(key);
		}
		this.pressed.put(key, pressed);
	}
	private boolean isPressed(char key){
		if(pressed.containsKey(key)&&pressed.get(key)){
			return true;
		}
		return false;
	}
}
