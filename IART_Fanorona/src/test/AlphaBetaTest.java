package test;

import static org.junit.Assert.assertEquals;
import logic.AlphaBeta;
import logic.Board;
import logic.Board.PlayType;
import logic.Move;
import logic.Play;
import logic.Position;

import org.junit.Test;



public class AlphaBetaTest {

	@Test
	public void test() {
		Board board_obj = new Board();
		int[][] board = board_obj.b;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = -1;
			}

		}

		board[1][2] = 1;
		board[1][3] = 0;
		board[1][4] = 0;
		board[1][5] = 0;
		board[1][6] = 0;
		board[2][1] = 0;
		board[3][2] = 0;
		board[4][2] = 0;
		board[3][6] = 1;
		board[4][4] = 1;

		Play result = (new AlphaBeta())
.minimax((Board) board_obj.clone(), 2, 1);

		Play expectedPlay = new Play();
		Move m1 = new Move(new Position(1, 2), new Position(1, 1),
				PlayType.WITHDRAWAL);
		Move m2 = new Move(new Position(1, 1), new Position(0, 1),
				PlayType.WITHDRAWAL);
		expectedPlay.addMove(m1);
		expectedPlay.addMove(m2);

		assertEquals(expectedPlay, result);
	}
}
