/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d;

/**
 * Interface for game AI
 * @author bpt
 */
public interface GameAI {
    /** Pick the next best move from the current state
     *
     * @param state the current state
     * @return returns the next move (according to this GameAI)
     */
    public GameState pickSuccessor(GameState state);
}
