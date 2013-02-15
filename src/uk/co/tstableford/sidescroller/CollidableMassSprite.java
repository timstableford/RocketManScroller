package uk.co.tstableford.sidescroller;

import java.util.ArrayList;

import uk.co.tstableford.sidescroller.collisions.Collidable;
import uk.co.tstableford.sidescroller.collisions.CollidableAABB;
import uk.co.tstableford.sidescroller.collisions.CollisionListener;

public abstract class CollidableMassSprite extends MassSprite{
	private Collidable bounds;
	private ArrayList<CollisionListener> collisionListeners = new ArrayList<CollisionListener>();
	private ArrayList<Boolean> prevCollision = new ArrayList<Boolean>();
	public CollidableMassSprite(Vector2D pos, Vector2D acceleration,
			Vector2D velocity, float mass) {
		super(pos, acceleration, velocity, mass);
		CollidableAABB bounds = new CollidableAABB();
		bounds.x = this.pos.getX(); bounds.y = this.pos.getY();
		bounds.w = getWidth();
		bounds.h = getHeight();
		this.bounds = bounds;
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
	public void processCollisions() {
		int i=0;
		for (CollisionListener listener : collisionListeners) {

			CollidableMassSprite sprite = listener.getCollidable();
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
	public int whereAreYou(CollidableMassSprite c2){
		int xC = (int)getX() + getWidth() / 2;
		int yC = (int)getY() + getHeight() / 2;
		if (xC < c2.getX()){
			if (yC < c2.getY()){
				return NW;
			}else if ((yC >= c2.getY()) && (yC <= c2.getY()+c2.getHeight())){
				return W;
			}else if (yC > c2.getY()+c2.getHeight()){
				return SW;
			}
		}else if ((xC >= c2.getX()) && (xC <= c2.getX()+c2.getWidth())){
			if (yC < c2.getY()){
				return N;
			}else if ((yC >= c2.getY()) && (yC <= c2.getY()+c2.getHeight())){
				return C;
			}else if (yC > c2.getY()+c2.getHeight()){
				return S;
			}
		}else if (xC >= c2.getX()+c2.getWidth()){
			if (yC < c2.getY()){
				return NE;
			}else if ((yC >= c2.getY()) && (yC <= c2.getY()+c2.getHeight())){
				return E;
			}else if (yC > c2.getY()+c2.getHeight()){
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
	public double backUp(CollidableMassSprite c2) {
		double a0=-1, a1=-1, a2=-1, a3=-1;
		double A0=0, A1=0, A2=0, A3=0;
		if (this.velocity.getX()!=0) {
			A0 = (this.pos.getX()-c2.pos.getX()-c2.getWidth())/this.velocity.getX(); a0 = Math.abs(A0);
			A1 = (this.pos.getX()+getWidth()-c2.pos.getX())/this.velocity.getX(); a1 = Math.abs(A1);
		}
		if (this.velocity.getY()!=0) {
			A2=(this.pos.getY()-c2.pos.getY()-c2.getHeight())/this.velocity.getY(); a2 = Math.abs(A2);
			A3 = (this.pos.getY()+getHeight()-c2.pos.getY())/this.velocity.getY(); a3 = Math.abs(A3);
		}
		double res = 0;
		if (a0>=0 && (a1<0 || a0<a1) && (a2<0 || a0<a2) && (a3<0 || a0<a3)) res = A0;
		else if (a1>=0 && (a2<0 || a1<a2) && (a3<0 || a1<a3)) res = A1;
		else if (a2>=0 && (a3<0 || a2<a3)) res = A2;
		else if (a3>=0) res = A3;
		if (Math.abs(res)>10) {
			System.out.println("res = " + res);
		}
		this.pos = new Vector2D((int)Math.round(this.pos.getX()-res*this.velocity.getX()), (int)Math.round(this.pos.getY() - res*this.velocity.getY()));

		return res;
	}
}
