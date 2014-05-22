package logic;

/**
 * Created by Francisco on 15/05/2014.
 */
public class AlphaBeta {

	public Play minimax(Board origin, int depth, int color) {
		Node n = new Node(null, (Board) origin.clone(), color);

		Node result = minimax(n, depth, Integer.MIN_VALUE, Integer.MAX_VALUE,
				true);

		System.out.println(result.resultingPlay);
		System.out.println(result.heuristicValue);
		return result.resultingPlay;
	}

	private Node minimax(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {
		if (depth == 0 || node.terminal()) {
			node.heuristicValue();
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

				if (returnMove.heuristicValue() > alpha) {
					alpha = returnMove.heuristicValue();
					bestMove = returnMove;
					bestChild = child;

				}

				if (beta <= alpha) {

					break;

				}
			}
			bestChild.isHeuristicSet = true;
			bestChild.heuristicValue = alpha;
			return bestChild;
		} else {
			for (Node child : node.getChilds()) {

				returnMove = minimax(child, depth - 1, alpha, beta, true);

				if (bestMove == null) {
					bestMove = returnMove;
					bestChild = child;
				} else if (bestMove.heuristicValue() > returnMove
						.heuristicValue()) {
					bestMove = returnMove;
					bestChild = child;
				}

				if (returnMove.heuristicValue() < beta) {
					beta = returnMove.heuristicValue();
					bestMove = returnMove;
					bestChild = child;
				}

				if (beta <= alpha) {
					bestChild.isHeuristicSet = true;
					bestChild.heuristicValue = beta;

					break;

				}
			}
			bestChild.isHeuristicSet = true;
			bestChild.heuristicValue = bestMove.heuristicValue;
			return bestChild;
		}

	}

	public static int min(int a, int b) {
		return Math.min(a, b);
	}

	public static int max(int a, int b) {
		return Math.max(a, b);
	}



}
