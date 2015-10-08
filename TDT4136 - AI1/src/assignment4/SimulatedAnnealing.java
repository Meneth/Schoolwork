package assignment4;

public class SimulatedAnnealing {
	public static Solution findSolution(double temp, double dT, Solution start, int target) {
		Solution currentSolution = start;
		Solution[] neighbors;
		double value = start.getObjectiveValue();
		
		while (value < target) { // If value is above target, then no need to continue search
			neighbors = currentSolution.generateNeigbhors();
			Solution solMax = getMaxSolution(neighbors);
			double valMax = solMax.getObjectiveValue();
			if (temp > 0) {
				double q = (valMax - value)/value;
				double p = Math.min(1d, Math.exp(-q/temp));
				double x = Math.random();
				if (x > p)
					currentSolution = solMax;
				else {
					int index = (int) (Math.random() * (neighbors.length - 1));
					currentSolution = neighbors[index];
				}
			} else { // At T <= 0 the exp function starts behaving strangely, so we stop here. This also ensures there's an iteration limit
				if (valMax == value)
					break;
				currentSolution = solMax; // We keep climbing until we hit a plateau
			}
			value = currentSolution.getObjectiveValue();
			temp -= dT;
		}
		return currentSolution;
	}
	
	/**
	 * Returns the solution with the highest value among a set of solutions
	 * If more than one has this value, then the first one found is returned
	 * @param solutions The set of solutions
	 * @return The best solution
	 */
	public static Solution getMaxSolution(Solution[] solutions) {
		double max = 0;
		Solution currentSolution = null;
		for (Solution solution : solutions) {
			if (solution.getObjectiveValue() > max) {
				max = solution.getObjectiveValue();
				currentSolution = solution;
			}
		}
		return currentSolution;
	}
}
