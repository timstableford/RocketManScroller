package uk.co.tstableford.sidescroller.collisions;

import java.awt.Graphics;

import uk.co.tstableford.sidescroller.Sprite;
import uk.co.tstableford.sidescroller.Vector2D;
import uk.co.tstableford.sidescroller.collisions.bounds.Square;

public class TestBounds {

	public TestBounds(){
		Sprite sa,sb;
		sa = new T(new Vector2D(10,10));
		sb = new T(new Vector2D(20,20));
		Square a,b;
		a = new Square(sa, new Vector2D(20,20));
		b = new Square(sb, new Vector2D(20,20));
		System.out.println(a.isColliding(b));
		b.setPos(new Vector2D (40,40));
		System.out.println(a.isColliding(b));
	}
	public static void main(String[] args) {
		new TestBounds();
	}
	class T extends Sprite{
		public T(Vector2D pos) {
			super(pos);
		}

		@Override
		public void paint(Graphics g) {}

		@Override
		public void update(long dT) {}
		@Override
		public int getWidth() {
			return 10;
		}

		@Override
		public int getHeight() {
			return 10;
		}
		
	}

}
