package uk.co.tstableford.sidescroller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import uk.co.tstableford.sidescroller.interfaces.Item;

public class SideScrollerPanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private long lastTick;
	private boolean run;
	private float fpsinv;
	private List<Item> items;
	public SideScrollerPanel(int fps){
		items = new ArrayList<Item>();
		run = true;
		fpsinv = 1000f/(float)fps;
		(new Thread(this)).start();
	}
	public void addItem(Item c){
		items.add(c);
	}
	public void stop(){
		this.run = false;
	}
	private void updateItems(long dT){
		for(Item i: items){
			i.update(dT);
		}
	}
	@Override
	public void paint(Graphics g){
		for(Item i: items){
			i.paint(g);
		}
	}
	@Override
	public void run() {
		lastTick = System.currentTimeMillis();
		while(run){
			long dT = System.currentTimeMillis()-lastTick;
			lastTick = System.currentTimeMillis();
			this.updateItems(dT);
			this.repaint();
			long wait = (long) (2*fpsinv-dT);
			if(wait<0){
				wait = 0;
			}
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				//insomniac thread
			}
		}
	}
	
}
