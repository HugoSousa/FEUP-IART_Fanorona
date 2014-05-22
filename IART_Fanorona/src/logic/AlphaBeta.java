package logic;

import java.util.ArrayList;

/**
 * Created by Francisco on 15/05/2014.
 */
public class AlphaBeta {

	public Play minimax(Board origin, int depth, int color) {
		Node n = new Node(null, (Board) origin.clone(), color);

		Node result = minimax(n, depth, Integer.MIN_VALUE, Integer.MAX_VALUE,
				true);

		System.out.println(result.resultingPlay);
		return result.resultingPlay;
	}

	private Node minimax(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {
		if (depth == 0 || node.terminal()) {
			return node;
		}

		Node returnMove, bestMove = null;
		Node bestChild = null;
		if (maximizingPlayer) {
			for (Node child : node.getChilds()) {

				returnMove = minimax(child, depth - 1, alpha, beta, false);

				if (bestMove == null) {
					bestMove = returnMove;
					bestChild = child;
				} else if (bestMove.heuristicValue() < returnMove
						.heuristicValue()) {
					bestMove = returnMove;
					bestChild = child;
				}

				alpha = max(alpha, returnMove.heuristicValue());
				if (beta <= alpha) {
					break;

				}
			}
			bestChild.heuristicValue = alpha;
			return bestChild;
		} else {
			for (Node child : node.getChilds()) {

				returnMove = minimax(child, depth - 1, alpha, beta, true);

				if (bestMove == null) {
					bestMove = returnMove;
					bestChild = child;
				} else if (bestMove.heuristicValue() < returnMove
						.heuristicValue()) {
					bestMove = returnMove;
					bestChild = child;
				}

				beta = min(beta, returnMove.heuristicValue());
				if (beta < alpha) {

					break;

				}
			}

			bestChild.heuristicValue = beta;

			return bestChild;
		}

	}

	public static int min(int a, int b) {
		return Math.min(a, b);
	}

	public static int max(int a, int b) {
		return Math.max(a, b);
	}

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
				heuristicValue = 2 * (board.countPieces(color) - board
						.countPieces(1 - color)); //
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
				newPlay = (resultingPlay == null ? null : (Play) resultingPlay
						.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}

			return new Node(newPlay, (Board) board.clone(), color);
		}
	}

}
