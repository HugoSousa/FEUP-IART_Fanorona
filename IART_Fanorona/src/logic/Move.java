package logic;

import logic.Board.PlayType;

public class Move {
	public Position pInit;
	public Position pFinal;
	public PlayType type;
	
	public Move(Position pinit, Position pfinal, PlayType type){
		this.pInit = pinit;
		this.pFinal = pfinal;
		this.type= type;
	}
	
	public String toString(){
		return pInit.toString() + " / " + pFinal.toString() + " - " + type;
	}
}
