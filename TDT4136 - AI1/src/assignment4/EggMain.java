package assignment4;

public class EggMain {
	public static void main(String[] args) {
		int size = 5, constraint = 2; // Edit these to change between variations
		double temp = 1, dT = 0.01; // Edit these to vary the annealing
		
		boolean[][] board = new boolean[size][size];
		
		Solution start = new EggSolution(board, constraint);
		
		Solution solution = SimulatedAnnealing.findSolution(temp, dT, start, 1);
		System.out.println(solution);
	}
}
