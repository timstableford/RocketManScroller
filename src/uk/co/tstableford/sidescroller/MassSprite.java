package uk.co.tstableford.sidescroller;

public abstract class MassSprite extends Sprite{
	public static final Vector2D g = new Vector2D(0f, (float) (2*9.8));
	protected Vector2D velocity, acceleration;
	protected float mass;
	private boolean gravity;
	public MassSprite(Vector2D pos, Vector2D acceleration, Vector2D velocity, float mass){
		super(pos);
		this.acceleration = acceleration;
		this.velocity = velocity;
		this.mass = mass;
		this.gravity = false;
	}
	public void setGravity(boolean g){
		this.gravity = g;
	}
	public Vector2D getVelocity(){
		return velocity;
	}
	public void applyForce(Vector2D force){
		//f = ma ==> f/m = a
		this.applyAcceleration(force.scale(1f/mass));
	}
	public void applyAcceleration(Vector2D acceleration){
		this.acceleration = this.acceleration.add(acceleration);
	}
	public void setAcceleration(Vector2D acceleration){
		this.acceleration = acceleration;
	}
	public void setVelocity(Vector2D velocity){
		this.velocity = velocity;
	}
	@Override
	public void update(long dT) {
		//if gravity apply it
		if(this.gravity){
			applyAcceleration(g);
		}
		
		//update velocity
		//v = u + at
		this.velocity = this.velocity.add(this.acceleration.scale(dT/1000f));
		//update position
		//ds = v*t
		this.pos = this.pos.add(this.velocity.scale(dT/1000f));
		//set acceleration to 0, we're done here
		acceleration = new Vector2D();
	}
}
