package sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class Sudoku {
	private String[] board, startBoard;
	private Stack<int[]> log; // Stores the undo log. 4 ints are stored per move: x, y, new value, old value
	private Stack<int[]> futureLog; // Stores the redo log
	
	public Sudoku(String problem) {
		board = new String[9];
		startBoard = new String[9];
		log = new Stack<int[]>();
		futureLog = new Stack<int[]>();
		for (int i = 0; i < 9; i++) {
			board[i] = ("000000000"); // 9 blank rows
			startBoard[i] = ("000000000"); // 9 blank rows
		}
		for (int i = 0; i < problem.length(); i++) {
			setCell((i % 9), (int) (i/9), problem.charAt(i) - '0', true);
		}
	}
	private void setCell(int x, int y, int value, boolean problem) { // Undo and redo bypass this method
		int original = board[y].charAt(x) - '0';
		int[] move = new int[] {x, y, value, original};
		if (futureLog.size() != 0 && Arrays.equals(move, futureLog.peek())) {
			futureLog.pop();
		} else {
			futureLog.clear();
		}
		setCell(move, problem, false);
	}
	private void setCell(int[] move, boolean problem, boolean undo) {
		int value;
		if (undo) {
			value = move[3];
		} else {
			value = move[2];
		}
		int x = move[0];
		int y = move[1];
		if (value == -2) {
			value = 0;
		}
		board[y] = board[y].substring(0, x)+value+board[y].substring(x+1);
		if (problem) {
			startBoard[y] = startBoard[y].substring(0, x)+value+startBoard[y].substring(x+1);
		} else if (!undo) {
			log.push(move);
		}
	}
	public boolean playerSetCell(int x, int y, int value) {
		if (x < 0 || x > 8 || y < 0 || y > 8) {
			throw new IllegalArgumentException("Index outside board.");
		}
		if ((value < 0 || value > 9) && value != -2) {
			throw new IllegalArgumentException("Value outside allowed range (1-9).");
		}
		if (isCellPartOfProblem(x, y)) {
			return false;
		}
		setCell(x, y, value, false);
		return true;
	}
	private static boolean isStringLegal(String s) {
		for (int i = 0; i < 9; i++) { // This double loop has a worst case of O(n^2), but for 9 that only gives 45 comparisons
			if (s.charAt(i) == '0') {
				continue;
			}
			for (int j = i + 1; j < 9; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
	private boolean isColumnLegal(int col) {
		String s = "";
		for (int i = 0; i < 9; i++) {
			s += getCell(col, i);
		}
		return isStringLegal(s);
	}
	private boolean isRowLegal(int row) {
		String s = "";
		for (int i = 0; i < 9; i++) {
			s += getCell(i, row);
		}
		return isStringLegal(s);
	}
	private boolean isRegionLegal(int rx, int ry) {
		String s = "";
		for (int i = 0; i < 3; i++) {
			s += getCell(rx*3, i + ry*3); // Order doesn't matter to isStringLegal
			s += getCell(rx*3+1, i + ry*3);
			s += getCell(rx*3+2, i + ry*3);
		}
		return isStringLegal(s);
	}
	public boolean gameOver() { // Average case will be O(n^2), with at most 9^2 comparisons. Worst case is O(n^3), with 9*3*45 + 9^2 comparisons
		for (int i = 0; i < 9; i++) { // O(n^2). Worst case: 9^2 comparisons
			if (board[i].contains("0")) { // O(n). Worst case: 9 comparisons
				return false; // Simple check for empty spots
			}
		}
		for (int i = 0; i < 9; i++) { // O(n^3). Worst case: 9*3*45 comparisons
			if (!isColumnLegal(i)) { return false; } // O(n^2). Worst case: 45 comparisons
			if (!isRowLegal(i)) { return false; } // O(n^2). Worst case: 45 comparisons
			if (i < 3) {
				for (int j = 0; j < 3; j++) {
					if (!isRegionLegal(i, j)) { return false; } // O(n^2). Worst case: 45 comparisons
				}
			}
		}
		return true;
	}
	
	private boolean isCellPartOfProblem(int x, int y) {
		return getCell(x, y) == getProblemCell(x, y) && getCell(x, y) != 0;
	}
	private int getCell(int x, int y) { // Not specified if JExercise needs access to this, nor if x and y are 0-indexed or 1-indexed
		return board[y].charAt(x) - '0'; // Integer value of position
	} // 0-indexing assumed
	private int getProblemCell(int x, int y) {
		return startBoard[y].charAt(x) - '0'; // Integer value of position
	}
	
	public void undo() {
		if (log.size() == 0) {
			return; // Nothing to undo
		}
		int[] value = log.pop();
		setCell(value, false, true);
		futureLog.push(value);
	}
	public void redo() {
		if (futureLog.size() == 0) {
			return; // Nothing to undo
		}
		int[] value = futureLog.pop();
		setCell(value, false, false);
	}
	public void save(String file) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file + ".txt"));
			for (int i = 0; i < 9; i++) {
				writer.write(board[i]);
				writer.newLine();
			}
			for (int i = 0; i < 9; i++) {
				writer.write(startBoard[i]);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public void load(String file) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file + ".txt"));
			for (int i = 0; i < 9; i++) {
				board[i] = reader.readLine();
			}
			for (int i = 0; i < 9; i++) {
				startBoard[i] = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String output = "   ";
		for (int i = 0; i < 9; i++) {
			if (!isColumnLegal(i)) {
				output += "!";
			} else { output += " "; }
			output += ((char) (i+'a'));
			if (i % 3 == 2) {
				output += "  ";
			}
		}
		output += "\n  +-------+-------+-------+\n";
		for (int i = 0; i < 9; i++) {
			if (!isRowLegal(i)) {
				output += "!"; // Show invalid line
			} else { output += " "; }
			output += (i+1);
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0) {
					output += "|";
					if (!isRegionLegal((int) (i/3), (int) (j/3))) {
						output += "!";
					}  else { output += " "; }
				}
				int cell = getCell(j, i);
				if (cell == 0) { // Value of period char minus '0'
					output += ". ";
				} else {
					output += cell + " ";
				}
			}
			output += "|\n";
			if (i % 3 == 2) {
				output += "  +-------+-------+-------+\n";
			}
		}
		return output;
	}
}
