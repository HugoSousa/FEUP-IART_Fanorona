package logic;

public class Position {

	public int x;
	public int y;
	
	public Position() {
		x = 0;
		y = 0;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void set(int x, int y) { this.x = x; this.y = y;}
	public int getX() { return x; }
	public int getY() { return y; }
	
	public boolean equals(Position p2) {
		return (this.x == p2.x && this.y == p2.y);
	}
	
	public static boolean equalPoints(Position p1, Position p2) {
		return p1.equals(p2);
	}
	
	public static boolean equalPoints(int x1, int y1, int x2, int y2) {
		return (x1 == 2 && y1 == y2);
	}
	
}
