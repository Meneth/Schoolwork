package objectstructures;

import java.util.Scanner;

public class SokobanProgram {
	private SokobanBoard board;
	private Scanner scanner;
	private void init(String s) {
		board = new SokobanBoard(s);
		scanner = new Scanner(System.in);
	}
	private void run() {
		System.out.println(board);
		String move;
		scanner.hasNext();
		while (!board.gameWon()) {
			move = scanner.next().toLowerCase();
			if (move.equals("w")) {
				if (!board.up()) {
					System.out.println("Invalid move.");
				}
			} else if (move.equals("s")) {
				if (!board.down()) {
					System.out.println("Invalid move.");
				}
			} else if (move.equals("a")) {
				if (!board.left()) {
					System.out.println("Invalid move.");
				}
			} else if (move.equals("d")) {
				if (!board.right()) {
					System.out.println("Invalid move.");
				}
			}
			System.out.println(board);
		}
		System.out.println("Congratulations! You win!");
	}
	public static void main(String[] args) {
		SokobanProgram game = new SokobanProgram();
		game.init(SampleLevels.SAMPLE_LEVEL2);
		game.run();
	}
}