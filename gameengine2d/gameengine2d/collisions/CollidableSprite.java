/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d.collisions;

import gameengine2d.SpriteBitmapArray;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * A collidable bitmap array sprite
 * @author bpt
 */
public class CollidableSprite extends SpriteBitmapArray {
	//private CollidableAABB bounds;
	private Collidable bounds;
	private double ax, ay, vx, vy, x, y, offsetx=0, offsety=0;
	private ArrayList<CollisionListener> collisionListeners = new ArrayList<CollisionListener>();
	private ArrayList<Boolean> prevCollision = new ArrayList<Boolean>();

	/** Constructor takes a location, a set of images and a component 
	 * 
	 * @param x x-coord
	 * @param y y-coord
	 * @param imgs the array of images to display
	 * @param comp the component used for drawing
	 */
	public CollidableSprite(int x, int y, Image[] imgs, Component comp) {
		super(x,y,imgs, comp);
		CollidableAABB bounds = new CollidableAABB();
		bounds.x = x; bounds.y = y;
		bounds.w = getWidth();
		bounds.h = getHeight();
		this.bounds = bounds;
		this.x = x; this.y = y;
	}


	/** Constructor takes a location, a set of images and a component
	 *
	 * @param x x-coord
	 * @param y y-coord
	 * @param img the array of images to display
	 * @param comp the component used for drawing
	 */
	public CollidableSprite(int x, int y, Image img, Component comp) {
		super(x,y,new Image[]{img}, comp);
		CollidableAABB bounds = new CollidableAABB();
		bounds.x = x; bounds.y = y;
		bounds.w = getWidth();
		bounds.h = getHeight();
		this.bounds = bounds;
		this.x = x; this.y = y;
	}
	/**
	 * Add a sprite listening for collisions with this
	 * @param listener the listener to add
	 * @return returns true if the iterm added OK
	 */
	public boolean addCollisionListener(CollisionListener listener) {
		prevCollision.add(false);
		return collisionListeners.add(listener);
	}

	/** Removes the specified collision listener from the list
	 * 
	 * @param listener the listener to remove
	 * @return returns true if the listener was removed OK
	 */
	public boolean removeCollisionListener(CollisionListener listener) {
		int index = collisionListeners.indexOf(listener);
		if (index<0) return false;
		prevCollision.remove(index);
		collisionListeners.remove(index);
		return true;
	}

	/** Empties the list of collision listeners
	 * 
	 */
	public void removeAllCollisionListeners() {
		collisionListeners.clear();
		prevCollision.clear();
	}

	@Override
	public void setLocation(double x, double y) {
		super.setLocation(x, y);
		if (bounds!=null) {
			//bounds.x = x+offsetx;
			//bounds.y = y+offsety;
			bounds.setLocation(x+offsetx, y+offsety);
		}
		this.x=x;
		this.y=y;
	}

	/** Set the offset of the bounds for this object,
	 * allows the bitmap and collision bounds to be different
	 * @param ox the x-offset
	 * @param oy the y-offset
	 */
	public void setBoundsOffset(double ox, double oy) {
		offsetx = ox;
		offsety = oy;
	}

	/** Get the collision bounds for this sprite
	 * 
	 * @return return the collision bounds
	 */
	public Collidable getBounds() {
		return bounds;
	}

	/** Set the collision bounds for this sprite
	 * 
	 * @param bounds set the collision bounds for this sprite
	 */
	public void setBounds(Collidable bounds) {
		this.bounds = bounds;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/*  if (debug){
                g.setColor(Color.red);
                g.drawRect((int)(bounds.x), (int)(bounds.y), (int)(bounds.w), (int)(bounds.h));
            }
		 */
	}

	@Override
	public void update() {
		super.update();
		vx+=ax;
		vy+=ay;
		x+=vx;
		y+=vy;
		setLocation((int)Math.round(x), (int)Math.round(y));
	}

	/** Set the sprite acceleration
	 * 
	 * @param ax the x-acceleration
	 * @param ay the y-acceleration
	 */
	public void setAcceleration(double ax, double ay) {
		this.ax = ax; this.ay=ay;
	}

	/** Set the velocity
	 *
	 * @param vx x-velocity
	 * @param vy y-velocity
	 */
	public void setVelocity(double vx, double vy) {
		this.vx = vx; this.vy=vy;
	}

	/** Get the x velocity
	 * 
	 * @return the x-velocity
	 */
	public double getVx() {return vx;}

	/** Get the y velocity
	 * 
	 * @return the y-velocity
	 */
	public double getVy() {return vy;}

	/** Get the x acceleration
	 * 
	 * @return the x acceleration
	 */
	public double getAx() {return ax;}

	/** Get the y acceleration
	 * 
	 * @return the y acceleration
	 */
	public double getAy() {return ay;}

	boolean debug = false;

	/** Turn debug mode on or off (draws collision bounds)
	 * 
	 * @param b the new debug setting
	 */
	public void setDebug(boolean b) {
		debug = b;
	}

	/** Process collisions i.e. check for collisions with all listeners and call
	 * the enter, exit of moved collision method as appropriate
	 */
	public void processCollisions() {
		int i=0;
		for (CollisionListener listener : collisionListeners) {

			CollidableSprite sprite = listener.getCollidable();
			if (!sprite.isActive() || !sprite.isVisible()) continue;
			boolean collide = sprite.getBounds().isColliding(bounds);
			if (collide) {
				if (!prevCollision.get(i)) listener.onCollisionEnter(this);
				else listener.onCollisionMoved(this);
			} else {
				if (prevCollision.get(i)) listener.onCollisionExit(this);
			}
			prevCollision.set(i, collide);
			i++;
		}
	}

	/** The north direction. */
	public static final int N = 0;
	/** The north-east direction. */
	public static final int NE = 1;  
	/** The east direction. */
	public static final int E = 2;   
	/** The south-east direction. */
	public static final int SE = 3;   
	/** The south direction. */
	public static final int S = 4;    
	/** The south-west direction. */
	public static final int SW = 5;  
	/** The west direction. */
	public static final int W = 6;   
	/** The north-west direction. */
	public static final int NW = 7;   
	/** The center direction (i.e. at the same place). */
	public static final int C = 8;    


	/**
	 * Where am I relative to you?
	 * @param c2 the collidable sprite representing you
	 * @return The direction constant (N, NE, NW, S, SE, SW, E, W, C)
	 */
	public int whereAreYou(CollidableSprite c2)
	{
		int xC = (int)getX() + getWidth() / 2;
		int yC = (int)getY() + getHeight() / 2;

		if (xC/* c1.getX()+c1.getWidth()*/ < c2.getX())
		{
			//  return W;
			if (yC < c2.getY())
			{
				return NW;
			}
			else if ((yC >= c2.getY()) && (yC <= c2.getY()+c2.getHeight()))
			{
				return W;
			}
			else if (yC > c2.getY()+c2.getHeight())
			{
				return SW;
			}

		}
		else if ((xC /*c1.getX()+c1.getWidth()*/ >= c2.getX()) && (xC /*c1.getX()*/ <= c2.getX()+c2.getWidth()))
		{
			//      return E;
			if (yC < c2.getY())
			{
				return N;
			}
			else if ((yC >= c2.getY()) && (yC <= c2.getY()+c2.getHeight()))
			{
				return C;
			}
			else if (yC > c2.getY()+c2.getHeight())
			{
				return S;
			}

		}
		else if (xC /*c1.getX()*/ >= c2.getX()+c2.getWidth())
		{
			//          return E;
			if (yC < c2.getY())
			{
				return NE;
			}
			else if ((yC >= c2.getY()) && (yC <= c2.getY()+c2.getHeight()))
			{
				return E;
			}
			else if (yC > c2.getY()+c2.getHeight())
			{
				return SE;
			}

		}
		return -1;
	}

	/** Attempts to reverse the sprite when an overlap occurs
	 * to the closest point along the velocity vector before collision occured
	 * @param c2 sprite that I'm in collision with
	 * @return returns the length of the backwards step
	 */
	public double backUp(CollidableSprite c2) {
		double a0=-1, a1=-1, a2=-1, a3=-1;
		double A0=0, A1=0, A2=0, A3=0;

		if (getVx()!=0) {
			A0 = (getX()-c2.getX()-c2.getWidth())/getVx(); a0 = Math.abs(A0);
			A1 = (getX()+getWidth()-c2.getX())/getVx(); a1 = Math.abs(A1);
		}
		if (getVy()!=0) {
			A2=(getY()-c2.getY()-c2.getHeight())/getVy(); a2 = Math.abs(A2);
			A3 = (getY()+getHeight()-c2.getY())/getVy(); a3 = Math.abs(A3);
		}
		double res = 0;
		if (a0>=0 && (a1<0 || a0<a1) && (a2<0 || a0<a2) && (a3<0 || a0<a3)) res = A0;
		else if (a1>=0 && (a2<0 || a1<a2) && (a3<0 || a1<a3)) res = A1;
		else if (a2>=0 && (a3<0 || a2<a3)) res = A2;
		else if (a3>=0) res = A3;
		if (Math.abs(res)>10) {
			System.out.println("res = " + res);
		}
		setLocation((int)Math.round(getX()-res*getVx()), (int)Math.round(getY() - res*getVy()));

		return res;
	}

}
