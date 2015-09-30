package sokoban;

import interfaces.ConsoleGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SokobanProgram implements ConsoleGame {
	private SokobanBoard board;
	private Scanner scanner;
	
	@Override
	public void init() {
		init(SampleLevels.SAMPLE_LEVEL2);
	}
	private void init(String s) {
		board = new SokobanBoard(s);
		scanner = new Scanner(System.in);
	}
	public void run() {
		System.out.println(board);
		String move;
		while (scanner.hasNext()) {
			move = scanner.nextLine().toLowerCase();
			if(doLine(move) != null) {
				break;
			}
			System.out.println(board);
		}
		System.out.println("Congratulations! You win!");
	}
	@Override
	public Integer doLine(String input) {
		String[] split = input.split(" ");
		if (split[0].equals("save")) {
			board.save(split[1]);
			return null;
		}
		if (split[0].equals("load")) {
			load(split[1]);
			return null;
		}
		if (split[0].equals("undo")) {
			board.undo();
			return null;
		}
		if (split[0].equals("redo")) {
			board.redo();
			return null;
		}
		for(String move : split) {
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
		}
		if (board.gameWon()) {
			return 1;
		}
		return null;
	}
	public void load(String file) {
		BufferedReader reader;
		String line = "";
		try {
			reader = new BufferedReader(new FileReader(file + ".txt"));
			while(true) {
				String s = reader.readLine();
				if (s == null) {
					break;
				}
				line += s + "\n";
			}
			line = line.trim();
		} catch (IOException e) {
			//System.out.println(e);
			e.printStackTrace();
		}
		board = new SokobanBoard(line);
	}
	public static void main(String[] args) {
		SokobanProgram game = new SokobanProgram();
		game.init(SampleLevels.SAMPLE_LEVEL2);
		game.run();
	}
}