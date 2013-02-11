/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d;

import java.util.ArrayList;

/**
 * Implementation of GameAI that just returns a random legal move
 * @author bpt
 */
public class RandomLegalMoveAI implements GameAI {
    @Override
    public GameState pickSuccessor(GameState state) {
        ArrayList<GameState> moves = state.getSuccesorStates();
        int i = (int)(Math.random()*moves.size());
        return moves.get(i);
    }


}
