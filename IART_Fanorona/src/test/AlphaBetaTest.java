package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import logic.AlphaBeta;
import logic.Board;
import logic.Board.PlayType;
import logic.Move;
import logic.Play;
import logic.Position;

import org.junit.Test;



public class AlphaBetaTest {

	@Test
	public void test_levels() {
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
	
	@Test
	public void test_fail_with_one_level() {
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

		Play result = (new AlphaBeta()).minimax((Board) board_obj.clone(), 1, 1); // only one level

		Play expectedPlay = new Play();
		Move m1 = new Move(new Position(1, 2), new Position(1, 1),
				PlayType.WITHDRAWAL);
		Move m2 = new Move(new Position(1, 1), new Position(0, 1),
				PlayType.WITHDRAWAL);
		expectedPlay.addMove(m1);
		expectedPlay.addMove(m2);

		assertFalse( expectedPlay.equals(result));
	}
	
	
	
	@Test
	public void test_block() {
		Board board_obj = new Board();
		int[][] board = board_obj.b;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = -1;
			}

		}

		board[1][4] = 1;
		board[4][3] = 0;
		board[4][5] = 0;

		Play result = (new AlphaBeta()).minimax((Board) board_obj.clone(), 3, 1); 

		Play expectedPlay = new Play();
		Move m1 = new Move(new Position(1, 4), new Position(2, 4),
				PlayType.NONE);
		expectedPlay.addMove(m1);

		assertEquals(expectedPlay, result);
	}
	
	@Test
	public void test_block_less_depth() {
		Board board_obj = new Board();
		int[][] board = board_obj.b;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = -1;
			}

		}

		board[1][4] = 1;
		board[4][3] = 0;
		board[4][5] = 0;

		Play result = (new AlphaBeta()).minimax((Board) board_obj.clone(), 2, 1); 

		Play expectedPlay = new Play();
		Move m1 = new Move(new Position(1, 4), new Position(2, 4),
				PlayType.NONE);
		expectedPlay.addMove(m1);

		assertFalse(expectedPlay.equals(result));
	}
}
