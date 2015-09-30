package sudoku;


import interfaces.ConsoleGame;

import java.util.Scanner;

public class SudokuProgram implements ConsoleGame {
	private Sudoku sudoku;
	private Scanner scanner;
	
	@Override
	public void init() {
		sudoku = new Sudoku("3..4......4.....8......9.....43....6....6..........1..1............5..1...3.....2");
		scanner = new Scanner(System.in);
	}
	@Override
	public void run() {
		System.out.println(sudoku);
		System.out.println("Commands: 'undo', 'redo', 'save filename', 'load filename'.");
		System.out.println("Enter move:");
		while(scanner.hasNext()) {
			if (doLine(scanner.nextLine()) != null) {
				System.out.println("You have won!");
				break;
			}
			System.out.println("Enter move:");
		}
	}

	@Override
	public Integer doLine(String input) {
		String[] in = input.split(" ");
		if (input.equals("undo")) {
			sudoku.undo();
			System.out.println(sudoku);
			System.out.print("Move undone. ");
			return null;
		} else if (input.equals("redo")) {
			sudoku.redo();
			System.out.println(sudoku);
			System.out.print("Move redone. ");
			return null;
		} else if (in[0].equals("new")) {
			sudoku = new Sudoku(in[1]);
			System.out.println(sudoku);
			return null;
		} else if (in[0].equals("save")) {
			sudoku.save(in[1]);
		} else if (in[0].equals("load")) {
			sudoku.load(in[1]);
		} else if (!sudoku.playerSetCell(input.charAt(0) - 'a', input.charAt(1) - '1', input.charAt(2) - '0')) {
			System.out.println("Invalid move!");
		}
		System.out.println(sudoku);
		if (sudoku.gameOver()) {
			return 1; // Sudoku has no failure state
		}
		return null;
	}
	
	public static void main(String[] args) {
		SudokuProgram program = new SudokuProgram();
		program.init();
		program.run();
	}
}
