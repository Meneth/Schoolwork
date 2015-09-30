package chess;

public class PiecePawn extends Piece {
	private Game game;
	
	public PiecePawn(boolean color, int row, int col, Game game) {
		super("pawn", color, row, col);
		this.game = game;
	}
	@Override
	public boolean canMove(int dx, int dy, int x, int y) {
		if (dy != 0) { // Pawns can only move sideways in order to attack
			if (!game.isEmpty(row + x, col + y)) {
				return (dx == 1 && dy == 1);
			}
			if (!game.isEmpty(row, col + y) && game.getPiece(row, col + y).getType() == "pawn") {
				if (dx == 1 && dy == 1 && game.getPiece(row, col + y * dy).getColor() != color) {
					Move m = game.logPeek();
					if (m.getNewX() == row && m.getNewY() == col + y && Math.abs(m.getNewX() - m.getNewY()) == 2) {
						return true; // En passant
					}
				}
			}
			return false;
		}
		if (dx <= 2) { // Pawns can never move more than two spots
			if (row != 1 && row != 6 && dx == 2) { // Can't move two spots if already moved
				return false;
			}
			if ((color && x == -1) || !color && x == 1) { // Can only move in one direction, specified by color
				return game.isEmpty(row + 1 * x, col); // Can't move through a piece
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return color ? "♙" : "♟";
	}
}