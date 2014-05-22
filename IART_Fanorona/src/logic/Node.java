package logic;

import java.util.ArrayList;

public class Node {
	public Play resultingPlay;
	public Board board;
	public int color;
	int heuristicValue = 0;
	boolean isHeuristicSet = false;

	public Node(Play resultingPlay, Board b, int color) {
		board = (Board) b.clone();
		this.color = color;
		this.resultingPlay = resultingPlay;
		isHeuristicSet = false;
	}

	public boolean terminal() {

		return (board.possiblePlays(color).size() == 0);
	}

	public int heuristicValue() {
		if (!isHeuristicSet) {
			isHeuristicSet = true;
			heuristicValue = 2 * (board.countPieces(color) - board.countPieces(1 - color));// +
																							// 0.5
																							// *
																							// (board.getAvailableMoves(color)
																							// -
																							// board.getAvailableMoves(1
																							// -
																							// color)));
																							// //
																							// TODO
																							// heuristicvalue
		}
		return heuristicValue;
	}

	public ArrayList<Node> getChilds() {
		ArrayList<Play> plays = board.possiblePlays(color);
		ArrayList<Node> nodes = new ArrayList<Node>();

		for (Play p : plays) {
			Board temp = (Board) board.clone();
			temp.movePlay(p);
			Node n = new Node(p, temp, 1 - color);
			nodes.add(n);
		}
		return nodes;
	}

	public Node clone() {
		Play newPlay = null;
		try {
			newPlay = (resultingPlay == null ? null : (Play) resultingPlay.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return new Node(newPlay, (Board) board.clone(), color);
	}
}