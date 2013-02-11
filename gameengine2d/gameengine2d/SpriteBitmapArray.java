package gameengine2d;

/**
 * file: SpriteBitmapArray.java
 * A class to handle sprites whose appearance is a selectable bitmap from an
 * array of bitmaps.
 *
 * Created by Fred Labrosse, adapted by bpt
 * Version: 01/02/2011
 */

import java.awt.*;
import java.util.Random;

/**
 * A class to handle sprites whose appearance is a selectable bitmap from
 * an array of bitmaps.
 */
public class SpriteBitmapArray extends Sprite
{
	private float scale = 1;
	private Image images[];    // The bitmaps.
	private int currentImage;  // The image currently displayed.
	private Component component;               // The parent component (needed to draw the image).

	/**
	 * No animation constant
	 */
	public static final int STATIC=0;
	/**
	 * Loop through all images in order animation constant
	 */
	public static final int LOOP=1;
	/**
	 * Pick a random image animation constant
	 */
	public static final int RANDOM=2;

	/** Loop through a defined subsequence constant.  Set the sequence to loop through with setSequence
	 */
	public static final int SEQUENCE=3;

	int mode = STATIC;
	private Random randNb = new Random(); /**< A random number generator. */
	int[] sequence = null;
	/**
	 * Constructor.
	 * @param x The initial X position.
	 * @param y The initial Y position.
	 * @param i An array of images that will make the loop.
	 * @param a The parent component.
	 */
	public SpriteBitmapArray(int x, int y, Image i[], Component a)
	{
		images = i;
		currentImage = 0;
		component = a;
		setLocation(x,y);
		restore();
	}
	public SpriteBitmapArray(int x, int y, Image i[], Component a, float scale)
	{
		this.scale = scale;
		images = i;
		currentImage = 0;
		component = a;
		setLocation(x,y);
		restore();
	}

	/**
	 * Sets the current image that makes the appearance of the sprite.
	 *
	 * @param which The index of the bitmap in the array of bitmaps.  Limits
	 * are checked.
	 * @return true if the operation succeded, false otherwise.
	 **/
	public boolean setCurrentImage(int which)
	{
		boolean success = true;

		if ((which < images.length) && (which >= 0))
			currentImage = which;
		else
			success = false;

		return(success);
	}

	public int getImageCount() {
		return images.length;
	}

	/** et the mode to one of STATIC, LOOP, RANDOM or SEQUENCE
	 *
	 * @param mode the new mode to use
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}

	/** Set the sequence of frames to cycle through
	 *  will automatically set the mode to SEQUENCE
	 * @param seq the frame numbers to cycle through
	 */
	public void setSequence(int[] seq) {
		this.sequence = seq;
		mode = SEQUENCE;
	}

	/**
	 * Gets the index or sequence number of the image current being displayed
	 * @return
	 */
	public int getCurrentImage() {
		return currentImage;
	}
	/**
	 * Update method. Progresses the frame animation depending on the mode.
	 *
	 */
	public void update()
	{
		// if (mode==STATIC) return;
		if (mode==LOOP) currentImage =
				(currentImage + 1) % images.length;
		else if (mode==RANDOM) currentImage =
				randNb.nextInt(images.length);
		else if (mode==SEQUENCE) currentImage =
				(currentImage + 1) % sequence.length;
	}

	/**
	 * Paint method.
	 * @param g The graphics context where to draw.
	 */
	public void paint(Graphics g)
	{
		if (isVisible())
		{
			if (mode==SEQUENCE) {
				g.drawImage(images[sequence[currentImage]], (int)getX(), (int)getY(),
						(int)getWidth(), 
						(int)getHeight(),
						component);
			} else if (images.length > 0) {
				g.drawImage(images[currentImage], (int)getX(), (int)getY(), 
						(int)getWidth(), 
						(int)getHeight(), 
						component);
			}
		}
	}

	@Override
	public int getWidth() {
		return (int)((float)images[currentImage].getWidth(component)*scale);
	}

	@Override
	public int getHeight() {
		return (int)((float)images[currentImage].getHeight(component)*scale);
	}
}
