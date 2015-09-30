package chess;

public abstract class Piece {
	private final String type; // Keeps track of what type of piece this is. Currently unused
	protected final boolean color;
	protected int row; // Current position
	protected int col;
	
	public Piece(String type, boolean color, int row, int col) {
		this.type = type;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	public String getType() {
		return type;
	}
	public boolean getColor() {
		return color;
	}
	public int[] getPosition() {
		return new int[] {row, col};
	}
	// Implemented by each piece. Returns whether it can move to the target tile or not
	public abstract boolean canMove(int dx, int dy, int x, int y);
	public void move(int row, int col) { // Updates position after move
		this.row = row;
		this.col = col;
	}
	public abstract String toString();
}
