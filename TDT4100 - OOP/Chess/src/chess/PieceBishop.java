package chess;

public class PieceBishop extends Piece {
	private Game game;
	
	public PieceBishop(boolean color, int row, int col, Game game) {
		super("bishop", color, row, col);
		this.game = game;
	}
	@Override
	public boolean canMove(int dx, int dy, int x, int y) {
		if (x == 0 || y == 0) {
			return false; // Can only move diagonally
		}
		if (dx != dy) {
			return false;
		}
		for (int i = 1; i < dx; i++) {
			if (!game.isEmpty(row + i * x, col + i * y)) {
				return false; // Can't move through a piece
			}
		}
		return true;
	}
	@Override
	public String toString() {
		return color ? "♗" : "♝";
	}
}