package chess;

public class Move {
	private final int oldX, oldY, newX, newY;
	private final Piece defender;
	public Move(int oldX, int oldY, int newX, int newY, Piece defender) {
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
		this.defender = defender;
	}
	public int getOldX() {
		return oldX;
	}
	public int getOldY() {
		return oldY;
	}
	public int getNewX() {
		return newX;
	}
	public int getNewY() {
		return newY;
	}
	public Piece getDefender() {
		return defender;
	}
	@Override
	public String toString() {
		return "[" + oldX + ";" + oldY + ";" + newX + ";" + newY + "]";
	}
}
