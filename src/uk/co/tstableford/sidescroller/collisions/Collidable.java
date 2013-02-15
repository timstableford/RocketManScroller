/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.tstableford.sidescroller.collisions;

/**
 * Interface for collidable objects
 * @author bpt
 */
public interface Collidable {
    /** Check if this collidable is colliding with another collidable object
     *
     * @param c the object to check for collisions with
     * @return returns true if the objects are colliding
     */
    boolean isColliding(Collidable c);
    void setLocation(double x, double y);
}
