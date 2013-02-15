package uk.co.tstableford.sidescroller.interfaces;

import java.awt.Graphics;

public interface Item {
	public void update(long dT);
	public void paint(Graphics g);
}
