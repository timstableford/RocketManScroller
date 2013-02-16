package uk.co.tstableford.sidescroller.collisions;

public class Projection {
	private float min,max;
	public Projection(float min, float max){
		this.min = min;
		this.max = max;
	}
	public boolean overlap(Projection other){
		boolean r = other.min>=this.min&&other.min<=this.max
				||other.max<=this.max&&other.max>=this.min
				||this.min>=other.min&&this.min<=other.max
				||this.max<=other.max&&this.max>=other.min;
		return r;
	}
}
