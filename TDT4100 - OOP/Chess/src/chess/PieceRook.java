package chess;

public class PieceRook extends Piece {
	private Game game;
	
	public PieceRook(boolean color, int row, int col, Game game) {
		super("rook", color, row, col);
		this.game = game;
	}
	@Override
	public boolean canMove(int dx, int dy, int x, int y) {
		if (x != 0 && y != 0) { // Can only move at a right-angle
			return false;
		}
		for (int i = 1; i < dx + dy; i++) {
			if (!game.isEmpty(row + i * x, col + i * y)) {
				return false; // Can't move through a piece
			}
		}
		return true;
	}
	@Override
	public String toString() {
		return color ? "♖" : "♜";
	}
}