package uk.co.tstableford.sidescroller.collisions.bounds;

import uk.co.tstableford.sidescroller.Sprite;
import uk.co.tstableford.sidescroller.Vector2D;

public class Square extends Polygon{
	public Square(Sprite owner, Vector2D dimensions){
		super(owner, getPoints(owner, dimensions));
	}
	private static Vector2D[] getPoints(Sprite owner, Vector2D dimensions){
		Vector2D[] points = new Vector2D[4];
		//top left
		points[0] = owner.getPos().subtract(dimensions.scale(0.5f));
		//top right
		points[1] = new Vector2D(owner.getPos().getX()+dimensions.getX(),
				owner.getPos().getY()-dimensions.getY());
		//bottom right
		points[2] = owner.getPos().add(dimensions.scale(0.5f));
		//bottom left
		points[3] = new Vector2D(owner.getPos().getX()-dimensions.getX(),
				owner.getPos().getY()+dimensions.getY());
		return points;
	}
}
