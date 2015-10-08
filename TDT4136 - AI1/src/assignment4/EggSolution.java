package assignment4;

import java.util.ArrayList;
import java.util.List;

public class EggSolution implements Solution {
	private final boolean[][] board;
	private final int size;
	private final int constraint;

	/**
	 * Creates a new egg solution with a given board
	 * @param board The board to use for the solution
	 * @param constraint The max number of eggs per line
	 * @throws IllegalArgumentException Thrown if the constraint is violated
	 */
	public EggSolution(boolean[][] board, int constraint) throws IllegalArgumentException {
		this.board = board;
		this.size = board.length;
		this.constraint = constraint;
		if (!checkConstraints())
			throw new IllegalArgumentException("Board violates constraint");
	}
	
	/**
	 * Checks whether the constraint is violated on any row
	 * @return True if no constraint is violated
	 */
	private boolean checkConstraints() {
		for (int x = 0; x < size; x++) {
			int[] eggs = new int[6];
			for (int y = 0; y < size; y++) {
				if (getPosition(x, y)) {
					eggs[0]++;
				}
				if (getPosition(y, x))
					eggs[1]++;
				if (getPosition(x+y, y))
					eggs[2]++;
				if (x != y && getPosition(y-x, y)) // x != y, or we'd count the diagonal starting in 0,0 twice
					eggs[3]++;
				if (getPosition(size - (x+y) - 1, size - y - 1))
					eggs[4]++;
				if (x != y && getPosition(size - (y-x) - 1, size - y - 1)) // x != y, or we'd count the diagonal starting in size-1,0 twice
					eggs[5]++;
			}
			for (int i : eggs) {
				if (i > constraint)
					return false;
			}
		}
		return true;
	}

	/**
	 * Uses a logarithm to find the objective value, as this keeps the percentage improvement when adding one more egg a bit more consistent
	 */
	@Override
	public double getObjectiveValue() {
		int maxEggs = size * constraint;
		double error = Math.log(maxEggs - countEggs() + 1) / Math.log(maxEggs + 1);
		return 1 - error;
	}

	/**
	 * Returns whether there's an egg in a given position
	 * @param x The position on the x axis
	 * @param y The position on the y axis
	 * @return Whether there's an egg in the position
	 */
	private boolean getPosition(int x, int y) {
		try {
			return board[x][y];
		}
		catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Counts the number of eggs on the board
	 * @return The number of eggs on the board
	 */
	private int countEggs() {
		int eggCount = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (getPosition(x, y)) {
					eggCount++;
				}
			}
		}
		return eggCount;
	}

	/**
	 * Varies the current board by adding all valid boards with one egg added or one egg moved one step
	 */
	@Override
	public Solution[] generateNeigbhors() {
		List<Solution> neighbors = new ArrayList<>();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (getPosition(x, y))
					generateMoves(x, y, neighbors);
				else {
					addEgg(x, y, neighbors);
				}
			}
		}
		return (Solution[]) neighbors.toArray(new Solution[0]);
	}

	/**
	 * Adds a copy of the board with an egg added in the given position to the neighbors list if the new board is valid
	 * Does nothing otherwise
	 */
	private void addEgg(int x, int y, List<Solution> neighbors) {
		boolean[][] board = copyOf(this.board);
		board[x][y] = true;
		generateSolution(board, constraint, neighbors);
	}

	/**
	 * Attempts to add a copy of the board with the given egg moved one step
	 */
	private void generateMoves(int x, int y, List<Solution> neighbors) {
		boolean[][] board = copyOf(this.board);
		board[x][y] = false;
		generateMove(x + 1, y, neighbors, board);
		generateMove(x - 1, y, neighbors, board);
		generateMove(x, y + 1, neighbors, board);
		generateMove(x, y - 1, neighbors, board);
	}
	
	/**
	 * Adds a copy of the board with an egg added in the given position to the neighbors list if the new board is valid
	 * Does nothing otherwise
	 */
	private void generateMove(int x, int y, List<Solution> neighbors, boolean[][] board) {
		try {
			boolean[][] newBoard = copyOf(board);
			board[x][y] = true;
			generateSolution(newBoard, constraint, neighbors);
		} catch (IndexOutOfBoundsException|IllegalArgumentException e) {
			
		}
	}
	
	/**
	 * Adds the given variation of the board to the neighbors list if it is valid
	 * Does nothing otherwise
	 */
	private static void generateSolution(boolean[][] board, int constraint, List<Solution> neighbors) {
		try {
			Solution newSolution = new EggSolution(board, constraint);
			neighbors.add(newSolution);
		} catch (IllegalArgumentException e) {

		}
	}
	
	public String toString() {
		String s = "";
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (getPosition(x, y))
					s += "|O";
				else
					s += "| ";
			}
			s += "|\n";
		}
		s += "There are " + countEggs() + "/" + (size * constraint) + " eggs.";
		return s;
	}
	
	/**
	 * Creates a deep copy of a given board
	 */
	private boolean[][] copyOf(boolean[][] board) {
		boolean[][] copy = new boolean[board.length][board.length];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				copy[x][y] = board[x][y];
			}
		}
		return copy;
	}
}
