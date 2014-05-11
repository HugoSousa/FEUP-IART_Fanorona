package gui;

import java.util.ArrayList;

import logic.Board.PlayType;

public class PlayMove {
	
	private int finalRow;
	private int finalColumn;
	private ArrayList<PlayType> types;
	
	public PlayMove(int finalRow, int finalColumn) {
		types = new ArrayList<PlayType>();
		
		this.finalRow = finalRow;
		this.finalColumn = finalColumn;
	}
	
	public void addType(PlayType type){
		types.add(type);
	}

	public int getRow() {
		return finalRow;
	}

	public int getColumn() {
		return finalColumn;
	}
	
	public ArrayList<PlayType> getTypes(){
		return types;
	}
	
	public boolean equals(Object p2){
		return this.finalRow == ((PlayMove)p2).getRow() && this.finalColumn == ((PlayMove)p2).getColumn();
	}
}
