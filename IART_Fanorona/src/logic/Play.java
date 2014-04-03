package logic;

import java.util.ArrayList;

import logic.Board.PlayType;

public class Play implements Cloneable {

	ArrayList<Move> moves;

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}

	public Play() {
		moves = new ArrayList<Move>();
	}

	public Play(Play oldPlay) {
		moves = new ArrayList<Move>();

		for (Move m : oldPlay.getMoves()) {
			try {
				moves.add((Move) m.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean addMove(Move m) {
		// TODO ultima direcao e posicoes alcancadas if verificiacoes
		if (moves.size() < 10)
			return moves.add(m);
		else
			return false;
	}

	public Move getLastMove() {
		if (moves.size() > 0)
			return moves.get(moves.size() - 1);
		else
			return null;
	}

	public boolean over() {
		return getLastMove().type == PlayType.NONE;
	}

	public String toString() {
		String returnm = "";
		for (Move m : moves)
			returnm += m.toString();
		return returnm;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		super.clone();
		Play p = new Play();
		for (Move m : moves) {
			p.moves.add((Move) m.clone());
		}
		return p;
	}
}
