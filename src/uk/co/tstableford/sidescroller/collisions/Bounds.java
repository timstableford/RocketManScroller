package uk.co.tstableford.sidescroller.collisions;

import uk.co.tstableford.sidescroller.Vector2D;

public class Bounds {
	private Collidable object;
	public Bounds(Vector2D pos, float radius){
		//circle
	}
	public Bounds(Vector2D pos, Vector2D dimensions){
		//square
	}
	public Vector2D getPos(){
		return object.getPos();
	}
	
}
