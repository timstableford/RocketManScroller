package uk.co.tstableford.sidescroller;

public abstract class CollidableMassSprite extends MassSprite{

	public CollidableMassSprite(Vector2D pos, Vector2D acceleration,
			Vector2D velocity, float mass) {
		super(pos, acceleration, velocity, mass);
	}
}
