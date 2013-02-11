package gameengine2d;

/**
 * file: Sprite.java
 * An abstract class to handle sprites.
 *
 * Created by Fred Labrosse
 * Version: 12/09/2001
 */

import java.awt.*;

/**
 * An abstract class to handle sprites.
 */
public abstract class Sprite
{
   private boolean visible;   /**< Is the sprite visible? */
   private boolean active;    /**< Is the sprite active? */
   private double x, y;

   /**
    * Paint method.
    * @param g The graphics context where to draw.
    */
   public abstract void paint(Graphics g);

   /**
    * Update method.
    */
   public abstract void update();

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
   public void setLocation(double X, double Y) {
       x=X;
       y=Y;
   }

   /** Get the x-coord of the location
    *
    * @return returns the x-coord
    */
   public double getX() {
       return x;
   }

   /** Get the y-coord of the location
    *
    * @return returns the y-coord
    */
   public double getY(){
       return y;
   }
}
