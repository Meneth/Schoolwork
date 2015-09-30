package objectstructures;

import java.util.Scanner;

public class SudokuProgram {
	private Sudoku sudoku;
	private Scanner scanner;
	private void init() {
		sudoku = new Sudoku("3..4......4.....8......9.....43....6....6..........1..1............5..1...3.....2");
		scanner = new Scanner(System.in);
	}
	private void run() {
		System.out.println(sudoku);
		System.out.println("Enter move:");
		while(scanner.hasNext()) {
			String s = scanner.next();
			if (!sudoku.playerSetCell(s.charAt(0) - 'a', s.charAt(1) - '1', s.charAt(2) - '0')) {
				System.out.println("Invalid move!");
			}
			System.out.println(sudoku);
			if (sudoku.gameOver()) {
				System.out.println("You have won!");
				break;
			}
			System.out.println("Enter move:");
		}
	}
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku("123456789456789123789123456231564897564897231897231564312645978645978312978312645");
		System.out.println(sudoku);
		long t = System.nanoTime();
		System.out.println(sudoku.gameOver());
		System.out.println(System.nanoTime()-t);
		SudokuProgram program = new SudokuProgram();
		program.init();
		program.run();
	}
}
