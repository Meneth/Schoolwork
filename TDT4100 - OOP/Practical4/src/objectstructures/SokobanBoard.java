package objectstructures;

public class SokobanBoard {
	private SokobanTile[][] board;
	private int y, x;
	
	public SokobanBoard(String s) {
		String[] layout = s.split("\n");
		board = new SokobanTile[layout.length][layout[0].length()]; // Note: Will crash if the first line isn't the longest
		for (int i = 0; i < layout.length; i++) {
			for (int j = 0; j < layout[i].length(); j++) {
				board[i][j] = new SokobanTile(layout[i].charAt(j));
				if (board[i][j].getContents() == "player") {
					x = i; // x is actually up/down due to how arrays work
					y = j;
				}
			}
		}
	}
	private boolean doMove(int dy, int dx) {
		if (board[x+dx][y+dy].isEmpty() || (board[x+dx][y+dy].getContents() == "box" && board[x+dx+dx][y+dy+dy].isEmpty())) {
			if (board[x+dx+dx][y+dy+dy].isEmpty()) { // Note: Can crash the game if outer walls are missing
				board[x+dx+dx][y+dy+dy].setContents(board[x+dx][y+dy].getContents());
			}
			board[x+dx][y+dy].setContents("empty");
			board[x+dx][y+dy].setContents("player");
			board[x][y].setContents("empty");
			y += dy;
			x += dx;
			return true;
		}
		return false;
	}
	public boolean up() { // Up is the negative direction due to how arrays are handled
		return doMove(0, -1);
	}
	public boolean down() {
		return doMove(0, 1);
	}
	public boolean left() {
		return doMove(-1, 0);
	}
	public boolean right() {
		return doMove(1, 0);
	}
	public boolean gameWon() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getContents() == "box" && board[i][j].getType() != "goal") {
					return false;
				}
			}
		}
		return true;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				s += board[i][j];
			}
			s += "\n";
		}
		return s;
	}
}
