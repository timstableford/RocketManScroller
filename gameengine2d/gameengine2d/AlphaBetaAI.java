/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine2d;

import java.util.ArrayList;

/**
 * Performs a basic minimax search with alpha-beta pruning
 * @author bpt
 */
public class AlphaBetaAI implements GameAI {
    int maxDepth = 5;


    /** Constructor, specifies the maximum depth to build the search tree
     *
     * @param maxDepth the maximum depth of the search tree
     */
    public AlphaBetaAI(int maxDepth) {
        this.maxDepth =  maxDepth;
    }

    /** Get the next state given the input state
     *
     * @param state the input state
     * @return returns the best output state
     */
      public GameState pickSuccessor(GameState state) {
          GameState[] bestChild={null};
          boolean[] win= {false};
        alphabeta(state, maxDepth, -100000, 100000, false, bestChild, win);
        return bestChild[0];
      }


      /** Performs a recursive minimax search using alpha-beta pruning
       *
       * @param node the current (input) game state
       * @param depth the maximum depth to search to
       * @param alpha the current alpha value
       * @param beta the current beta value
       * @param max indicates if i a min or max cycle
       * @param bestState place to return the best move to make
       * @param win place to return a value indicating a win
       * @return returns the  value of the returned state
       */
      public int alphabeta(GameState node, int depth, int alpha, int beta, boolean max, GameState[] bestState, boolean[] win) {
    if  (depth == 0 || node.checkWin()) {

        return node.getStateValue();
    }
    ArrayList<GameState> children = node.getSuccesorStates();
    GameState[] nextState={null};
    boolean[] nextWin= {false};
    int[] order = getRandomOrder(children.size());
    if (max) {
        for (int i=0; i<children.size(); i++) {
            GameState child = children.get(order[i]);
            int move = alphabeta(child, depth-1, alpha, beta, !max, nextState, nextWin);
            //if (alpha==null) alpha = move;
            if (move>alpha) {
                alpha = move;
                bestState[0] = child;
            }
            if (nextWin[0]) break;
            if (beta<=alpha) break;                             // Beta cut-off
        }
        return alpha;
    } else {
         for (int i=0; i<children.size(); i++) {
            GameState child = children.get(order[i]);
            int move = alphabeta(child, depth-1, alpha, beta, !max, nextState, nextWin);
            if (move<beta) {
                beta = move;
                bestState[0] = child;
            }

            if (nextWin[0]) break;
            if (beta<=alpha) break; // Alpha cut-off
         }
        return beta;
    }
}



    int[] getRandomOrder(int n) {
        int[] order = new int[n];
        for (int i=0; i<n; i++) order[i]=i;
            for (int i=0; i<n; i++) {
                int i1 = (int)(n*Math.random());
                int tmp = order[i];
                order[i] = order[i1];
                order[i1] = tmp;
            }
        return order;
    }

    /* Some basic tests
    public static void main(String[] args) {
        
        class TreeState implements GameState {
            public ArrayList<TreeState> children = new ArrayList<TreeState>();
            public int player, cost;
            public boolean win=false;

           

            public String toString() {
                return "player = " + player + ", cost = " +cost + ", children = " + children.size();
            }

           

            public boolean checkWin() {
                return win;
            }

            public int getWinner() {
                return 0;
            }

           

            public ArrayList<GameState> getSuccesorStates() {
                 ArrayList<GameState> moves = new ArrayList<GameState>();
                for (int i=0; i<children.size(); i++) {
                    TreeState c = children.get(i);

                    moves.add(c);
                }
                return moves;
            }

            public int getStateValue() {
                return cost;
            }

            public boolean checkLegalMove(GameState nextState) {
                return true; // not used in test
            }

        }
        TreeState[] nodes = new TreeState[31];//13];
        for (int i=0; i<nodes.length; i++) nodes[i] = new TreeState();

        nodes[0].player=1;
        for (int i=0; i<3; i++) {
            nodes[0].children.add(nodes[1+i]);
            nodes[1+i].player=2;
        }


        int[] costs = {2, 1, 3, 5, 14, 13, 8, 4, 12, 5, 1, 3, 6, 2, 3, 4, 2, 1};// {2, 1, 3, 5, 14, 13, 6, 2, 3, 4, 2, 1, 8, 4, 12, 5, 1, 3};//{3, 12, 8, 2, 4, 6, 14, 5, 2};
        //{3, 1, 5, 12, 4, 8, 1, 2, 4, 3, 2, 6, 13, 14, 5, 3, 1, 2};

        for (int i=0; i<3; i++) {
            nodes[1].children.add(nodes[4+i]);
          //  nodes[4+i].cost=costs[i];
            nodes[4+i].player=1;
            for (int j=0; j<2; j++) {
                nodes[4+i].children.add(nodes[13+i*2+j]);
                nodes[13+i*2+j].cost = costs[i*2+j];
                nodes[13+i*2+j].player=2;
            }
        }
        for (int i=0; i<3; i++) {
            nodes[2].children.add(nodes[7+i]);
            //nodes[7+i].cost=costs[i+3];
            nodes[7+i].player=1;
            for (int j=0; j<2; j++) {
                nodes[7+i].children.add(nodes[19+i*2+j]);
                nodes[19+i*2+j].cost = costs[6+i*2+j];
                nodes[19+i*2+j].player=2;
            }
        }
        for (int i=0; i<3; i++) {
            nodes[3].children.add(nodes[10+i]);
            //nodes[10+i].cost=costs[i+6];
            nodes[10+i].player=1;
            for (int j=0; j<2; j++) {
                nodes[10+i].children.add(nodes[25+i*2+j]);
                nodes[25+i*2+j].cost = costs[12+i*2+j];
                nodes[25+i*2+j].player=2;
            }
        }

        AlphaBetaAI ai = new AlphaBetaAI(3);
        GameState nextState = ai.pickSuccessor(nodes[0]);
        System.out.println("Best Move(final) = " + nextState);
    }
*/

}
