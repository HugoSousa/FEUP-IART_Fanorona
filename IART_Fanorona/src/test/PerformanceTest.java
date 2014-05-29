package test;

import java.util.Random;

import logic.AlphaBeta;
import logic.Board;
import logic.Game;
import logic.Node;
import logic.Play;

import org.junit.Test;



public class PerformanceTest {

	@Test
	public void test_depth() {
		
		
	    Random random = new Random();

		
		int games = 3;
		int player_one_wins = 0;
		int player_two_wins = 0;
		int draws = 0;
		int player_one_plays = 0;
		int player_two_plays = 0;
		long player_one_total_time= 0;
		long player_two_total_time= 0;
		long start_time;
		
		for (int j= 0; j < games; j++) {
			Game game = new Game();
			int i = 0;
			int winner = Board.EMPTY;
			int player = random.nextInt(1 - 0 + 1);
			
			int player_level, opponent_level;
			int max_level = random.nextInt(6 - 5 + 1) + 5;
			int min_level = random.nextInt(2 - 1 + 1) + 1;

			if (random.nextInt(1 - 0 + 1) == 1) {
				player_level = max_level;
				opponent_level = min_level;
			}
			else {
				player_level = min_level;
				opponent_level = max_level;
			}
			
			if (player == 1) game.switchTurn();
			while (i < 100) {
	
				int level;
				if (game.getTurn() == player) level = player_level;
				else level = opponent_level;
				
				if (level > 4) player_one_plays++;
				else player_two_plays++;
				
				 start_time = System.currentTimeMillis();
				Play next = (new AlphaBeta()).minimax((Board) game.getBoard().clone(), level, game.getTurn());
				game.getBoard().movePlay(next);
				
				if (level > 4) player_one_total_time += System.currentTimeMillis() - start_time;
				else player_two_total_time += System.currentTimeMillis() - start_time;
				
				
				game.switchTurn();
				
				if (game.getBoard().countPieces(1) == 0 || game.getBoard().countPieces(0) == 0) {
					winner = 1 - game.getTurn();
					break;
				}
				i++;
			}
			if (winner == -1) {
				draws++;
			}
			else if (player_level > opponent_level) {
				if (player == winner) player_one_wins++;
				else player_two_wins++;
			}
			else {
				if (player == winner) player_two_wins++;
				else player_one_wins++;
			}
			
		}
		System.out.println("Games: " + games);
		System.out.println("Draws: " + (double) (draws /(double) games ) * 100 + "%");
		System.out.println("Player A wins(depth: [5,6]): " + (double)(player_one_wins / (double)games ) * 100 + " %");
		System.out.println("Player A average play time: "+ (double)(player_one_total_time / (double)player_one_plays ) +" ms");
		System.out.println("Player B wins(depth: [1,2]): " + (double) (player_two_wins / (double)games ) * 100 + " %");
		System.out.println("Player B average play time: "+ (double)(player_two_total_time / (double)player_two_plays ) +" ms");


	}
	
	@Test
	public void test_heuristic() {
		
		
	    Random random = new Random();

		
		int games = 3;
		int player_one_wins = 0;
		int player_two_wins = 0;
		int draws = 0;
		int player_one_plays = 0;
		int player_two_plays = 0;
		long player_one_total_time= 0;
		long player_two_total_time= 0;
		long start_time;
		
		for (int j= 0; j < games; j++) {
			Game game = new Game();
			int i = 0;
			int winner = Board.EMPTY;
			int player = random.nextInt(1 - 0 + 1);
			
			int player_level, opponent_level;
			int max_level = 6;
			int min_level = 6;

			if (random.nextInt(1 - 0 + 1) == 1) {
				player_level = max_level;
				opponent_level = min_level;
			}
			else {
				player_level = min_level;
				opponent_level = max_level;
			}
			
			if (player == 1) game.switchTurn();
			while (i < 100) {
	
				int level;
				if (game.getTurn() == player) level = player_level;
				else level = opponent_level;
				
				if (game.getTurn() == player) player_one_plays++;
				else {
					Node.COMPLEX_HEURISTIC = true;
					player_two_plays++;
				}
				
				 start_time = System.currentTimeMillis();
				Play next = (new AlphaBeta()).minimax((Board) game.getBoard().clone(), level, game.getTurn());
				game.getBoard().movePlay(next);
				
				if (Node.COMPLEX_HEURISTIC) Node.COMPLEX_HEURISTIC = false;
				
				if (game.getTurn() == player) player_one_total_time += System.currentTimeMillis() - start_time;
				else player_two_total_time += System.currentTimeMillis() - start_time;
				
				
				game.switchTurn();
				
				if (game.getBoard().countPieces(1) == 0 || game.getBoard().countPieces(0) == 0) {
					winner = 1 - game.getTurn();
					break;
				}
				i++;
			}
			if (winner == -1) {
				draws++;
			}
			else  {
				if (player == winner) player_one_wins++;
				else player_two_wins++;
			}
			
			
		}
		System.out.println("Games: " + games);
		System.out.println("Draws: " + (double) (draws /(double) games ) * 100 + "%");
		System.out.println("Player A wins(depth: 6): " + (double)(player_one_wins / (double)games ) * 100 + " %");
		System.out.println("Player A average play time: "+ (double)(player_one_total_time / (double)player_one_plays ) +" ms");
		System.out.println("Player B wins(depth: 6; Complex Heuristic): " + (double) (player_two_wins / (double)games ) * 100 + " %");
		System.out.println("Player B average play time: "+ (double)(player_two_total_time / (double)player_two_plays ) +" ms");


	}
	
	@Test
	public void test_computer_vs_computer() {
		
		
	    Random random = new Random();

		
		int games = 3;
		int player_one_wins = 0;
		int player_two_wins = 0;
		int draws = 0;
		int player_one_plays = 0;
		int player_two_plays = 0;
		long player_one_total_time= 0;
		long player_two_total_time= 0;
		long start_time;
		
		for (int j= 0; j < games; j++) {
			Game game = new Game();
			int i = 0;
			int winner = Board.EMPTY;
			int player = random.nextInt(1 - 0 + 1);
			
			int player_level, opponent_level;
			int max_level = 6;
			int min_level = 6;

			if (random.nextInt(1 - 0 + 1) == 1) {
				player_level = max_level;
				opponent_level = min_level;
			}
			else {
				player_level = min_level;
				opponent_level = max_level;
			}
			
			if (player == 1) game.switchTurn();
			while (i < 100) {
	
				int level;
				if (game.getTurn() == player) level = player_level;
				else level = opponent_level;
				
				if (game.getTurn() == player) player_one_plays++;
				else player_two_plays++;
				
				 start_time = System.currentTimeMillis();
				Play next = (new AlphaBeta()).minimax((Board) game.getBoard().clone(), level, game.getTurn());
				game.getBoard().movePlay(next);
				
				if (game.getTurn() == player) player_one_total_time += System.currentTimeMillis() - start_time;
				else player_two_total_time += System.currentTimeMillis() - start_time;
				
				
				game.switchTurn();
				
				if (game.getBoard().countPieces(1) == 0 || game.getBoard().countPieces(0) == 0) {
					winner = 1 - game.getTurn();
					break;
				}
				i++;
			}
			if (winner == -1) {
				draws++;
			}
			else  {
				if (player == winner) player_one_wins++;
				else player_two_wins++;
			}
			
			
		}
		System.out.println("Games: " + games);
		System.out.println("Draws: " + (double) (draws /(double) games ) * 100 + "%");
		System.out.println("Player A wins(depth: 6): " + (double)(player_one_wins / (double)games ) * 100 + " %");
		System.out.println("Player A average play time: "+ (double)(player_one_total_time / (double)player_one_plays ) +" ms");
		System.out.println("Player B wins(depth: 6): " + (double) (player_two_wins / (double)games ) * 100 + " %");
		System.out.println("Player B average play time: "+ (double)(player_two_total_time / (double)player_two_plays ) +" ms");


	}
	
	
}
