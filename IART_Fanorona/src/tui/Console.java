package tui;

import java.util.ArrayList;
import java.util.Scanner;

import logic.Board.PlayType;
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
			int typeInput;
			Scanner scan = new Scanner(System.in);
			game.getBoard().show();

			// TODO adicionar jogadas consecutivas
			// guardar direccao (n repeter jogadas consecutivas)
			// guardar posicoes (nao voltar atras numa jogada)
			// depois de jogar uma vez, tem de jogar aquela pe�a
			// "getMovesInPosition"
			// mudar condicao de paragem do ciclo de jogo (ate nao haver pecas
			// de uma cor)
			ArrayList<Play> ps = game.possiblePlays();

			for (int i=0; i < ps.size(); i++) {
				System.out.println(i + " - " + ps.get(i));
			}

			int input = scan.nextInt();

            /*
			if (possiblePlays.get(input).type == PlayType.BOTH) {
				System.out.println("1 - Withdrawal");
				System.out.println("2 - Approach");
				typeInput = scan.nextInt();

				if (typeInput == 1)
					possiblePlays.get(input).type = PlayType.WITHDRAWAL;
				else if (typeInput == 2)
					possiblePlays.get(input).type = PlayType.APPROACH;
			}
            */

            ArrayList<Move> play = ps.get(input).getMoves();

            for(int i=0; i <play.size(); i++){
                game.move(play.get(i));
            }

			game.switchTurn();

            int winner = game.getBoard().getWinner();
            if(winner != -1){
                if(winner == Game.WHITE)
                    System.out.println("White player won!");
                else if(winner == Game.BLACK)
                    System.out.println("Black player won!");

                break;
            }
		}
	}

	private ArrayList<Move> filterMoves(ArrayList<Move> possiblePlays) {
		ArrayList<Move> possiblePlaysAux = new ArrayList<Move>();

		for (Move move : possiblePlays) {
			if (move.type != PlayType.NONE)
				possiblePlaysAux.add(move);
		}
		if (possiblePlaysAux.size() != 0)
			possiblePlays = possiblePlaysAux;
		return possiblePlays;
	}

	public static void main(String[] args) {
		Console console = new Console();

		console.playGame();
	}

}
