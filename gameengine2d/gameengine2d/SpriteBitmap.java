package gameengine2d;

/**
 * file: SpriteBitmap.java
 * A class to handle sprites whose appearance is a bitmap.
 *
 * Created by Fred Labrosse
 * Version: 27/09/2002
 */

import java.awt.*;

/**
 * A class to handle sprites whose appearance is a bitmap.
 */
public class SpriteBitmap extends Sprite
{
   private Image image;       /**< The bitmap. */
   private Component applet;               /**< The parent applet (needed to
                                   draw the image). */

   /**
    * Constructor.
    * @param x The initial X position.
    * @param y The initial Y position.
    * @param i An image that will make the bitmap's appearance.
    * @param a The parent applet.
    */
   public SpriteBitmap(int x, int y, Image i, Component a)
   {
      this.setLocation(x, y);
      image = i;
      applet = a;
      restore();
   }

   /**
    * Update method.
    *
    * It does nothing in this class.
    */
   public void update()
   {
      // Does nothing.
   }
   
   /**
    * Paint method.
    * @param g The graphics context where to draw.
    */
   public void paint(Graphics g)
   {
      if (isVisible())
      {
         g.drawImage(image, (int)getX(), (int)getY(), applet);
      }
   }

    @Override
    public int getWidth() {
        return image.getWidth(applet);
    }

    @Override
    public int getHeight() {
        return image.getHeight(applet);
    }
}
