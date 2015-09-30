package objectstructures;

import java.util.Scanner;

public class TicTacToe {
	private char player;
	private String[] grid;
	
	public TicTacToe() {
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
			return true;
		}
		return false;
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
	
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter x and y coordinate of next placement. Coordinates are 0-indexed. Move format is 'x y'.");
		System.out.println(game);
		while (!game.hasWinner()) {
			System.out.println("Player " + game.player + ", enter position of desired move:");
			if (scanner.hasNextInt()) {
				int x = scanner.nextInt();
				if (scanner.hasNextInt()) {
					int y = scanner.nextInt();
					game.play(x, y);
					System.out.println(game);
				}
			}
		}
		if (game.isWinner('x')) { System.out.println("Player x won!"); }
		else if (game.isWinner('o')) { System.out.println("Player o won!"); }
		else { System.out.println("The game resulted in a draw!"); }
		scanner.close();
	}
}
