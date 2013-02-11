package uk.co.tstableford.rocketman;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;

import gameengine2d.ActionGamePanel;
import gameengine2d.collisions.CollidableSprite;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.JFrame;

public class Launch extends JFrame{
	private static final long serialVersionUID = 1L;
	public Launch(String[] args){
		this.setSize(800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionGamePanel agp = new ActionGamePanel(10);
		
		
		
		this.getContentPane().add(agp, BorderLayout.CENTER);
		this.setVisible(true);
	}
	private CollidableSprite getRocketMan(){
		
		CollidableSprite s = new CollidableSprite(100, 100, loadImage("/rocketman.png"), this);
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

}
