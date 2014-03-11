package logic;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	public static final int EMPTY = -1;
	public static final int BLACK = 1;
	public static final int WHITE = 0;
	public static final int ERROR = -2;

	private static final int ROWS = 5;
	private static final int COLUMNS =  9;

	public enum PlayType {NONE, WITHDRAWAL, APPROACH, BOTH};

	public int [][]b;


	public Board() {
		b = new int[ROWS][COLUMNS];
		reset();
	}

	public int get(Position pos) {
		return get(pos.x, pos.y);
	}

	public int get(int x, int y) {
		if (x < 0 || y < 0 || 
				x > ROWS -1 || y > COLUMNS -1) return ERROR;
		else return b[x][y];

	}

	public void reset() {
		// Default rows and columns value dependant
		Arrays.fill(b[0], BLACK);
		Arrays.fill(b[1], BLACK);
		Arrays.fill(b[3], WHITE);
		Arrays.fill(b[4], WHITE);

		for (int i = 0; i < COLUMNS; i++) {

			if (i < 5) 
			{
				if (i % 2 == 0) b[2][i] = BLACK;
				else b[2][i] = WHITE;
			}			
			else 
			{
				if (i % 2 == 0) b[2][i] = WHITE;
				else b[2][i] = BLACK;
			}
		}
		b[2][4] = EMPTY;
	}
	public int set(Position pos, int value) {
		return set(pos.x,pos.y, value);
	}

	public int set(int x, int y, int value) {
		if (x < 0 || y < 0 || x > ROWS -1 || y > COLUMNS -1) return ERROR;
		if (value != EMPTY && value != WHITE && value != BLACK ) return ERROR;

		b[x][y] = value;
		return 0;
	}

	public boolean canMove(Position p1, Position p2) {
		return canMove(p1.x, p1.y, p2.x, p2.y);
	}

	public boolean canMove(int x1, int y1, int x2, int y2) {

		if (Position.equalPoints(x1,y1,x2,y2)) return false;

		if(! insideBoard(x1,y1) || ! insideBoard(x2,y2)) return false;

		if(get(x2,y2) != EMPTY) return false;

		boolean canMoveDiagonal = false;

		if ((x1 % 2 == 0 && y1 % 2 == 0 )||
				(x1 % 2 == 1 && y1 % 2 == 1 ) ) canMoveDiagonal = true;

		// Horizontal
		if (x1 == x2 && Math.abs(y2-y1) == 1) return true;

		//Vertical
		if (y1 == y2 && Math.abs(x2-x1) == 1) return true;

		//Diagonal
		if (Math.abs(x2 - x1) == 1 && Math.abs(y2 - y1) == 1 && canMoveDiagonal) return true;


		return false;
	}

	public boolean insideBoard(int x, int y){
		if (x < 0 || y < 0 || x > ROWS -1 || y > COLUMNS -1) return false;
		return true;
	}

	public PlayType playType(Position p1, Position p2){
		return playType(p1.x,p1.y,p2.x,p2.y);
	}

	private PlayType playType(int x1, int y1, int x2, int y2) {

		int difX = x2-x1;
		int difY = y2-y1;

		int approachX = x2+difX;
		int approachY = y2+difY;
		int withdrawalX = x1-difX;
		int withdrawalY = y1-difY;


		boolean isApproach = false;
		boolean isWithdrawal = false;

		if(insideBoard(approachX, approachY)){
			if(get(approachX,approachY) != get(x1,y1) && get(approachX,approachY) != EMPTY){
				isApproach = true;
			}
		}

		if(insideBoard(withdrawalX, withdrawalY)){
			if(get(withdrawalX,withdrawalY) != get(x1,y1) && get(withdrawalX,withdrawalY) != EMPTY){
				isWithdrawal = true;
			}
		}

		if(isApproach && !isWithdrawal)
			return PlayType.APPROACH;

		if(!isApproach && isWithdrawal)
			return PlayType.WITHDRAWAL;

		if(isApproach && isWithdrawal)
			return PlayType.BOTH;


		return PlayType.NONE;
	}

	public ArrayList<Position> gainedPieces(Position p1, Position p2, PlayType pt){
		return gainedPieces(p1.x,p1.y,p2.x,p2.y,pt);
	}

	public ArrayList<Position> gainedPieces(int x1, int y1, int x2, int y2, PlayType pt){

		int difX = x2-x1;
		int difY = y2-y1;

		if(pt == PlayType.WITHDRAWAL)
			return countInLine(x1, y1, -difX, -difY, get(x1,y1));
		else if(pt == PlayType.APPROACH)
			return countInLine(x2, y2, difX, difY, get(x1,y1));
		else if(pt == PlayType.NONE)
			return new ArrayList<Position>();

		return null;
	}

	private ArrayList<Position> countInLine(int x, int y, int difX, int difY, int color) {

		int newX = x + difX;
		int newY = y + difY;

		ArrayList<Position> gainedPieces = new ArrayList<Position>();

		while(insideBoard(newX, newY)){
			if(color != EMPTY && color != get(newX, newY)){
				Position p = new Position(newX, newY);
				gainedPieces.add(p);

				newX += difX;
				newY += difY;
			}
			else
				break;
		}

		return gainedPieces;
	}

	public ArrayList<Move> getMovesInPosition(Position p){

		ArrayList<Move> moves = new ArrayList<Move>();

		for(int i = p.x-1; i <= p.x+1; i++){
			int yLeft = p.y - 1;
			int yRight = p.y + 1;

			Position pLeft = new Position(i, yLeft); 
			Position pRight = new Position(i, yRight);
			Position pMid = new Position(i, p.y);

			if(canMove(p, pLeft)){
				moves.add(new Move(p, pLeft, playType(p, pLeft)));
			}

			if(canMove(p, pRight)){
				moves.add(new Move(p, pRight, playType(p, pRight)));
			}	

			if(canMove(p, pMid)){
				moves.add(new Move(p, pMid, playType(p, pMid)));
			}	
		}

		return moves;
	}

	public void move(Move m){

		ArrayList<Position> gainedPieces = gainedPieces(m.pInit, m.pFinal, m.type);

		for(Position piece: gainedPieces){
			set(piece, EMPTY);
		}
		
		set(m.pFinal, get(m.pInit));
		set(m.pInit, EMPTY);
	}

	public ArrayList<Move> possibleMoves(int color){
		ArrayList<Move> plays = new ArrayList<Move>();
		for(int i = 0; i < ROWS; i++) 
		{
			for (int j = 0; j < COLUMNS; j++) {
				if (get(i,j) == color) 
					plays.addAll(getMovesInPosition(new Position(i,j)));
			}
		}
		
		return plays;
	}

	public void show(){
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				if(get(i,j) == WHITE)
					System.out.print("X-");
				else if(get(i,j) == BLACK)
					System.out.print("Y-");
				else
					System.out.print("  ");

			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		Board board = new Board();
		board.show();

		Position p1 = new Position(1,3);
		Position p2 = new Position(2,4);
		
		//Move m = new Move(p1,p2,PlayType.APPROACH);
		
		System.out.println(board.possibleMoves(WHITE));
		
	}

}
