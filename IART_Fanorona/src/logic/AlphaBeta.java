package logic;

import java.util.Collection;

/**
 * Created by Francisco on 15/05/2014.
 */
public class AlphaBeta {

    public  Movement minimax(Node origin, int depth) {

        return minimax(origin, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }


    private  Movement minimax(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {
            if (depth == 0 || node.terminal()){
                return (new Movement(node.heuristicValue(), null));
            }

        Movement returnMove, bestMove = null;

        if (maximizingPlayer){
            for (Node child: node.getChilds()){

                 returnMove = minimax(child, depth - 1, alpha, beta, false);

                if (bestMove == null) {
                    bestMove = returnMove;
                    bestMove.moveNode = child;
                } else if (bestMove.returnValue  < returnMove.returnValue){
                    bestMove = returnMove;
                    bestMove.moveNode = child;
                }


                alpha = max(alpha, returnMove.returnValue);
                if (beta < alpha){
                    bestMove.returnValue = alpha; // TODO nao sei se e alpha ou beta aqui
                    bestMove.moveNode = null;

                    break;

                }
            }
            return bestMove;
        }
        else {
            for (Node child: node.getChilds()){

                 returnMove = minimax(child, depth - 1, alpha, beta, true);

                if (bestMove == null) {
                    bestMove = returnMove;
                    bestMove.moveNode = child;
                } else if (bestMove.returnValue  < returnMove.returnValue){
                    bestMove = returnMove;  // TODO nao sei se e alpha ou beta aqui
                    bestMove.moveNode = child;
                }


                beta = min(beta, returnMove.returnValue);
                if (beta < alpha){
                    bestMove.returnValue = beta; // ?
                    bestMove.moveNode = null;

                    break;

                }
            }
            return bestMove;
        }


    }


    public static int min(int a, int b) {
       return Math.min(a,b);
    }


    public static int max(int a, int b) {
        return Math.max(a,b);
    }

    public interface Node {
        public boolean terminal();
        public int heuristicValue();
        public Collection<Node> getChilds();
        public Node clone();
    }

    public class Movement  {
        int returnValue;
        Node moveNode;
        public  Movement(int value, Node moveNode ){
            this.returnValue = value;
            this.moveNode = moveNode.clone();
        }
        public  boolean heuristicValue() {return false;}
        public  Collection<Node> getChilds() {return null;}

    }
}
