package uk.co.tstableford.sidescroller;

import java.util.ArrayList;

import uk.co.tstableford.sidescroller.collisions.CollisionListener;

public abstract class CollidableMassSprite extends MassSprite{
	private ArrayList<CollisionListener> collisionListeners = new ArrayList<CollisionListener>();
	public CollidableMassSprite(Vector2D pos, Vector2D acceleration,
			Vector2D velocity, float mass) {
		super(pos, acceleration, velocity, mass);
	}
	public void addCollisionListener(CollisionListener l){
		collisionListeners.add(l);
	}
}
