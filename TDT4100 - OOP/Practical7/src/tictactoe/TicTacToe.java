package tictactoe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class TicTacToe {
	private char player;
	private String[] grid;
	private Stack<int[]> log;
	private Stack<int[]> futureLog;
	
	public TicTacToe() {
		log = new Stack<int[]>();
		futureLog = new Stack<int[]>();
		grid = new String[3];
		grid[0] = "   ";
		grid[1] = "   ";
		grid[2] = "   ";
		player = 'x';
	}
	private static boolean cellExists(int x, int y) {
		return x < 3 && x >= 0 && y < 3 && y >= 0;
	}
	public char getCell(int x, int y) {
		if (cellExists(x, y)) {
			return grid[y].charAt(x);
		}
		return ' ';
	}
	public boolean isOccupied(int x, int y) {
		return getCell(x, y) != ' ';
	}
	private static boolean validPlayer(char c) {
		return c == 'x' || c == 'o';
	}
	public boolean setCell(char c, int x, int y) {
		if (!cellExists(x, y)) {
			return false;
		}
		if (validPlayer(c) && !isOccupied(x, y)) {
			grid[y] = grid[y].substring(0, x) + c + grid[y].substring(x + 1);
			log.push(new int[] {x, y, c});
			if (futureLog.size() != 0 && Arrays.equals(new int[] {x, y}, futureLog.peek())) {
				futureLog.pop();
			} else {
				futureLog.clear();
			}
			return true;
		}
		return false;
	}
	public void undo() {
		if (log.size() == 0) {
			return; // Nothing to undo
		}
		int[] value = log.pop();
		int x = value[0];
		int y = value[1];
		grid[y] = grid[y].substring(0, x) + ' ' + grid[y].substring(x + 1);
		futureLog.push(new int[] {x, y});
		if (player == 'x') {
			player = 'o';
		} else {
			player = 'x';
		}
	}
	public void redo() {
		if (futureLog.size() == 0) {
			return; // Nothing to undo
		}
		int[] value = futureLog.peek();
		play(value[0], value[1]);
	}
	public void save(String file) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file + ".txt"));
			for (int[] i : log) {
				writer.write(i[0] + " " + i[1]);
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
			log.clear();
			futureLog.clear();
			grid[0] = "   ";
			grid[1] = "   ";
			grid[2] = "   ";
			player = 'x';
			while(true) {
				String s = reader.readLine();
				if (s == null) {
					break;
				}
				play(s.charAt(0) - '0', s.charAt(2) - '0');
			}
			reader.readLine();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public boolean isFinished() {
		return hasWinner() || (!grid[0].contains(" ") && !grid[1].contains(" ") && !grid[2].contains(" "));
	}
	public boolean hasWinner() {
		 return isWinner('x') || isWinner('o');
	}
	public boolean isWinner(char c) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (getCell(i, j) != c) {
					break;
				}
				if (j == 2) { return true; }
			}
			for (int j = 0; j < 3; j++) {
				if (getCell(j, i) != c) {
					break;
				}
				if (j == 2) { return true; }
			}
		}
		if (getCell(0, 0) == c && getCell(1, 1) == c && getCell(2, 2) == c) {
			return true;
		}
		if (getCell(0, 2) == c && getCell(1, 1) == c && getCell(2, 0) == c) {
			return true;
		}
		return false;
	}
	public char winner() {
		if (isWinner('o')) {
			return 'o';
		}
		if (isWinner('x')) {
			return 'x';
		}
		return ' ';
	}
	public void play(int x, int y) {
		System.out.println(x + " " + y);
		if (!cellExists(x, y)) {
			return;
		}
		if (!isOccupied(x, y)) {
			setCell(player, x, y);
			if (player == 'x') {
				player = 'o';
			} else {
				player = 'x';
			}
		}
	}
	
	public char getCurrentPlayer() {
		return player;
	}
	
	public String toString() {
		return " " + grid[0].charAt(0) + " | " + grid[0].charAt(1) + " | " + grid[0].charAt(2) + "\n"
				+ "-----------\n"
				+ " " + grid[1].charAt(0) + " | " + grid[1].charAt(1) + " | " + grid[1].charAt(2) + "\n"
				+ "-----------\n"
				+ " " + grid[2].charAt(0) + " | " + grid[2].charAt(1) + " | " + grid[2].charAt(2);
	}
}
