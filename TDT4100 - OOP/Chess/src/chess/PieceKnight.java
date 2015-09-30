package chess;

public class PieceKnight extends Piece {
	public PieceKnight(boolean color, int row, int col) {
		super("knight", color, row, col);
	}
	@Override
	public boolean canMove(int dx, int dy, int x, int y) { // Can only move in L-shape, but jumps over pieces
		return (dx == 1 && dy == 2) || (dy == 1 && dx == 2);
	}
	@Override
	public String toString() {
		return color ? "♘" : "♞";
	}
}