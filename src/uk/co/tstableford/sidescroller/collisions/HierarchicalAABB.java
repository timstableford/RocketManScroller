/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.tstableford.sidescroller.collisions;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A Hierarchical AABB example
 * @author bpt
 */
public class HierarchicalAABB implements Collidable {
	/** The x coord of the AABB */
	public double x;
         /** The y coord of the AABB */
	public double y;
         /** The width of the AABB */
	public double w;
         /** The height of the AABB */
	public double h;
        /**The children of this HAABB*/
        public ArrayList<HierarchicalAABB> children;

        /** Draw the HAABB
         *
         * @param g the graphics to draw too
         */
        public void draw(Graphics g) {
        g.drawRect((int)(x), (int)(y), (int)(w), (int)(h));
        if (children!=null) {
            for (HierarchicalAABB a: children) {
                a.draw(g);
            }
        }
    }

        /** Move this HAABB
         *
         * @param dx the x-shift
         * @param dy the y-shift
         */
        public void move(double dx, double dy) {
        x+=dx; y+=dy;
        if (children!=null) {
            for (HierarchicalAABB a: children) {
                a.move(dx, dy);
            }
        }
    }

        /** Check if it is colliding (with another HAABB)
         *
         * @param c the target collidable object
         * @return returns true if colliding, false if not colliding or doesn't know
         */
	public boolean isColliding(Collidable c) {
		if (c instanceof HierarchicalAABB) {
			HierarchicalAABB aabb =
				(HierarchicalAABB)c;
			if (x>aabb.x+aabb.w)
                            return false;
			if (aabb.x>x+w)
                            return false;
			if (y>aabb.y+aabb.h)
                            return false;
			if (aabb.y>y+h)
                            return false;
			if (children==null || children.isEmpty())
				return true;
			for (HierarchicalAABB child: children) {
				if (aabb.isColliding(child))
					return true;
			}
			return false;
		}
                return false;
        }

    public void setLocation(double x, double y) {
        for (HierarchicalAABB child : children) {
            double X = child.x-this.x;
            double Y = child.x-this.x;
            child.setLocation(x+X, y+Y);
        }
        this.x = x;
        this.y = y;
    }


}