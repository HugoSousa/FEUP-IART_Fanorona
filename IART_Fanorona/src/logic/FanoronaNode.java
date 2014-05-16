package logic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Francisco on 16/05/2014.
 */
public class FanoronaNode implements Node {
    Board board;
    Movement play;
    @Override
    public Movement getPlay() {
        return null;
    }

    public FanoronaNode(Board b, Play p) {
        this.board = b;
        this.play = p;
    }
    @Override
    public boolean terminal() {
        return (board.getWinner() == -1);
    }

    @Override
    public int heuristicValue(int player) {
        int adv;
        if (player == Board.BLACK) adv = Board.WHITE;
        else adv = Board.BLACK;
        return 2 * ( board.countPieces(player) - board.countPieces(adv)); // + 0.5 * TODO
    }

    @Override
    public ArrayList<Node> getChilds(int player) {
    ArrayList<Node> list  = new ArrayList<Node>();

      for( Play p:  board.possiblePlays(player)) {

          Board b = new Board(Board.myBoardClone(board.getBoard()));

          b.movePlay(p);
            list.add(new FanoronaNode(b, p));
      }
        return list;
    }

    @Override
    public Node clone() {
        Play newPlay = null;
        if (play != null) {
            try {
                newPlay = (Play) ((Play) play).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        Board b = new Board(Board.myBoardClone(board.getBoard()));


        FanoronaNode n = new FanoronaNode(b,newPlay);
        return null;
    }
}
