/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d;

import gameengine2d.collisions.CollidableSprite;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Game panel designed for simple action games
 * @author bpt
 */
public class ActionGamePanel extends GamePanel {
	private static final long serialVersionUID = 1L;
	ArrayList<CollidableSprite> sprites = new ArrayList<CollidableSprite>();

    /** Constructor
     *
     * @param pause the inter frame pause
     */
    public ActionGamePanel(int pause) {
        super(pause);
    }

    @Override
    public void updateState() {
        try {
        for (CollidableSprite sprite: sprites) {
            if (sprite.isActive()) {
                sprite.update();
                sprite.processCollisions();
            }
        }
        } catch (java.util.ConcurrentModificationException ex) {

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
        for (CollidableSprite sprite: sprites) {
            if (sprite.isVisible()) sprite.paint(g);
            /*g.setColor(Color.red);
            CollidableAABB bounds = sprite.getBounds();
            g.drawRect((int)bounds.x, (int)bounds.y, (int)bounds.w, (int)bounds.h);
            */
             }
        } catch (java.util.ConcurrentModificationException ex) {

        }
    }

    @Override
    public void init() {
    }

    /** Add a sprite to the list of sprites managed by this panel
     *
     * @param sprite the new sprite to add
     */
    public void addSprite(CollidableSprite sprite) {
        sprites.add(sprite);
    }

     public void remove(CollidableSprite obj){
    	sprites.remove(obj);
    }
}
