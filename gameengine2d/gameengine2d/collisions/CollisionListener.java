/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d.collisions;

/**
 * Interface collision listeners should use
 * @author bpt
 */
public interface CollisionListener {
    /** Fired when a new collision occurs
     *
     * @param hitObject the object that's been jit
     */
    public void onCollisionEnter(CollidableSprite hitObject);

    /** Fired when a collision ends
     *
     * @param hitObject the object that's been hit
     */
    public void onCollisionExit(CollidableSprite hitObject);

    /** Fired when a collision continues
     *
     * @param hitObject the object that's been hit
     */
    public void onCollisionMoved(CollidableSprite hitObject);

    /** Get the collidable that's listening
     *
     * @return the collidable object associated with this listener
     */
    public CollidableSprite getCollidable();
}
