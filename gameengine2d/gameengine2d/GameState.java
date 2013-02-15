/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d;

import java.util.ArrayList;

/**
 * Records the state of a game for use with a GameAI
 * @author bpt
 */
public interface GameState {
    /** Return all the legal next moves from this state
     *
     * @return returns a list of the legal next moves
     */
    public ArrayList<GameState> getSuccesorStates();

    /** Returns a value for the state (e.g. 1 for win, -1 for loss otherwise 0)
     *
     * @return  returns the state's value
     */
    public int getStateValue();

    /** Checks if this is a winning state
     *
     * @return
     */
    public boolean checkWin();

    /**
     * Checks if nextState can follow from this state
     * @param nextState the query next state
     * @return returns true if nextState is a legal next state
     */
    public boolean checkLegalMove(GameState nextState);
}
