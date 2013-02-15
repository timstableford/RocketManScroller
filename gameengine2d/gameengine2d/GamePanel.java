/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d;

import javax.swing.JPanel;

/**
 * A basic container for games
 * @author bpt
 */
public abstract class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private int pause=1;
    private boolean running = false;

    /** No args constructor, frame pause is set to 1
     *
     */
    public GamePanel(){}

    /** Constructor
     *
     * @param pause the inter frame pause to use
     */
    public GamePanel(int pause) {
        this.pause = pause;
    }

    /** Called before paint to update the game state
     */
    public abstract void updateState();

    /** Called once to initialise*/
    public abstract void init();

    /** Starts animation loop
     *
     */
    public void play() {
        init();
        Thread t = new Thread(this);
        t.start();
    }

    /** Stops the animation loop
     *
     */
    public void stop() {
        running=false;
    }

    /** Restart the animation loop
     *
     */
    public void restart() {
        running = false;
        play();
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            updateState();
            repaint();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
            }
        }
    }

    

}
