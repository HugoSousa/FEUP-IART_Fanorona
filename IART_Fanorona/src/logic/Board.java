package logic;

import java.util.Arrays;

public class Board {
	public static final int EMPTY = -1;
	public static final int BLACK = 1;
	public static final int WHITE = 0;
	public static final int ERROR = -2;
	
	private static final int ROWS = 5;
	private static final int COLUMNS =  9;
	
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
		Arrays.fill(b[0], 1);
		Arrays.fill(b[1], 1);
		Arrays.fill(b[3], 0);
		Arrays.fill(b[4], 0);
		
		for (int i = 0; i < COLUMNS; i++) {
			if (i % 2 == 0) b[2][i] = 1;
			else b[2][i] = 0;
		}
		b[2][4] = EMPTY;
	}
	public int set(Position pos, int value) {
		return set(pos.x,pos.y, value);
	}
	
	public int set(int x, int y, int value) {
		if (x < 0 || y < 0 || x > ROWS -1 || y > COLUMNS -1) return ERROR;
		if (value != EMPTY || value != WHITE || value != BLACK ) return ERROR;
	
		b[x][y] = value;
		return 0;
	}
	
	public boolean canMove(Position p1, Position p2) {
		return canMove(p1.x, p1.y, p2.x, p2.y);
	}

	public boolean canMove(int x1, int y1, int x2, int y2) {
		// verificacao p1 == p2
		if (Position.equalPoints(x1,y1,x2,y2)) return false;
		
		// TODO restrictions limites
		
		
		boolean canMoveDiagonal = false;
		
		if ((x1 % 2 == 0 && y1 % 2 == 0 )||
			(x1 % 2 == 1 && y1 % 2 == 1 ) ) canMoveDiagonal = true;
		
		// Horizontal
		if (x1 == x2) return true;
		if (y1 == y2) return true;
		
		if (Math.abs(x2 - x1) == Math.abs(y2 - y1) && canMoveDiagonal) return true;
		
		
		return false;
	}

}
