package uk.co.tstableford.sidescroller;

public abstract class MassSprite extends Sprite{
	protected Vector2D velocity, acceleration;
	protected float mass;
	private Vector2D gravity;
	public MassSprite(Vector2D pos, Vector2D acceleration, Vector2D velocity, float mass){
		super(pos);
		this.acceleration = acceleration;
		this.velocity = velocity;
		this.mass = mass;
		this.gravity = null;
	}
	public void setGravity(Vector2D g){
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
		if(this.gravity!=null){
			applyAcceleration(this.gravity);
		}	
		//update velocity
		//v = u + at
		this.velocity = this.velocity.add(this.acceleration.scale(dT/1000f));
		//friction
		this.velocity = this.velocity.scale(0.995f);
		//update position
		//ds = v*t
		this.pos = this.pos.add(this.velocity.scale(dT/1000f));
		//set acceleration to 0, we're done here
		acceleration = new Vector2D();
	}
}
