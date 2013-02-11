/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d.collisions;

/**
 * A collidable circle
 * @author bpt
 */
public class CollidableCircle implements Collidable {
        /** the x-coord of the centre */
	public double x;
	/** the y-coord of the centre */
	public double y;
	/** the radius */
	public double r;

        @Override
        public boolean isColliding(Collidable c) {
		if (c instanceof CollidableCircle) {
			CollidableCircle circ=(CollidableCircle)c;
			double R = Math.sqrt((circ.x-x)*(circ.x-x)
				+(circ.y-y)*(circ.y-y));
			if (R<circ.r+r) return true;
			return false;
		} else if (c instanceof CollidableAABB) {
			CollidableAABB aabb = (CollidableAABB)c;
			// Collapse problem on to one quadrant
			double X = Math.abs(x-aabb.x-aabb.w/2);
			double Y = Math.abs(y-aabb.y-aabb.h/2);
			// Check if AABBs don't intersect
			if (X > (aabb.w/2 + r)) return false;
			if (Y > (aabb.h/2 + r)) return false;
			// Check if AABBs intersect along an edge
  			if (X <= (aabb.w/2)) return true;
  			if (Y <= (aabb.h/2)) return true;
  			// Check for intersection at corners
  			double dx = X - aabb.w/2;
  			double dy = Y - aabb.h/2;
  			double cornerDistance_sq = dx*dx + dy*dy;
  			return (cornerDistance_sq <= r*r);
		} else if (c instanceof InsideCollidableAABB) {
                    return c.isColliding(this);
                }
                return false;
	}

    public void setLocation(double x, double y) {
        this.x = x+r;
        this.y = y+r;
    }
}
