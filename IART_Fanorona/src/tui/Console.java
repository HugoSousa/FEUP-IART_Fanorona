package tui;

import java.util.ArrayList;
import java.util.Scanner;

import logic.AlphaBeta;
import logic.Board;
import logic.Game;
import logic.Move;
import logic.Play;

public class Console {

	private Game game;

	public Console() {
		game = new Game();
	}

	public void playGame() {
		while (true) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			game.getBoard().show();

			
			ArrayList<Play> ps = game.possiblePlays();

			for (int i = 0; i < ps.size(); i++) {
				System.out.println(i + " - " + ps.get(i));
			}
			System.out.println("minmax: ");

			(new AlphaBeta()).minimax((Board) game.getBoard().clone(), 5,
					game.getTurn());

			System.out.print("play: ");
			int input = scan.nextInt();

		

			ArrayList<Move> play = ps.get(input).getMoves();

			for (int i = 0; i < play.size(); i++) {
				game.move(play.get(i));
			}

			game.switchTurn();

			int winner = game.getBoard().getWinner();
			if (winner != -1) {
				if (winner == Game.WHITE)
					System.out.println("White player won!");
				else if (winner == Game.BLACK)
					System.out.println("Black player won!");

				break;
			}
		}
	}
/*
	public static void main(String[] args) {
		Console console = new Console();

		console.playGame();
	}
	*/
	

}
