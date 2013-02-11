package uk.co.tstableford.rocketman;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import gameengine2d.ActionGamePanel;
import gameengine2d.collisions.CollidableSprite;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Launch extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private CollidableSprite rocketMan;
	public Launch(String[] args){
		this.setSize(800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		ActionGamePanel agp = new ActionGamePanel(10);
		agp.play();
		rocketMan = getRocketMan();
		agp.addSprite(rocketMan);


		this.getContentPane().add(agp, BorderLayout.CENTER);
		this.setVisible(true);
	}
	private CollidableSprite getRocketMan(){
		CollidableSprite s = new CollidableSprite(100, 100, loadImage("/rocketman.png"), this, 0.5f);
		return s;
	}
	private BufferedImage loadImage(String path){
		try {
			return ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			return null;
		}
	}
	public static void main(String[] args) {
		new Launch(args);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		double vx,vy;
		vx = rocketMan.getVx();
		vy = rocketMan.getVy();
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: vy = -1; break;
			case KeyEvent.VK_DOWN: vy = 1; break;
			case KeyEvent.VK_LEFT: vx = -1; break;
			case KeyEvent.VK_RIGHT: vx = 1; break;
			default: break;
		}
		rocketMan.setVelocity(vx, vy);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		double vx,vy;
		vx = rocketMan.getVx();
		vy = rocketMan.getVy();
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: vy = 0; break;
			case KeyEvent.VK_DOWN: vy = 0; break;
			case KeyEvent.VK_LEFT: vx = 0; break;
			case KeyEvent.VK_RIGHT: vx = 0; break;
			default: break;
		}
		rocketMan.setVelocity(vx, vy);
	}
	@Override
	public void keyTyped(KeyEvent e) {}

}
