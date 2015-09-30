package tictactoe;

import java.util.Scanner;

import interfaces.ConsoleGame;

public class TicTacToeProgram implements ConsoleGame {
	private TicTacToe game;
	private Scanner scanner;
	
	@Override
	public void init() {
		game = new TicTacToe();
		scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		System.out.println(game);
		System.out.println("Enter x and y coordinate of next placement. Coordinates are 0-indexed. Move format is 'x y'.");
		System.out.println("Commands: 'undo', 'redo', 'save filename', 'load filename'.");
		while (!game.hasWinner()) {
			System.out.println("Player " + game.getCurrentPlayer() + ", enter position of desired move:");
			if (doLine(scanner.nextLine()) != null) {
				break;
			}
		}
		if (game.isWinner('x')) { System.out.println("Player x won!"); }
		else if (game.isWinner('o')) { System.out.println("Player o won!"); }
		else { System.out.println("The game resulted in a draw!"); }
		scanner.close();
	}

	@Override
	public Integer doLine(String input) {
		String[] in = input.split(" ");
		if (input.equals("undo")) {
			game.undo();
			System.out.println(game);
			return null;
		}
		if (input.equals("redo")) {
			game.redo();
			System.out.println(game);
			return null;
		}
		if (in[0].equals("save")) {
			game.save(in[1]);
			System.out.println(game);
			return null;
		}
		if (in[0].equals("load")) {
			game.load(in[1]);
			System.out.println(game);
			return null;
		}
		game.play(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
		System.out.println(game);
		if (game.hasWinner()) {
			return 1;
		}
		if (game.isFinished()) {
			return 0;
		}
		return null;
	}
	
	public static void main(String[] args) {
		TicTacToeProgram program = new TicTacToeProgram();
		program.init();
		program.run();
	}
}
