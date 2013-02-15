/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.tstableford.sidescroller.collisions;

/**
 * A collidable axis aligned bounding box (AABB)
 * @author bpt
 */
public class CollidableAABB implements Collidable {
	/** The x coord of the AABB */
	public double x;
	/** The y coord of the AABB */
	public double y;
	/** The width of the AABB */
	public double w;
	/** The height of the AABB */
	public double h;

	@Override
	public boolean isColliding(Collidable c) {
		if (c instanceof CollidableCircle) {
			// No need to repeat ourselves
			return c.isColliding(this);
		} else if (c instanceof CollidableAABB) {
			CollidableAABB aabb = (CollidableAABB)c;
			if (x>aabb.x+aabb.w) return false;
			if (aabb.x>x+w) return false;
			if (y>aabb.y+aabb.h) return false;
			if (aabb.y>y+h) return false;
			return true;
		}  else if (c instanceof InsideCollidableAABB) {
			return c.isColliding(this);
		}
		return false;

	}

	@Override
	public String toString() {
		return "x = " + x + ", y = " + y + ", w = " + w + ", h = " + h;
	}

	public void setLocation(double x, double y) {
		this.x=x; this.y=y;
	}

}
