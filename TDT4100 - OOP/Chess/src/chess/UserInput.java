package chess;

import java.util.Scanner;

public class UserInput implements ConsoleGame {
	private Game game;
	
	@Override
	public void init() {
		game = new Game();
	}

	@Override
	public void run() {
		System.out.print(game);
		Scanner scanner = new Scanner(System.in);
		String move;
		Integer result = null;
		while (true) {
			System.out.print(game.getWhiteTurn() ? "White move: " : "Black move: ");
			if (scanner.hasNext()) {}
			move = scanner.nextLine().toLowerCase();
			try {
				result = doLine(move);
				System.out.print(game);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			if(result != null) {
				break;
			}
		}
		scanner.close();
		if(result == -1) {
			System.out.println("Black won!");
		}
		if(result == 1) {
			System.out.println("White won!");
		}
	}

	@Override
	public Integer doLine(String s) throws IllegalArgumentException {
		if (s.equals("undo")) {
			game.undo();
			return null;
		}
		if (s.split(" ")[0].equals("save")) {
			Save.save(game.getLog(), s.split(" ")[1]);
			return null;
		}
		if (s.split(" ")[0].equals("load")) {
			game = Save.load(s.split(" ")[1]);
			return null;
		}
		if (s.length() != 5)
			throw new IllegalArgumentException("Invalid input length.");
		if (game.canMove('8'-s.charAt(1), s.charAt(0)-'a', '8'-s.charAt(4), s.charAt(3)-'a', game.getWhiteTurn()))
			game.move('8'-s.charAt(1), s.charAt(0)-'a', '8'-s.charAt(4), s.charAt(3)-'a');
		else
			throw new IllegalArgumentException("Illegal move.");
		if (game.checkmate(true)) {
			return -1;
		}
		if (game.checkmate(false)) {
			return 1;
		}
		return null;
	}
	
	public static void main(String[] args) {
		UserInput program = new UserInput();
		program.init();
		program.run();
	}
}
