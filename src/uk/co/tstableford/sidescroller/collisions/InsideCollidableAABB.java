/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.tstableford.sidescroller.collisions;

/**
 * An inside collidable, returns true if any part of the object is inside the rect
 * @author bpt
 */
public class InsideCollidableAABB implements Collidable {//extends CollidableAABB {
    public double x, y, w, h;
    @Override
	public boolean isColliding(Collidable c) {
            //return !super.isColliding(c);
            if (c instanceof CollidableAABB) {
                CollidableAABB aabb = (CollidableAABB)c;
                if (aabb.x<x) return true;
                if (aabb.y<y) return true;
                if (aabb.x+aabb.w>=x+w) return true;
                if (aabb.y+aabb.h>=y+h) return true;
                return false;
            } else if (c instanceof CollidableCircle) {
                CollidableCircle circle = (CollidableCircle)c;
                if (circle.x-circle.r<x) return true;
                if (circle.x+circle.r>=x+w) return true;
                if (circle.y-circle.r<y) return true;
                if (circle.x+circle.r>=y+h) return true;
                return false;
            }
            return false;
	}

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
