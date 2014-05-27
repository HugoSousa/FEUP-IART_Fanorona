package logic;

/**
 * Created by Francisco on 15/05/2014.
 */
public class AlphaBeta {
	 static boolean PRUNNING = true;

	/*
	public Play minimax2(Board origin, int depth, int color) {
		Node n = new Node(null, (Board) origin.clone(), color);

		Node result = minimax2(n, depth, Integer.MIN_VALUE, Integer.MAX_VALUE,
				true);

		System.out.println(result.resultingPlay);
		System.out.println(result.heuristicValue);
		return result.resultingPlay;
	}

	private Node minimax2(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {
		if (depth == 0 || node.terminal()) {
			node.heuristicValue();
			return node;
		}

		Node returnMove, bestMove = null;
		Node bestChild = null;
		if (maximizingPlayer) {
			for (Node child : node.getChilds()) {

				returnMove = minimax2(child, depth - 1, alpha, beta, false);

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

				returnMove = minimax2(child, depth - 1, alpha, beta, true);

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
			bestChild.heuristicValue = beta;
			return bestChild;
		}

	}
	*/
	

	private Node minimax(Node node, int depth, int alpha, int beta, boolean maximizingPlayer, int maincolor) {
		if (depth == 0 || node.terminal(maincolor)) {
			node.heuristicValue(maincolor);
			return node;
		}

		Node returnMove;
		Node bestNode = null;
		if (maximizingPlayer) {
			if (!PRUNNING) alpha = -9999;
			for (Node child : node.getChilds()) {

				returnMove = minimax(child, depth - 1, alpha, beta, false, maincolor);

				if (returnMove.heuristicValue(maincolor) > alpha) {
					alpha = returnMove.heuristicValue(maincolor);
					bestNode = child.clone();
				}

				if (beta <= alpha && PRUNNING)
					break;

			}
			if (bestNode == null && PRUNNING) {
				bestNode = new Node(new Play(), new Board(), -1000);
			}
			bestNode.isHeuristicSet = true;
			bestNode.heuristicValue = alpha;
			return bestNode;
		} else {
			if (!PRUNNING ) beta = 9999;

			for (Node child : node.getChilds()) {

				returnMove = minimax(child, depth - 1, alpha, beta, true, maincolor);

				if (returnMove.heuristicValue(maincolor) < beta) {
					beta = returnMove.heuristicValue(maincolor);
					bestNode = child.clone();
				}

				if (beta <= alpha && PRUNNING)
					break;
			}
			if (bestNode == null && PRUNNING) {
				bestNode = new Node(new Play(), new Board(), -1000);
			}
			bestNode.isHeuristicSet = true;
			bestNode.heuristicValue = beta;
			return bestNode;
		}

	}

	
	


	public Play minimax(Board origin, int depth, int color) {
		Node n = new Node(null, (Board) origin.clone(), color);
		Node result = minimax(n, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true, color);

		System.out.println(result.resultingPlay);
		System.out.println(result.heuristicValue);
		return result.resultingPlay;
	}

	public static int min(int a, int b) {
		return Math.min(a, b);
	}

	public static int max(int a, int b) {
		return Math.max(a, b);
	}

}
