package uk.co.tstableford.sidescroller;

/**
 * file: Sprite.java
 * An abstract class to handle sprites.
 *
 * Created by Fred Labrosse
 * Version: 12/09/2001
 */

import java.awt.*;

import uk.co.tstableford.sidescroller.interfaces.Item;

/**
 * An abstract class to handle sprites.
 */
public abstract class Sprite implements Item{
   private boolean visible;   /**< Is the sprite visible? */
   private boolean active;    /**< Is the sprite active? */
   protected Vector2D pos;

   public Sprite(Vector2D pos){
	   this.pos = pos;
	   this.visible  = false;
	   this.active = false;
   }
   /**
    * Paint method.
    * @param g The graphics context where to draw.
    */
   public abstract void paint(Graphics g);

   /**
    * Update method.
    */
   public abstract void update(long dT);

   public abstract int getWidth();

   public abstract int getHeight();

   /**
    * Returns the visibility status of the sprite.
    * @return true if the sprite is visible, false otherwise.
    */
   public boolean isVisible()
   {
      return visible;
   }

   /**
    * Sets the visibility status of the sprite.
    * @param v true if the sprite must be visible, false otherwise.
    */
   public void setVisible(boolean v)
   {
      visible = v;
   }

   /**
    * Returns the activity status of the sprite.
    * @return true if the sprite is visible, false otherwise.
    */
   public boolean isActive()
   {
      return active;
   }

   /**
    * Sets the activity status of the sprite.
    * @param a true is the sprite must be active, false otherwise.
    */
   public void setActive(boolean a)
   {
      active = a;
   }

   /**
    * Suspends (inactive and invisible) the sprite.
    */
   public void suspend()
   {
      setVisible(false);
      setActive(false);
   }

   /**
    * Restores (active and visible) the sprite.
    */
   public void restore()
   {
      setVisible(true);
      setActive(true);
   }

   /** Set the location of the sprite
    *
    * @param X x-coord
    * @param Y y-coord
    */
   public void setLocation(Vector2D pos) {
       this.pos = pos;
   }
   public Vector2D getPos(){
	   return this.pos;
   }
   /** Get the x-coord of the location
    *
    * @return returns the x-coord
    */
   public double getX() {
       return pos.getX();
   }

   /** Get the y-coord of the location
    *
    * @return returns the y-coord
    */
   public double getY(){
       return pos.getY();
   }
}
