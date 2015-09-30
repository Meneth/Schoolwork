package chess;

public class PieceKing extends Piece {
	public PieceKing(boolean color, int row, int col) {
		super("king", color, row, col);
	}
	@Override
	public boolean canMove(int dx, int dy, int x, int y) {
		return dx <= 1 && dy <= 1; // Can move a single spot if the target is valid
	}
	@Override
	public String toString() {
		return color ? "♔" : "♚";
	}
}