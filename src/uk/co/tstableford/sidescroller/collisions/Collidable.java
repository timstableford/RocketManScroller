package uk.co.tstableford.sidescroller.collisions;

import uk.co.tstableford.sidescroller.Vector2D;
import uk.co.tstableford.sidescroller.collisions.bounds.BoundsType;

public interface Collidable {
    public Vector2D isColliding(Collidable c);
    public Vector2D getPos();
    public void setPos(Vector2D p);
    public Object getOwner();
    public BoundsType getType();
}
