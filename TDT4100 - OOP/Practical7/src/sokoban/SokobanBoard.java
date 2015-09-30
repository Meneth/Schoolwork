package sokoban;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class SokobanBoard {
	private SokobanTile[][] board;
	private int y, x;
	private Stack<int[]> log; // Stores dx, dy, and box moved as ints
	private Stack<int[]> futureLog; // Stores dx, dy, (and box moved) as ints
	
	public SokobanBoard(String s) {
		log = new Stack<int[]>();
		futureLog = new Stack<int[]>();
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
			int[] logEntry = new int[] {dy, dx, 0};
			boolean clearFuture = true;
			if (board[x+dx][y+dy].getContents() == "box") { // Note: Can crash the game if outer walls are missing
				board[x+dx+dx][y+dy+dy].setContents(board[x+dx][y+dy].getContents());
				log.push(new int[] {dy, dx, 1});
			} else if (log.size() != 0) {
				int[] peek = log.peek();
				if (peek[0] == -logEntry[0] && peek[1] == -logEntry[1]) {
					log.pop();
					clearFuture = false;
					futureLog.push(new int[] {-dy, -dx});
				} else {
					log.push(logEntry);
				}
			} else {
				log.push(logEntry);
			}
			if (futureLog.size() != 0 && clearFuture) {
				int[] futurePeek = futureLog.peek();
				if (futurePeek[0] == logEntry[0] && futurePeek[1] == logEntry[1]) {
					System.out.println("Test");
					futureLog.pop();
				} else {
					futureLog.clear();
				}
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
	public void undo() {
		if (log.size() == 0) {
			return; // Nothing to undo
		}
		int[] value = log.peek();
		int dx = -value[1];
		int dy = -value[0];
		doMove(dy, dx);
		if (value[2] == 1) {
			board[x-dx-dx][y-dy-dy].setContents("empty");
			board[x-dx][y-dy].setContents("box");
		}
	}
	public void redo() {
		if (futureLog.size() == 0) {
			return; // Nothing to undo
		}
		int[] value = futureLog.peek();
		doMove(value[0], value[1]);
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
	public void save(String file) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file + ".txt"));
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					writer.write(board[i][j].saveFormat());
				}
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
