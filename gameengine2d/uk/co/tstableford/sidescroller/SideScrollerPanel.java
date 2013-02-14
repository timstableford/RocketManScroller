package uk.co.tstableford.sidescroller;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.tstableford.sidescroller.interfaces.Item;

public class SideScrollerPanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private long lastTick;
	private boolean run;
	private float fpsinv;
	private List<Item> items;
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.setSize(800,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new SideScrollerPanel(10), BorderLayout.CENTER);
		f.setVisible(true);
	}
	public SideScrollerPanel(int fps){
		items = new ArrayList<Item>();
		run = true;
		fpsinv = 1000f/(float)fps;
		(new Thread(this)).start();
	}
	public void addComponent(Item c){
		items.add(c);
	}
	private void updateItems(long dT){
		for(Item i: items){
			i.update(dT);
		}
	}
	@Override
	public void run() {
		lastTick = System.currentTimeMillis();
		while(run){
			long dT = System.currentTimeMillis()-lastTick;
			lastTick = System.currentTimeMillis();
			this.updateItems(dT);
			
			try {
				Thread.sleep((long) (fpsinv));
			} catch (InterruptedException e) {
				//insomniac thread
			}
		}
	}
	
}
