/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d;

import java.util.LinkedList;

/**
 * Simple implementation of GamePanel for two player turn based games (e.g. noughts and crosses or connect four)
 *
 * @author bpt
 */
public abstract class TwoPlayerTurnGame extends GamePanel {

    LinkedList<GameState> stack = new LinkedList<GameState>();
     GameAI opponent;
     GameState currentState;

     /** Sets the GameAI to use
      *
      * @param ai the GameAI to use
      */
     public void setOpponent(GameAI ai) {
         this.opponent = ai;
     }

     /** Tries to make a move, checks if it is legal
      *
      * @param move the move to make
      * @return returns true if the move was made successfully or false otherwise
      */
     public boolean makeMove(GameState move) {
         if (currentState.checkLegalMove(move)) {
             //GameState nextState = currentState.applyMove(move);
             stack.push(currentState);
             currentState = move;
             return true;
         }
         return false;
     }

     /** Get the current GameState
      *
      * @return returns the current game state
      */
     public GameState getCurrentState() {
         return currentState;
     }

     /** Sets the current game state
      *
      * @param state the new game state
      */
     public void setCurrentState(GameState state) {
         currentState = state;
     }

     /** Undo a move
      *
      */
     public void undoMove() {
         currentState = stack.pop();
     }

     /** pick a move from the GameAI
      *
      * @return returns the selected opponent move
      */
     public GameState pickMove() {
         return opponent.pickSuccessor(currentState);

     }
}
