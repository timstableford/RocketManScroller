package uk.co.tstableford.sidescroller.collisions.bounds;

import uk.co.tstableford.sidescroller.Sprite;
import uk.co.tstableford.sidescroller.Vector2D;
import uk.co.tstableford.sidescroller.collisions.Collidable;
import uk.co.tstableford.sidescroller.collisions.Projection;

public class Polygon implements Collidable{
	private Vector2D[] points;
	private Sprite owner;
	public Polygon(Sprite owner, Vector2D[] points){
		this.points = points;
		this.owner = owner;
	}
	public Projection project(Vector2D axis, Vector2D vertice, Vector2D[] vertices){
		float min = axis.dot(vertice);
		float max = min;
		for(int i=0; i<vertices.length; i++){
			if(vertice!=vertices[i]){
				float p = axis.dot(vertices[i]);
				if(p<min){
					min = p;
				}else if(p>max){
					max = p;
				}
			}
		}
		return new Projection(min, max);
	}
	private Vector2D[] getVertices(Vector2D[] points){
		Vector2D[] v = new Vector2D[points.length];
		for(int i=0; i<points.length; i++){
			Vector2D a = points[i].add(this.owner.getPos());
			Vector2D b;
			if(i+2>points.length){
				b = points[0];
			}else{
				b = points[i+1];
			}
			b = b.add(this.owner.getPos());
			v[i] = b.subtract(a);
		}
		return v;
	}
	private Vector2D[] getNormals(Vector2D[] vertices){
		Vector2D[] r = new Vector2D[vertices.length];
		for(int i=0; i<vertices.length; i++){
			r[i] = vertices[i].normal();
		}
		return r;
	}
	@Override
	public Vector2D isColliding(Collidable c) {
		switch(c.getType()){
		case Polygon:
			Polygon o = (Polygon)c;
			//generate our norms
			Vector2D[] verts1 = getVertices(this.points);
			Vector2D[] norms1 = getNormals(verts1);
			//generate other norms
			Vector2D[] verts2 = o.getVertices(o.points);
			Vector2D[] norms2 = o.getNormals(verts2);
			for(int i=0; i<norms1.length; i++) {
				Vector2D axis = norms1[i];
				// project both shapes onto the axis
				Projection p1 = this.project(axis, verts1[i], verts1);
				Projection p2 = o.project(axis, verts2[i], verts2);
				// do the projections overlap?
				if (!p1.overlap(p2)) {
					// then we can guarantee that the shapes do not overlap
					return null;
				}
			}
			// loop over the axes2
			for (int i=0; i<norms2.length; i++) {
				Vector2D axis = norms2[i];
				// project both shapes onto the axis
				Projection p1 = this.project(axis, verts1[i], verts1);
				Projection p2 = o.project(axis, verts2[i], verts2);
				// do the projections overlap?
				if (!p1.overlap(p2)) {
					// then we can guarantee that the shapes do not overlap
					return null;
				}
			}
			break;
		default: break;
		}
		return new Vector2D(1,1);
	}

	@Override
	public Vector2D getPos() {
		return this.owner.getPos();
	}

	@Override
	public void setPos(Vector2D p) {
		this.owner.setLocation(p);
	}

	@Override
	public Object getOwner() {
		return owner;
	}
	@Override
	public BoundsType getType() {
		return BoundsType.Polygon;
	}

}
