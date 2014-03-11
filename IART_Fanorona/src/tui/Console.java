package tui;

import java.util.ArrayList;
import java.util.Scanner;

import logic.Board.PlayType;
import logic.Game;
import logic.Move;

public class Console {

	private Game game;
	
	public Console() {
		game = new Game();
	}

	public void playGame(){
		while(true){
			int typeInput;
			Scanner scan = new Scanner(System.in);
			game.getBoard().show();
			
			
			// TODO adicionar jogadas consecutivas
			// guardar direccao (n repeter jogadas consecutivas)
			// guardar posicoes (nao voltar atras numa jogada)
			// depois de jogar uma vez, tem de jogar aquela peça "getMovesInPosition"
			//mudar condicao de paragem do ciclo de jogo (ate nao haver pecas de uma cor)
			
			ArrayList<Move> possiblePlays = game.possibleMoves();
			possiblePlays = filterMoves(possiblePlays);
			
			for(int i=0; i < possiblePlays.size(); i++){
				Move m = possiblePlays.get(i);
				System.out.println(i + " - " + m);		
			}
			
			int input = scan.nextInt();
			
			if(possiblePlays.get(input).type == PlayType.BOTH){
				System.out.println("1 - Withdrawal");
				System.out.println("2 - Approach");
				typeInput = scan.nextInt();
				
				if(typeInput == 1)
					possiblePlays.get(input).type = PlayType.WITHDRAWAL;
				else if(typeInput == 2)
					possiblePlays.get(input).type = PlayType.APPROACH;
			}
			
			
				
			game.move(possiblePlays.get(input));
			game.switchTurn();
		}
	}

	private ArrayList<Move> filterMoves(ArrayList<Move> possiblePlays) {
		ArrayList<Move> possiblePlaysAux = new ArrayList<Move>();
		
		for(Move move: possiblePlays){
			if(move.type != PlayType.NONE)
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
