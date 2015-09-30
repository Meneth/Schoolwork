package chess;

public class PieceQueen extends Piece {
	private Game game;
	
	public PieceQueen(boolean color, int row, int col, Game game) {
		super("queen", color, row, col);
		this.game = game;
	}
	@Override
	public boolean canMove(int dx, int dy, int x, int y) {
		if (x != 0 && y != 0) { // Diagonal movement
			if (dx != dy) {
				return false;
			}
			for (int i = 1; i < dx; i++) {
				if (!game.isEmpty(row + i * x, col + i * y)) {
					return false; // Can't move through a piece
				}
			}
			return true;
		} else if (x == 0 || y == 0) { // Right-angle movement
			for (int i = 1; i < dx + dy; i++) {
				if (!game.isEmpty(row + i * x, col + i * y)) {
					return false; // Can't move through a piece
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return color ? "♕" : "♛";
	}
}